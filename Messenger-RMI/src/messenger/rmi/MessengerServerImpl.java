package messenger.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MessengerServerImpl implements MessengerServer {

	
	public HashMap<String, MessengerClient> getUsers() {
		return users;
	}

	public void setUsers(HashMap<String, MessengerClient> users) {
		this.users = users;
	}

	private HashMap<String, MessengerClient> users;

	public MessengerServerImpl() {
		
		this.users = new HashMap<String, MessengerClient>();
	}

	public void login(MessengerClient client, String userName)throws RemoteException {
		
		System.out.println("Login: " + userName);
		         //nome     referência
		users.put(userName, client);
		
		/*
		 * notificação de usuário online
		 * */
		for (String user : users.keySet()) {
			
			client = users.get(user);
			client.usuariosOnline(userName);
		}
		
	}

	
	public boolean sendMsg(String from, String to, String msg)throws RemoteException {
		
		System.out.println("Sending msg from " + from + " to " + to);
		MessengerClient toClient = users.get(to);
		if (toClient == null) {
			return false;
		}
		toClient.receiveMsg(from, msg);
		return true;
	}
	
	public String listUsers() throws RemoteException {
		
		System.out.println("Listing logged users.");
		return users.keySet().toString();
	}

	
	public void logout(String userName) throws RemoteException {
		
		System.out.println("Logout: " + userName);
		users.remove(userName);
	}
	
	/*
	 * envia para todo mundo
	 */
	public void broadcast(String from,String primeira, String msg) throws RemoteException {
		
		MessengerClient cliente;
		
		System.out.println(from+" enviando para todo mundo");
		
		// enviar para todos os que estiverem no mapa
		for (String user : users.keySet()) {
			
			cliente = users.get(user);
			cliente.receiveMsg(from, primeira+" "+msg);
									// gambiarra
	
		}
		
	}

	public static void main(String[] args) {
		try {
			String host = "127.0.0.1";
			LocateRegistry.createRegistry(1099);
			MessengerServerImpl obj = new MessengerServerImpl();
			MessengerServer stub = (MessengerServer) UnicastRemoteObject
					.exportObject(obj, 0);
			// Bind the remote object's stub in the registry
			Registry registry = LocateRegistry.getRegistry(host);
			registry.rebind("MessengerServer", stub);

			System.err.println("Server is running...");
		} catch (Exception e) {
			System.err.println("Server exception: " + e.toString());
			e.printStackTrace();
		}
	}
}