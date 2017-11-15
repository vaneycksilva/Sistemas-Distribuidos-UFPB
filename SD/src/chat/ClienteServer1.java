package chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteServer1 {

	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		// cria conexão com o servidor
		Socket clienteSocket = new Socket("localhost", 9999);
		
		//ler o que o cliente digitar
		BufferedReader entrada = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
		
		
		DataOutputStream saida = new DataOutputStream(clienteSocket.getOutputStream());
		
		
		String texto = entrada.readLine();
		
		saida.writeUTF(texto);
		
		
		clienteSocket.close();
		

	}

}
