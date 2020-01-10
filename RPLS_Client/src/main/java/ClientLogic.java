import java.io.*;
import java.net.*;
import java.util.*;
import java.util.function.Consumer;

public class ClientLogic extends Thread{

	
	Socket socket;
	
	InputStream input;
	OutputStream output;
	
	ObjectInputStream in;
	ObjectOutputStream out;
	
	GameInfo gInfo = new GameInfo();
	int num;
	int port;
	String IPstring;
	private Consumer<Serializable> callback;
	
	ClientLogic(int port, String IPstring,Consumer<Serializable> call){
		this.port = port;
		this.IPstring = IPstring;
		callback = call;
	}

	public void run() {
		try {
			 socket = new Socket("127.0.0.1", 5555);
			 out = new ObjectOutputStream(socket.getOutputStream());
			 in = new ObjectInputStream(socket.getInputStream());
		}
		catch (Exception e) {}
		while(true) {
			try {
				gInfo = (GameInfo)in.readObject();
				callback.accept(gInfo);
			}
			catch(Exception e) {}
		}
	}
	public void send(GameInfo GI) {
		try {			
			System.out.println("In send method " + GI.p1Points);
			this.gInfo = GI;
			out.writeObject(gInfo);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
