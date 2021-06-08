package p3SocketsMc;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MultiClient extends Thread {
    private Socket client;
    private Scanner in;
    private PrintWriter out;   
    private Integer random;
    private Integer idC;
    

    public MultiClient(Socket client, Integer random, Integer idC) {
        try {
            this.client = client;
            this.in = new Scanner(client.getInputStream());
            this.out = new PrintWriter(client.getOutputStream(), true);
            this.random = random;
            this.idC = idC;
        } catch (IOException ex) {
            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }         
    }
    
    @Override
    public void run() {
        String msg;
        try {
        	out.println("Conectado al Server. Cliente: " +idC +" finalice con EXIT. Adivine un numero del 1 al 100");
            while(in.hasNextLine()) {
                msg = in.nextLine();
                int numero= Integer.parseInt(msg);		
        		System.out.println("Cliente: "+ idC);
                System.out.println("Intento con el numero: " + numero);
                if(msg.contains("EXIT")) {
                    break;
                } else {
                	if(numero == random) {
                		out.println("Ok. El numero :"+ numero +" acerto");
                	}else if (numero > random) {
                		out.println("El numero :"+ numero +" es muy ALTO");
                	}else {
                		out.println("El numero :"+ numero +" es muy BAJO");
                	}
                }
            }
            client.close();
        } catch (IOException ex) {
            Logger.getLogger(MultiClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
