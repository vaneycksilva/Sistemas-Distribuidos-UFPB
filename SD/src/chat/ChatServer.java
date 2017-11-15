package chat;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {


	private static int porta = 9999; 
			
			
	public static void main(String[] args) throws IOException {
		
		// estabelece o canal de comunicação
		ServerSocket servidorSocket = new ServerSocket(porta);
		
		while (true) {
			
			System.out.println("############## BATE PAPO BOL #############");
			
			// espera a conexão de algum cliente
			Socket clienteSocket = servidorSocket.accept();
			
			System.out.println(clienteSocket.getInetAddress()+" acabou de entrar!!");
			
			// apenas imprimi o que o usuário digitou
			PrintStream saidaServidor = new PrintStream(clienteSocket.getOutputStream());
			
			System.out.println(clienteSocket.getInetAddress()+" disse...");
			saidaServidor.println();
			
			clienteSocket.close();
		}
		
		

	}

}
