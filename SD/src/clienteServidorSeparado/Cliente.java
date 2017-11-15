package clienteServidorSeparado;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;


/*          Métodos utilizados
 * 
 * 
 * Socket() - é um método construtor que cria um socket de fluxo e
 * conecta-o a um endereço IP e a um número de porta especificado
 * 
 * 
 * getInputStream() - obtém um fluxo de entrada originado do servidor
 * através do socket
 * 
 * 
 * getOutputStream() - obtém um fluxo de saída que será enviado para
 * o servidor a partir do socket
 * 
 * 
 * DataOutputStream() - cria um canal de comunicação no sentido cliente-servidor
 * 
 * DataInputStream() - cria um canal de comunicação no sentido servidor-cliente
 * 
 * 
 * writeBytes() - envia o fluxo de dados no canal de comunicação através da porta
 * para o servidor.
 * 
 * 
 * 
 * */

public class Cliente implements Runnable{

	
	public void run() {
	
		String msg;
		String msgModificada;
		
		
		try {
			
			
			// cria o stream do teclado
			BufferedReader lerDoUsuario = new BufferedReader(new InputStreamReader(System.in));
			
			// cria o socket de acesso
			Socket clienteSocket = new Socket("localhost", 6789);
			
			// cria os streams de entrada e saída com o servidor
			DataOutputStream clienteParaServidor = new DataOutputStream(clienteSocket.getOutputStream());
			
			BufferedReader saiDoServidor = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
			
			// ler do teclado
			msg = lerDoUsuario.readLine();
			
			// envia a linha para o servidor
			clienteParaServidor.writeBytes(msg+"\n");
			
			//ler uma linha do server
			msgModificada = saiDoServidor.readLine();
			
			
			// 
			System.out.println("O que chegou no servior "+ msgModificada);
			
			clienteSocket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
	

}
