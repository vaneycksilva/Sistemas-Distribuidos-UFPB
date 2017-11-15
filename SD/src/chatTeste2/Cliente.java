package chatTeste2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;



public class Cliente extends Thread{


		private Socket cliente;
		
		public Cliente(Socket c) {
			
			this.cliente = c;
			
		}

		public static void main(String[] args) throws IOException {
			
			try {
			
				//estabele conexão com o servidor
				//meu endereço = 10.0.0.112
				Socket clienteSocket = new Socket("localhost", 9999);
				
				PrintStream saida = new PrintStream(clienteSocket.getOutputStream());
				
				BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
				
				//não tinha isso
				DataInputStream in = new DataInputStream(clienteSocket.getInputStream());
					
				// thread aqui
				Thread thread = new Cliente(clienteSocket);
				thread.start();
				
				//envia o texto para o servidor
				
				String texto,recebida = "";
				while (true) {
					
					System.out.println("Digite uma msg: ");
					// pega do usuário
					texto = entrada.readLine();
					
					// o que volta do servidor
					recebida = in.readUTF();
					System.out.println(clienteSocket.getInetAddress()+" disse "+recebida);
					//System.out.println("Você disse: "+texto);
					// envia de volta para o servidor
					saida.println(recebida);
					
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				System.err.println(e);
			}
		}
		
		
		
		// executa a thread do cliente
		public void run() {
			
			try {
				
				//recebe mensagens de outros clientes através do servidor
				BufferedReader entrada = new BufferedReader(new InputStreamReader(this.cliente.getInputStream()));
				PrintStream saida = new PrintStream(this.cliente.getOutputStream());
				//não tinha isso
				DataInputStream in = new DataInputStream(cliente.getInputStream());
				
				String texto,recebida;
			
				while (true) {
					
					System.out.println("entrei");
					// pega do usuário
					texto = entrada.readLine();
					
					// o que volta do servidor
					recebida = in.readUTF();
					System.out.println(cliente.getInetAddress()+" disse "+recebida);
					
					
					//System.out.println(clienteSocket.getInetAddress()+" disse: ");
					// envia de volta para o servidor
					saida.println(texto);
					
				}
				
				/**
				 * 
				 * TESTE
				 * 
				 * */
				
				/*texto = entrada.readLine();
				
				
				while (texto !=null ) {
					
					saida.println(cliente.getInetAddress()+ " disse da thread: "+texto);
					
					texto = entrada.readLine();
				}*/
				
				/*while (true) {
					
					texto = entrada.readLine();
					
					//imprime o texto recebido
					//System.out.println(texto);
					System.out.println("Responder..."+texto);
				}*/
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}

}
