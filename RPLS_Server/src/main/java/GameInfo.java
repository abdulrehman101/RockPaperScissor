import java.io.Serializable;


public class GameInfo implements Serializable
{
	 private static final long serialVersionUID = 1L;
	 int playerNumber;
	 int p1Points;
	 int p2Points; 
	 String playerPlays; 
	 String opponentPlays;
	 boolean have2players;
	 boolean played;
	 int win;
	 boolean bothPlayersPlayed;
	 boolean gameStarted;
	 String messg;
	 
	 GameInfo() {
		 playerNumber = 0;
		 p1Points = 0;
		 p2Points = 0; 
		 playerPlays= ""; 
		 opponentPlays= "";
		 have2players = false;
		 win = 0;
		 messg = "";
		 bothPlayersPlayed = false;
		 gameStarted = false;
		 played = false;
		 System.out.println("Player 1 Points: " + p1Points);
		 System.out.println("Player 2 Points: " + p2Points);
		 System.out.println("Player 1 Plays: " + playerPlays);
		 System.out.println("Player 2 Plays: " + opponentPlays);
		 System.out.println("2 Players (Y or N): " + have2players);
	 }
	 public void setMessage(String message) {
		 this.messg = message;
	 }
}