package pratica1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente2 {

	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		String msgRecebida;
		
		Socket clienteSocket = new Socket("localhost", 9999);
		
		// streams de entrada
		DataInputStream streamsEntrada = new DataInputStream(clienteSocket.getInputStream());
		
		// streams de saída
		DataOutputStream streamsSaida = new DataOutputStream(clienteSocket.getOutputStream());
		
		streamsSaida.writeUTF("Van Eyck");
		
		msgRecebida = streamsEntrada.readUTF();
		
		//String codigo = msgRecebida.substring(46, 50);
		
		//streamsSaida.writeUTF(codigo);
		
		clienteSocket.close();
	}
	
	
}
