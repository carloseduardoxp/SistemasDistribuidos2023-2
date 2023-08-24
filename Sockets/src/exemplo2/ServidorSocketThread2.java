package exemplo2;

import java.net.*;
import javax.swing.*;
import java.io.*;

public class ServidorSocketThread2 extends Thread {

	private Socket con;
	private JTextArea display;
	private ObjectOutputStream output;
    private ObjectInputStream input;
	private int counter = 0;
	private String message = "";
	private boolean ativa = false;

	
	// Start methods Construction
	public ServidorSocketThread2(Socket connection, JTextArea disp, int counter) {
		this.con = connection;
		this.display = disp;
		this.counter = counter;

		ativa = true;

		display.append( "\ncon " + counter +
               " received from: " +
        con.getInetAddress().getHostName() );
		System.out.println( "con " + counter +
               " received from: " +
        con.getInetAddress().getHostName() );
		System.out.println(""+con.getInetAddress());

        try {
			// Step 3: Get input and output streams.
			output = new ObjectOutputStream(
                       con.getOutputStream() );
			output.flush();
			input = new ObjectInputStream(
                       con.getInputStream() );
			display.append( "\nGot I/O streams\n" );
 
	        // Step 4: Process con.
	        String message =
	           "SERVER>>> con successful";
	        output.writeObject( message );
	        output.flush();
		} catch ( EOFException eof ) {
            System.out.println( "Client terminated connection" );
        } catch ( IOException io ) {
            io.printStackTrace();
        }
	}

	public ObjectOutputStream getOutput() {
		return output;
	}

	public boolean getAtiva() {
		return ativa;
	}

    @Override
	public void run() {
        try {
		
			do {
				try {
					  message = (String) input.readObject();
				      display.append( "\nCLIENT "+counter+" >>>" + message );
				      display.setCaretPosition(
                      display.getText().length() );
				}
				catch ( ClassNotFoundException cnfex ) {
					  display.append(
						 "\nUnknown object type received" );
				}
			} while ( !message.equals( " TERMINATE" ) );

			// Step 5: Close con.
			display.append( "\nUser terminated con" );

			ativa = false;
			output.close();
			input.close();
			con.close();
		}
        catch ( IOException io ) {
            io.printStackTrace();
        }

	}//fim do m�todo run
}
