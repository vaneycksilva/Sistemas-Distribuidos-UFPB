package chatTeste2;



import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;




public class Servidor {



	public static int DEFAULT_PORT = 9999;

	public static void main(String args[]) {
		int serverPort = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_PORT;
		try {
			ServerSocket serverSocket = new ServerSocket(serverPort);
			while (true) {
				System.out.println("Aguardando conexao no endereco: "
						+ InetAddress.getLocalHost().getHostAddress() + ":" + serverPort);
				Socket clientSocket = serverSocket.accept();
				Connection connection = new Connection(clientSocket);
				System.out.println("Conexao feita com: " + clientSocket.getInetAddress() + 
						":" + clientSocket.getPort());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Connection extends Thread {
	private DataInputStream in;
	private DataOutputStream out;
	private Socket clientSocket;
	private Scanner sc;
	
	public Connection(Socket aClientSocket) {
		
		try {
			
			sc = new Scanner(System.in);
			clientSocket = aClientSocket;
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			this.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String currentTime() {
		return "[" + System.currentTimeMillis() + "]";
	}

	
	public void run() {
		
		
		
		try {
			
			
			while (true) {
				
				// recebe o texto
				String texto = in.readUTF();
				
				
				//envia o texto de volta
				out.writeUTF(clientSocket.getInetAddress()+" disse "+texto);
				
			}
			
			
			
			/*// 1: Recebe mensagem com nome dos alunos
			String msg1 = in.readUTF();
			System.out.println(currentTime() + " Mensagem recebida de " + clientSocket.getInetAddress() + ": " + msg1);

			// 2: Envia mensagem com codigo de verificacao
			int random4digits = 1000 + (int)(Math.random()*9999);
			out.writeUTF("Retorne mensagem com o codigo de verificacao: " + random4digits);
			System.out.println(currentTime() + " Enviado para " + clientSocket.getInetAddress() + " o codigo: " + random4digits);
*/
			
		} catch (EOFException e) {
			System.err.println("Acesso invalido de: " + clientSocket.getInetAddress());
		} catch (IOException e) {
			System.err.println("Acesso invalido de: " + clientSocket.getInetAddress());
		} 
	}
}
