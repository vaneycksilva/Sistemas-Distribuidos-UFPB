package chatTeste;

import java.io.*;
import java.net.*;

public class ServidorChat extends Thread {

	private static int porta = 9999;
	private Socket conexao;
	
	public ServidorChat(Socket c) {
		// TODO Auto-generated constructor stub
		this.conexao = c;
	}
	
	public static void main(String args[]) throws Exception{
		
		try {
			
			//cria o socket
			ServerSocket servidor = new ServerSocket(porta);
			
			while (true) {
				
				System.out.println("########Chat###########");
				
				// espera um cliente se conectar
				Socket cliente = servidor.accept();
				
				//System.out.println("conexão feita com: "+cliente.getInetAddress());
				System.out.println("\n"+cliente.getInetAddress()+" entrou...\n");
				//cria um thread para cada cliente
				Thread thread = new ServidorChat(cliente);
				thread.start();
			}
			
	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Conexão encerrada "+e.getMessage());
		}
		System.out.println("Conexão cancelada");
		
		
	} // fim do main
	
	
	//execução da thread do servidor
	public void run() {
		
		try {
			
			InputStreamReader input = new InputStreamReader(this.conexao.getInputStream());
			
			//entrada do cliente
			BufferedReader entrada = new BufferedReader(input);
			
			//DataOutputStream saida2 = new DataOutputStream(this.conexao.getOutputStream());
			
			//saida do cliente
			PrintStream saida = new PrintStream(this.conexao.getOutputStream());
			
			// WHILE AQUI
		
			String texto = entrada.readLine();
			
			while (true) {
				
				if (texto.isEmpty() || texto.equals(null)) {
					this.conexao.close();
					break;
				}
				
				// aparece o que o cliente digita no servidor
				System.out.println(this.conexao.getInetAddress()+" escreveu... "+texto);
				
				//era pra ser esse
				//saida.println(this.conexao.getInetAddress()+" escreveu... "+texto);
			
				//saida2.writeUTF(this.conexao.getInetAddress()+" escreveu... "+texto);
				
				texto = entrada.readLine();
			}
			System.out.println(this.conexao.getInetAddress()+"...saiu");
			//envia de volta
			//saida.println(conexao.getInetAddress()+" disse :"+texto);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
}







