package clienteServidorSeparado;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable {

	
	public void run() {
		
		String msg;
		String msgCapturada;
		
			try {
				
				// cria um canal de comunicação
				ServerSocket servidor = new ServerSocket(6789);
				
				while (true) {
					
					// espera conexao de algum cliente
					Socket conexao = servidor.accept();
					
					// cria streams de entrada e saída com o cliente
					
					BufferedReader cliente = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
					
					DataOutputStream servidorCliente = new DataOutputStream(conexao.getOutputStream());
					
					// lê a linha do cliente
					msg = cliente.readLine();
					
					// transforma a linha em maiúsculo
					msgCapturada = msg.toUpperCase() + "\n";
					
					// envia para o cliente
					servidorCliente.writeBytes(msgCapturada);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
