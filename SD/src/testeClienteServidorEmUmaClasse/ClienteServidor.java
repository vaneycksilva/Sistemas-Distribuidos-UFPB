package testeClienteServidorEmUmaClasse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteServidor {

	private final static int porta = 2000;
	
	public static class Cliente implements Runnable{
		
		public void run() {
			
			//int porta = 4000;
										
			try {
											// ip        porta
				Socket cliente = new Socket("localhost", porta);
				// socket cria o canal
				
				//Scanner entrada = new Scanner(cliente.getInputStream());
				
				//System.out.println("Mensagem "+ entrada.nextLine());
				
				/*
				PrintWriter armazena = new PrintWriter(cliente.getOutputStream());
				armazena.println("msg do cliente");
				armazena.flush();
				cliente.close();//*/
				
				Scanner sc = new Scanner(System.in);
				
				while (true) {
					
					System.out.print("Cliente manda uma msg: ");
					String mensagem = sc.nextLine();
					
					if ("SAIR".equals(mensagem)) {
						break;
					}
					
					System.out.println("\nMensagem recebida do servidor: "+ mensagem.toUpperCase()+"\n");
				}
				
				System.out.println("\nConexão com o servidor encerrado!!");
				
				//cliente.close(); // se botar esse da pau
				sc.close();
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static class Servidor implements Runnable{
		
		public void run() {
		
			//int porta = 4000;
			
			System.out.println("Iniciando Servidor...\n");
			
			try {
				ServerSocket serverSocket = new ServerSocket(porta);
				
				while(true){
					
					Socket servidor = serverSocket.accept(); // abre um canal de comunicação
					
					Scanner entrada = new Scanner(servidor.getInputStream());
					
					entrada.nextLine().toUpperCase();
					//System.out.println("Mensagem "+ entrada.nextLine().toUpperCase());
					
					/*if (entrada.equals("FIM")) {
						entrada.close();
						System.out.println("Conexão com o servidor encerrada!!");
						break;*/
					//}
					entrada.close();
					serverSocket.close();
				}
		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// iniciando a thread do servidor
		Servidor servidor = new Servidor();
		Thread threadServer = new Thread(servidor);
		threadServer.start();
		
		
		// iniciando a thread do cliente
		Cliente cliente = new Cliente();
		Thread threadClient = new Thread(cliente);
		threadClient.start();
		
	}

}
