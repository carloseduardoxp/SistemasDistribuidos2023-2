package exemplo2;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClienteSocket2 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3229550953263088466L;
	private JTextField enter;
	private JTextArea display;
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";

	public ClienteSocket2() {
		super("Client");

		Container c = getContentPane();

		enter = new JTextField();
		enter.setEnabled(false);
		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData(e.getActionCommand());
			}
		});
		c.add(enter, BorderLayout.NORTH);

		display = new JTextArea();
		c.add(new JScrollPane(display), BorderLayout.CENTER);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(300, 350);
		setVisible(true);
	}

	public void runClient() {
		Socket client;

		try {
			// Step 1: Create a Socket to make connection.
			display.setText("Attempting connection\n");
			client = new Socket(InetAddress.getByName("127.0.0.1"), 4000);

			display.append("Connected to: "
					+ client.getInetAddress().getHostName());

			// Step 2: Get the input and output streams.
			output = new ObjectOutputStream(client.getOutputStream());
			output.flush();
			input = new ObjectInputStream(client.getInputStream());

			display.append("\nGot I/O streams\n");

			// Step 3: Process connection.
			enter.setEnabled(true);

			do {
				try {
					message = (String) input.readObject();
					System.out.println("" + message);

					display.append("\n" + message);
					display.setCaretPosition(display.getText().length());
				} catch (ClassNotFoundException cnfex) {
					display.append("\nUnknown object type received");
				}
			} while (!message.equals("SERVER>>> TERMINATE"));

			// Step 4: Close connection.
			display.append("Closing connection.\n");
			output.close();
			input.close();
			client.close();
		} catch (EOFException eof) {
			System.out.println("Server terminated connection");
		} catch (IOException e) {
			System.out.println("N�o consegui conectar!");
			e.printStackTrace();
		}
	}

	private void sendData(String s) {
		try {
			message = s;
			output.writeObject(" " + s);
			output.flush();
			display.append("\nCLIENT>>>" + s);
		} catch (IOException cnfex) {
			display.append("\nError writing object");
		}
	}

	public static void main(String args[]) {
		ClienteSocket2 app = new ClienteSocket2();

		app.runClient();
	}
}
