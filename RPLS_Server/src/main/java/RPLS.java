import java.awt.Font;
import java.io.*;
import java.net.*;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.*;
import javafx.stage.Stage;


@SuppressWarnings("restriction")
public class RPLS extends Application 
{
	
	Scene infoScene;
	Scene startScene;
	int playerCount = 0; 
	public String portNum; 
	public int portInt;
	public ServerLogic serverSocket;
	public InputStream input = null;
	public OutputStream output = null;
    public Thread newClient = null;
    public int player1Score;
    public int player2Score;
    Label gameInfo;
    Label playOrQuit;
    Label numClients;
    Button playAgainButton;
    Button quitButton;
    HBox gameInfoSummary;
    HBox playAgain;
    HBox numClientsHBox;
    GameInfo gi= new GameInfo();
    ListView<String> displayPlayerOneResults;
    ListView<String> displayPlayerTwoResults;
    
//    public static ArrayList<GameInfo> clientList = new ArrayList<GameInfo>();
	//public static ServerLogic server = new ServerLogic();

	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		// TODO Auto-generated method stub
		//Main - Server		
		launch(args);
	}

	//feel free to remove the starter code from this method
	@Override
	public void start(Stage primaryStage) throws Exception
	{
		// TODO Auto-generated method stub

		//GUI aspect of program
	    primaryStage.setTitle("RPLS - Server!!!");
	    
	    TextField portInput = new TextField();
	    Button startButton = new Button("Start");
	    Label portLabel = new Label("Enter Port Number");
	    
	    startButton.setDisable(false);
	    
	    VBox beginningLayout = new VBox(20, portLabel, portInput, startButton);
	    
	    beginningLayout.setPadding(new Insets(400, 380, 500, 370));
	    beginningLayout.setFillWidth(true);
	    beginningLayout.setAlignment(Pos.CENTER);
	    
	    startButton.setOnAction(e -> {
	    	try
	    	{
	    		portNum = portInput.getText();
	    		portInt = Integer.parseInt(portNum);
	    		portInput.clear();
	    		startButton.setDisable(true);
	    		
	    		serverSocket  = new ServerLogic(data -> {
	    			Platform.runLater(()-> {
	    				gi = (GameInfo) data;
	    				if(gi.playerNumber == 1) {
	    					displayPlayerOneResults.getItems().add(gi.messg);
	    				
	    						displayPlayerOneResults.getItems().add("player 1 played: " + gi.playerPlays);
	    					
	    				}
	    				if(gi.playerNumber == 2) {
	    					displayPlayerTwoResults.getItems().add(gi.messg);
	    					
	    						displayPlayerTwoResults.getItems().add("player 2 played: " + gi.playerPlays);
	    					
	    				}
	    				
	    				//numClients.setText("Number Of Clients: " + (serverSocket.getNumClients()));
	    			});
	    		});
	    		//serverSocket = server.setConnection(portInt);
	    		System.out.println("Actual Port: " + portNum);
	    		System.out.println("Server Port: " + serverSocket);
	    		
	    		//server.getNewClient(input, output, serverSocket, newClient);
	    		
	    		primaryStage.setScene(infoScene);
	    	}
	    	catch (Exception e1)
	    	{
	    		System.out.println("Port Number Not An Integer");
	    		portInput.clear();
	    		startButton.setDisable(false);
	    	}
		});
	    
	    
	    playerCount = 0;
	    gameInfo = new Label("Game Summary");
	    playOrQuit = new Label("Play Again Or Quit?");
	    numClients = new Label("Number Of Clients: " + playerCount);
	    playAgainButton = new Button("Play Again");
	    quitButton = new Button("Quit");
	    gameInfoSummary = new HBox(gameInfo);
	    playAgain = new HBox(10, playOrQuit, playAgainButton, quitButton);
	    numClientsHBox = new HBox(numClients);
	    displayPlayerOneResults = new ListView<String>();
	    displayPlayerTwoResults = new ListView<String>();
	    Group root = new Group();
	    
	    root.getChildren().addAll(gameInfoSummary, numClientsHBox);
	    root.getChildren().get(0).setLayoutX(400);
	    root.getChildren().get(1).setLayoutY(50);
	    
	    displayPlayerOneResults.getItems().addAll("Player 1 Score:");
	    displayPlayerTwoResults.getItems().addAll("Player 2 Score:");
	    
	    root.getChildren().addAll(displayPlayerOneResults, displayPlayerTwoResults, playAgain);
	    root.getChildren().get(2).setLayoutY(120);
	    root.getChildren().get(3).setLayoutX(400);
	    root.getChildren().get(3).setLayoutY(120);
	    root.getChildren().get(4).setLayoutX(550);
	    root.getChildren().get(4).setLayoutY(800);
	    
	    playAgainButton.setOnAction(e -> {			
	    	RPLS restartApp = new RPLS();
			
			try 
			{
				restartApp.start(primaryStage);
			} 
			catch (Exception e1) 
			{
				System.out.println("Couldn't Restart Game...");
			}
		});
	    
	    quitButton.setOnAction(e -> {			
	    	primaryStage.close();
		});
	    
		infoScene = new Scene(root, 900, 900);
	    startScene = new Scene(beginningLayout, 900, 900);
		primaryStage.setScene(startScene);
		primaryStage.show();
	}

}
