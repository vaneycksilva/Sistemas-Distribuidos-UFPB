package pratica1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class MessageClient {
	
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		String msgRecebida;
		
		String msg = "Van Eyck";
		
		// 10.0.254.11 servidor do prof
		Socket clienteSocket = new Socket("localhost", 9999);
		
		// cria os streams de entrada e saída com o servidor
		DataOutputStream clienteParaServidorSaida = new DataOutputStream(clienteSocket.getOutputStream());
		DataInputStream clienteParaServidorEntrada = new DataInputStream(clienteSocket.getInputStream());
		
		// ENVIA A MSG
		clienteParaServidorSaida.writeUTF(msg);
		
		// recebe uma msg do servidor
		msgRecebida = clienteParaServidorEntrada.readUTF();
		
		// pega apenas o código, e o código pode ter 4 ou 5 dígitos
		String codigo = msgRecebida.substring(msgRecebida.length()-5, msgRecebida.length());
		
	
		
		clienteParaServidorSaida.writeUTF(codigo);
			
		clienteSocket.close();
		
	}
}
