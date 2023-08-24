package exemplo2;

import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ServidorSocket2 extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2658606029167891808L;
	private JTextField enter, numero;
	private JTextArea display;
	private JPanel painelNorte;
	private ServidorSocketThread2 clients[];
	static final int maxClients = 10;
	static int nClient = 0;

	public ServidorSocket2() {
		super("Server");

		clients = new ServidorSocketThread2[maxClients];

		Container c = getContentPane();

		enter = new JTextField();
		enter.setEnabled(true);
		numero = new JTextField(5);
		numero.setEnabled(true);
		painelNorte = new JPanel();
		painelNorte.setLayout(new BorderLayout());
		painelNorte.add(numero, BorderLayout.EAST);
		painelNorte.add(enter, BorderLayout.CENTER);

		enter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendData(e.getActionCommand());
			}
		});
		c.add(painelNorte, BorderLayout.NORTH);

		display = new JTextArea();
		c.add(new JScrollPane(display), BorderLayout.CENTER);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 350);
		setVisible(true);
	}

	public void runServer() {
		ServerSocket server;
		Socket connection;

		try {
			// Step 1: Create a ServerSocket.
			server = new ServerSocket(4000, 100);

			while (true) {
				// Step 2: Wait for a connection.
				display.append("\nWaiting for connection\n");
				connection = server.accept();
				clients[nClient] = new ServidorSocketThread2(connection, display, nClient);
				clients[nClient].start();
				nClient++;

			}
		} catch (EOFException eof) {
			System.out.println("Client terminated connection");
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	private void sendData(String s) {
		int n = 0;
		try {
			if (numero.getText() == null || numero.getText().equals("")) {
				JOptionPane.showMessageDialog(
					null,
					"Digite um número de cliente", 
					"Digite um número de cliente",
					 n,
					  null);
				return;
			}
			n = Integer.parseInt(numero.getText());
			if (n > -1 && n < nClient) {
				clients[n].getOutput().writeObject("SERVER>>> " + s);
				clients[n].getOutput().flush();
			} else {
				for (int i = 0; i < nClient; i++) {
					//				System.out.println(""+clients[i].getOutput());
					if (clients[i].getAtiva()) {
						clients[i].getOutput().writeObject("SERVER>>> " + s);
						clients[i].getOutput().flush();
					}
				}
				display.append("\nSERVER>>>" + s);
			}
		} catch (IOException cnfex) {
			display.append("\nError writing object");
		}
	}

	public static void main(String args[]) {
		ServidorSocket2 app = new ServidorSocket2();
		app.runServer();
	}
}