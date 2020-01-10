import java.io.*;
import java.net.*;
import java.util.ArrayList;
//import java.util.List;
import java.util.function.Consumer;


public class ServerLogic 
{
	int count = 1;
	
	// all the client thread
	ArrayList<ClientThread> clients = new ArrayList<ClientThread>();
	ArrayList<GameInfo> gameInfoObjects = new ArrayList<GameInfo>();
	TheServer server;
	private Consumer<Serializable> callback;
	GameInfo gInfo;
	String playerOnePlayed;
	String playerTwoPlayed;
	
	// server constructor
	ServerLogic(Consumer<Serializable> call){
		callback = call;
		server = new TheServer();
		server.start();
	}
	
	public class TheServer extends Thread {
		public void run() {
			try(ServerSocket mysocket = new ServerSocket(5555);){
				
				while(true) {
					ClientThread c = new ClientThread(mysocket.accept(), count); 
					//callback.accept("client has connected to server: " + "client #" + count);
					clients.add(c);
					c.start();
					
					gInfo = new GameInfo();
					gInfo.playerNumber = count;
					gInfo.messg = "Client num: " + count + " got connected to the server";
					gameInfoObjects.add(gInfo);
					callback.accept(gInfo);
					count++;
				}
			}
			catch(Exception e) {
				callback.accept("Server did not launch");
			}
		}
	}
	public int getNumClients()
	{
		return clients.size();
	}
	class ClientThread extends Thread {
		
		Socket connection;
		int count;
		ObjectInputStream in;
		ObjectOutputStream out;
		
		
		ClientThread(Socket s, int count) {
			this.connection = s;
			this.count = count;
		}
		public void updateClients(GameInfo gi) {
			for(int i = 0; i < clients.size(); i++) {
				ClientThread t = clients.get(i);
				try {
					System.out.println("game info object send has player number: " + gi.playerNumber + "\n");
					t.out.writeObject(gi);
				}
				catch(Exception e) {}
			}
		}
		
		public void updateClients() {
			for(int i = 0; i < clients.size(); i++) {
				ClientThread t = clients.get(i);
				try {
					if(t.count == 1) {
						gInfo = gameInfoObjects.get(0);
						System.out.println("t.count: " + t.count + "\n");
						System.out.println("game info object send has player number: " + gameInfoObjects.get(0).playerNumber + "\n");
						t.out.writeObject(gInfo);
					}
					else if (t.count == 2){
						gInfo = gameInfoObjects.get(1);
						System.out.println("t.count: " + t.count + "\n");
						System.out.println("game info object send has player number: " + gameInfoObjects.get(1).playerNumber + "\n"); // aor ye bhi print hue tha
						t.out.writeObject(gInfo);
					}
				}
				catch(Exception e) {}
			}
		}
		// tcp ka kuch karna han na // tcpNoDelay jaissay shahrukh k code me ta
		public void run() {
			
			try {
				in = new ObjectInputStream(connection.getInputStream());
				out = new ObjectOutputStream(connection.getOutputStream());
				connection.setTcpNoDelay(true);	
			}
			catch(Exception e) {
				System.out.println("Streams not open");
			}
			if(clients.size() == 2) {
				gameInfoObjects.get(0).have2players = true;
				gameInfoObjects.get(1).have2players = true;
				updateClients();
			}
			while (true) {
				try 
				{	
					// server reads in the Game_Info object from first client
					// ok umer bhai I think we are back in the game // ohh good
					gInfo = (GameInfo)in.readObject();
					if(count == 1) {
						gameInfoObjects.add(0, gInfo);
					}
					else {
						gameInfoObjects.add(1, gInfo);
					}
					while(true) {
						if (gameInfoObjects.get(0).played && gameInfoObjects.get(1).played) {
							
							callback.accept(gameInfoObjects.get(count - 1));
							gameInfoObjects.get(0).opponentPlays = gameInfoObjects.get(1).playerPlays;
							gameInfoObjects.get(1).opponentPlays = gameInfoObjects.get(0).playerPlays;
							gameInfoObjects.get(0).played = false; 
							gameInfoObjects.get(1).played = false; 
							break;
						}
					}
					// sending the information back to both clients
					
					updateClients();
				} 
				catch (ClassNotFoundException | IOException e) {
					gInfo.setMessage("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
					gInfo.playerNumber = count;
					callback.accept(gInfo);
					// TODO Auto-generated catch block
					clients.remove(this);
					break;
				} 
			}
		}
	}
}
	