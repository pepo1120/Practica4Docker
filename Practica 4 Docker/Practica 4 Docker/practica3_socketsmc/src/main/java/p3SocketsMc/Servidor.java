package p3SocketsMc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {

	public static void main(String[] args) {
		try {
            ServerSocket server  = new ServerSocket(8888);
            while(true) {
            	int x = (int) (Math.random() * 100 + 1);
            	int idC = (int) (Math.random() * 1000 + 1);
                System.out.println("Esperando cliente...");
                Socket socket = server.accept();
                System.out.println("Cliente conectado...");
                new MultiClient(socket, x, idC).start();
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}

}
