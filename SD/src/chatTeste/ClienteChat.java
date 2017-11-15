package chatTeste;

import java.io.*;
import java.net.*;


public class ClienteChat extends Thread{
	
	private Socket cliente;
	
	public ClienteChat(Socket c) {
		
		this.cliente = c;
		
	}

	public static void main(String[] args) throws IOException {
		
		try {
		
			//estabele conexão com o servidor
			//meu endereço = 10.0.0.112
			Socket clienteSocket = new Socket("localhost", 9999);
			
			PrintStream saida = new PrintStream(clienteSocket.getOutputStream());
			
			BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
				
			// thread aqui
			Thread thread = new ClienteChat(clienteSocket);
			thread.start();
			
			//envia o texto para o servidor
			String texto;
			while (true) {
				
				System.out.print("Digite uma msg: ");
				// pega do usuário
				texto = entrada.readLine();
				if (texto.equalsIgnoreCase("sair")) {
					clienteSocket.close();
					break;
				}
				//modificado = entrada.readLine();
				
				
				//System.out.println("Você disse: "+modificado);
				// envia de volta para o servidor
				saida.println(texto);
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
		}
		

		/////////////////////////////////////////////////////
		
		
	}
	
	
	// executa a thread do cliente
	public void run() {
		
		try {
			
			//recebe mensagens de outros clientes através do servidor
			BufferedReader entrada = new BufferedReader(new InputStreamReader(this.cliente.getInputStream()));
			PrintStream saida = new PrintStream(this.cliente.getOutputStream());
			
			
			String texto;
		
			while (true) {
				
				//System.out.println("entrei");
				// pega do usuário
				texto = entrada.readLine();
				if (texto.equalsIgnoreCase("sair")) {
					break;
				}
				//modificado = entrada.readLine();
				
				//System.out.println(clienteSocket.getInetAddress()+" disse: ");
				// envia de volta para o servidor
				saida.println(texto);
				
			}
			
			System.out.println(cliente.getInetAddress()+"...saiu");
		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
} // fim da classe cliente