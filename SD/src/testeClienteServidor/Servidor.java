package testeClienteServidor;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {

	
	public static void main(String[] args) throws Exception {
		
		System.out.println("Iniciando Servidor...");
		
		int porta = 1234;
		
		ServerSocket servidor = new ServerSocket(porta);
		
		//servidor recebendo conexões
		Socket socket = servidor.accept(); // socket é um canal de comunicação
		
		System.out.println("Conexão estabelecida");
		
		InputStream entrada = socket.getInputStream();
		OutputStream saida = socket.getOutputStream();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(entrada));
		PrintStream out = new PrintStream(saida);
		
		servidor.close(); // tem q fechar
	}

}
