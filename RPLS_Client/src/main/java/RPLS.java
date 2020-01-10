import java.util.*;
import java.util.function.Consumer;
import java.awt.Font;
import java.io.*;
import java.net.*;
import java.util.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.stage.Stage;


public class RPLS extends Application {

	Scene startScene;
	Scene secondScene;
	Scene thirdScene;
	Scene fourthScene;
	ListView<String> displayMessages = new ListView<String>();
	String opponentPlayed;
	public String portNum;
	public String addressString;
	public int portInt;
	public int addressInt;
	public ClientLogic client;
	public GameInfo gameInfo = new GameInfo();
//	public static ArrayList<>
	ImageView rockPic;
	ImageView paperPic;
	ImageView scissorsPic;
	ImageView lizardPic;
	ImageView spockPic;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//Main - Client	
		launch(args);
	}

	//feel free to remove the starter code from this method
	@SuppressWarnings("restriction")
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		
		/*client = new ClientLogic (data->{
			Platform.runLater(()->{});
		});
		client.start();*/
		
		primaryStage.setTitle("RPLS - Client!!!");
		
	    Image rock = new Image("Rock.jpg");
		rockPic = new  ImageView(rock);
		rockPic.setFitHeight(200);
		rockPic.setFitWidth(220);
		rockPic.setX(0);
		rockPic.setVisible(false);
		
		Image paper = new Image("Paper.jpg");
		paperPic = new  ImageView(paper);
		paperPic.setFitHeight(200);
		paperPic.setFitWidth(220);
		paperPic.setX(0);
		paperPic.setVisible(false);

		Image scissors = new Image("Scissors.jpg");
		scissorsPic = new  ImageView(scissors);
		scissorsPic.setFitHeight(200);
		scissorsPic.setFitWidth(220);
		scissorsPic.setX(0);
		scissorsPic.setVisible(false);

		
		Image lizard = new Image("Lizard.jpg");
		lizardPic = new  ImageView(lizard);
		lizardPic.setFitHeight(200);
		lizardPic.setFitWidth(220);
		lizardPic.setX(0);
		lizardPic.setVisible(false);

		Image spock = new Image("Spock.jpg");
		spockPic = new  ImageView(spock);
		spockPic.setFitHeight(200);
		spockPic.setFitWidth(220);
		spockPic.setX(0);
		spockPic.setVisible(false);
		
		ImageView rockPic2 = new  ImageView(rock);
		rockPic2.setFitHeight(200);
		rockPic2.setFitWidth(220);
		rockPic2.setX(0);
		rockPic2.setVisible(false);
		
		ImageView paperPic2 = new  ImageView(paper);
		paperPic2.setFitHeight(200);
		paperPic2.setFitWidth(220);
		paperPic2.setX(0);
		paperPic2.setVisible(false);

		ImageView scissorsPic2 = new  ImageView(scissors);
		scissorsPic2.setFitHeight(200);
		scissorsPic2.setFitWidth(220);
		scissorsPic2.setX(0);
		scissorsPic2.setVisible(false);

		ImageView lizardPic2 = new  ImageView(lizard);
		lizardPic2.setFitHeight(200);
		lizardPic2.setFitWidth(220);
		lizardPic2.setX(0);
		lizardPic2.setVisible(false);

		ImageView spockPic2 = new  ImageView(spock);
		spockPic2.setFitHeight(200);
		spockPic2.setFitWidth(220);
		spockPic2.setX(0);
		spockPic2.setVisible(false);
		
		rockPic.setDisable(true);
		paperPic.setDisable(true);
		scissorsPic.setDisable(true);
		lizardPic.setDisable(true);
		spockPic.setDisable(true);
		
		/*if(client.gInfo.have2players == true) {
			rockPic.setDisable(false);
			paperPic.setDisable(false);
			scissorsPic.setDisable(false);
			lizardPic.setDisable(false);
			spockPic.setDisable(false);
		}*/

		
	    Button connectButton = new Button("Connect");
	    Label gameTitle = new Label("ROCK, PAPER, SCISSORS, LIZARD, & SPOCK");
	    Label portLabel = new Label("Port Number");
	    TextField portInput = new TextField();
	    Label IPAddress = new Label("IP Address");
	    TextField address = new TextField();
	    HBox portBox = new HBox(10, portLabel, portInput);
	    HBox addressBox = new HBox(10, IPAddress, address);
	    HBox threeImages = new HBox(30, rockPic, paperPic, scissorsPic);
	    HBox twoImages = new HBox(30, lizardPic, spockPic);
	    Group root1 = new Group();
	    
	    root1.getChildren().addAll(portBox, addressBox, connectButton);
	    root1.getChildren().get(0).setLayoutX(285);
	    root1.getChildren().get(0).setLayoutY(350);
	    root1.getChildren().get(1).setLayoutX(300);
	    root1.getChildren().get(1).setLayoutY(400);
	    root1.getChildren().get(2).setLayoutX(400);
	    root1.getChildren().get(2).setLayoutY(470);
	    
	    connectButton.setDisable(false);
	    
	    connectButton.setOnAction(e -> {
	    	try
	    	{
	    		//System.out.println("A \n");
	    		portNum = portInput.getText();
	    		//System.out.println("B \n");
	    		portInt = Integer.parseInt(portNum);
	    		//System.out.println("C \n");
	    		portInput.clear();
	    		//System.out.println("D \n");
	    		addressString = address.getText();
	    		//System.out.println("E \n");
	    		address.clear();
	    		//System.out.println("F \n");
	    		connectButton.setDisable(true);
	    		//System.out.println("H \n");
	    		
	    		client = new ClientLogic (portInt, addressString, data->{
	    			Platform.runLater(()->{
	    				this.gameInfo = (GameInfo) data;
	    			
	    				System.out.println(gameInfo.playerNumber + "\n");
	    				System.out.println("runlater working \n");
	    				
	    				if(gameInfo.have2players && !(gameInfo.gameStarted))
	    				{	
	    					primaryStage.setScene(secondScene);
	    					gameInfo.gameStarted = true;
	    					System.out.println("inside if working \n");
	    				}
	    				
	    				opponentPlayed = gameInfo.opponentPlays;
	    				
	    				if(gameInfo.gameStarted) {
	    					
	    					System.out.println(opponentPlayed);
		    				
	    					if(opponentPlayed.equals("rock")) {
		    					System.out.println("inside rock if statement");
		    					primaryStage.setScene(fourthScene);
		    					rockPic2.setVisible(true);
	//	    					primaryStage.setScene(fourthScene);
		    				}
		    				else if (opponentPlayed == "paper") {
		    					//System.out.println("paper");
		    					primaryStage.setScene(fourthScene);
		    					paperPic2.setVisible(true);
	//	    					primaryStage.setScene(fourthScene);
		    				}
		    				else if (opponentPlayed == "scissor") {
		    					//System.out.println("sciccor");
		    					primaryStage.setScene(fourthScene);
		    					scissorsPic2.setVisible(true);
	//	    					primaryStage.setScene(fourthScene);
		    				}
		    				else if (opponentPlayed == "lizard") {
		    					//System.out.println("lizard");
		    					primaryStage.setScene(fourthScene);
		    					lizardPic2.setVisible(true);
	//	    					primaryStage.setScene(fourthScene);
		    				}
		    				else if (opponentPlayed == "spock") {
		    					//System.out.println("spock");
		    					primaryStage.setScene(fourthScene);
		    					spockPic2.setVisible(true);
	//	    					primaryStage.setScene(fourthScene);
		    				}
		    				else {
		    					System.out.println("kabaddi kabaddi kabaddi");
		    				}
		    				
	    				}
	    			});
	    		});
	    		
	    		client.start();		
	    		
	    		rockPic.setDisable(false);
				paperPic.setDisable(false);
				scissorsPic.setDisable(false);
				lizardPic.setDisable(false);
				spockPic.setDisable(false);
				
	    		rockPic.setVisible(true);
	    		paperPic.setVisible(true);
	    		scissorsPic.setVisible(true);
	    		lizardPic.setVisible(true);
	    		spockPic.setVisible(true);
	    		
	    		// when the client connects, oppent played images are hidden
	    		rockPic2.setVisible(false);
		    	paperPic2.setVisible(false);
		    	scissorsPic2.setVisible(false);
		    	lizardPic2.setVisible(false);
		    	spockPic2.setVisible(false);
		    	
	    	}
	    	catch (Exception e1)
	    	{
	    		System.out.println("Port Number And/Or Address Not An Integer(s)");
	    		portInput.clear();
	    		address.clear();
	    		connectButton.setDisable(false);
	    		e1.printStackTrace();
	    	}
		});
	    
	    Button playAgainButton = new Button("Play Again");
	    Button quitButton = new Button("Quit");
	    Label rockImage = new Label("Rock");
	    Label paperImage = new Label("Paper");
	    Label scissorsImage = new Label("Scissors");
	    Label lizardImage = new Label("Lizard");
	    Label spockImage = new Label("Spock");
	    Button scoreBoard = new Button("ScoreBoard");
	    Label pickImage = new Label("Pick One: ");
	    HBox imageButtonsOne = new HBox(200, rockImage, paperImage, scissorsImage);
	    HBox imageButtonsTwo = new HBox(200, lizardImage, spockImage);
	    Group root2 = new Group();
	    
	    root2.getChildren().addAll(scoreBoard, threeImages, twoImages, pickImage, imageButtonsOne, imageButtonsTwo, gameTitle);
	    root2.getChildren().get(0).setLayoutX(750);
	    root2.getChildren().get(0).setLayoutY(50);
	    root2.getChildren().get(1).setLayoutX(50);
	    root2.getChildren().get(1).setLayoutY(250);
	    root2.getChildren().get(2).setLayoutX(200);
	    root2.getChildren().get(2).setLayoutY(550);
	    root2.getChildren().get(3).setLayoutX(400);
	    root2.getChildren().get(3).setLayoutY(170);
	    root2.getChildren().get(4).setLayoutX(130);
	    root2.getChildren().get(4).setLayoutY(470);
	    root2.getChildren().get(5).setLayoutX(270);
	    root2.getChildren().get(5).setLayoutY(780);
	    root2.getChildren().get(6).setLayoutX(290);
		    
	    scoreBoard.setOnAction(e -> {			
			try 
			{
				primaryStage.setScene(thirdScene);
			} 
			catch (Exception e1) 
			{
				System.out.println("Couldn't Go To ScoreBoard...");
			}
		});
	  
	    rockPic.setOnMouseClicked(e -> {			
			try 
			{
				// setting what player played
				gameInfo.playerPlays= "rock";
				gameInfo.played = true;
				// testing
				System.out.println("rock pic pressed");
				// setting scene that shows opponents move
				//primaryStage.setScene(fourthScene);
				pickImage.setText("waiting for opponnets move");
				
				// sending the info back to server
		    	client.send(gameInfo);
			} 
			
			// pictures click karne ka code ye hy? oh nhi neeche hai // acha // tou koi idea click kiun nahi ho rahin?
			catch (Exception e1) 
			{
				System.out.println("Couldn't Go To Scene...");
			}
		});
	    
	    paperPic.setOnMouseClicked(e -> {			
			try 
			{
				// setting what player played
				gameInfo.playerPlays= "paper";
				gameInfo.played = true;
				// testing
				System.out.println("paper pic pressed");
				// setting scene that shows opponents move
				// primaryStage.setScene(fourthScene);
				pickImage.setText("waiting for opponnets move");
				
				// sending the info back to server
		    	client.send(gameInfo);
			} 
			catch (Exception e1) 
			{
				System.out.println("Couldn't Go To Scene...");
			}
		});
	    scissorsPic.setOnMouseClicked(e -> {			
			try 
			{
				// setting what player played
				gameInfo.playerPlays= "scissor";
				gameInfo.played = true;
				// testing
				System.out.println("scissor pic pressed");
				// setting scene that shows opponents move
				// primaryStage.setScene(fourthScene);
				pickImage.setText("waiting for opponnets move");
				
				// sending the info back to server
		    	client.send(gameInfo);
			} 
			catch (Exception e1) 
			{
				System.out.println("Couldn't Go To Scene...");
			}
		});
	    
	    lizardPic.setOnMouseClicked(e -> {			
			try 
			{
				// setting what player played
				gameInfo.playerPlays= "lizard";
				gameInfo.played = true;
				// testing
				System.out.println("lizard pic pressed");
				// setting scene that shows opponents move
				// primaryStage.setScene(fourthScene);
				pickImage.setText("waiting for opponnets move");
				
				// sending the info back to server
		    	client.send(gameInfo);
			} 
			catch (Exception e1) 
			{
				System.out.println("Couldn't Go To Scene...");
			}
		});
	    
	    spockPic.setOnMouseClicked(e -> {			
			try 
			{
				// setting what player played
				gameInfo.playerPlays= "spock";
				gameInfo.played = true;
				// testing
				System.out.println("spock pic pressed");
				// setting scene that shows opponents move
				// primaryStage.setScene(fourthScene);
				pickImage.setText("waiting for opponnets move");
				
				// sending the info back to server
		    	client.send(gameInfo);
			} 
			catch (Exception e1) 
			{
				System.out.println("Couldn't Go To Scene...");
			}
		});
	    
	    int playerOneTotal = 0;
	    int playerTwoTotal = 0;
	    Label scoreBoardSceneTitle = new Label("Scoreboard");
	    Label score = new Label("Scores: ");
	    Label messages = new Label("Server Messages: ");
	    Button returnButton = new Button("Go Back To Main Screen");
	    Button anotherReturnButton = new Button("Go Back To Options Screen");
	    Label displayPlayerOneScore = new Label("Player 1 Total: " + playerOneTotal);
	    Label displayPlayerTwoScore = new Label("Player 2 Total: " + playerTwoTotal);
	    Group root3 = new Group();
	    
	    anotherReturnButton.setDisable(true);
	    
	    root3.getChildren().addAll(score, messages, returnButton, displayPlayerOneScore, displayPlayerTwoScore, displayMessages, scoreBoardSceneTitle, anotherReturnButton);
	    root3.getChildren().get(0).setLayoutX(50);
	    root3.getChildren().get(0).setLayoutY(200);
	    root3.getChildren().get(1).setLayoutX(550);
	    root3.getChildren().get(1).setLayoutY(100);
	    root3.getChildren().get(2).setLayoutX(100);
	    root3.getChildren().get(2).setLayoutY(800);
	    root3.getChildren().get(3).setLayoutX(50);
	    root3.getChildren().get(3).setLayoutY(120);
	    root3.getChildren().get(4).setLayoutX(50);
	    root3.getChildren().get(4).setLayoutY(140);
	    root3.getChildren().get(5).setLayoutX(350);
	    root3.getChildren().get(5).setLayoutY(130);
	    root3.getChildren().get(6).setLayoutX(400);
	    root3.getChildren().get(7).setLayoutX(600);
	    root3.getChildren().get(7).setLayoutY(800);
	    
	    displayMessages.setPrefWidth(480);
	    
	    returnButton.setOnAction(e -> {
	    	anotherReturnButton.setDisable(true);
	    	primaryStage.setScene(secondScene);
	    });
	    
	    anotherReturnButton.setOnAction(e -> {
	    	primaryStage.setScene(fourthScene);
	    	rockPic2.setVisible(true);
	    	paperPic2.setVisible(true);
	    	scissorsPic2.setVisible(true);
	    	lizardPic2.setVisible(true);
	    	spockPic2.setVisible(true);
	    });
	    
	    Button checkScoreBoard = new Button("ScoreBoard");
	    Label opponentsChoice = new Label("Opponent Played: ");
	    HBox threeImagesCopy = new HBox(30, rockPic2, paperPic2, scissorsPic2);
	    HBox twoImagesCopy = new HBox(30, lizardPic2, spockPic2);
	    Group root4 = new Group();
	    
	    root4.getChildren().addAll(playAgainButton, quitButton, checkScoreBoard, threeImagesCopy, twoImagesCopy, opponentsChoice);
	    root4.getChildren().get(0).setLayoutX(50);
	    root4.getChildren().get(0).setLayoutY(800);
	    root4.getChildren().get(1).setLayoutX(800);
	    root4.getChildren().get(1).setLayoutY(800);
	    root4.getChildren().get(2).setLayoutX(750);
	    root4.getChildren().get(2).setLayoutY(50);
	    root4.getChildren().get(3).setLayoutX(50);
	    root4.getChildren().get(3).setLayoutY(250);
	    root4.getChildren().get(4).setLayoutX(200);
	    root4.getChildren().get(4).setLayoutY(550);
	    root4.getChildren().get(5).setLayoutX(100);
	    root4.getChildren().get(5).setLayoutY(100);
	    
	    playAgainButton.setOnAction(e -> {	
	    	anotherReturnButton.setDisable(true);
	    	primaryStage.setScene(secondScene);
		});
	    
	    quitButton.setOnAction(e -> {
	    	anotherReturnButton.setDisable(true);
	    	primaryStage.setScene(startScene);
	    	connectButton.setDisable(false);
		});
	    
	    checkScoreBoard.setOnAction(e -> {
	    	anotherReturnButton.setDisable(false);
	    	primaryStage.setScene(thirdScene);
		});
	    
	    // port and Ip input scene
		startScene = new Scene(root1, 900, 900);
		// game playable scene
		secondScene = new Scene(root2, 900, 900);
		// score board plus server messages, player totals
		thirdScene = new Scene(root3, 900, 900);
		// what opponent played
		fourthScene = new Scene(root4, 900, 900);
		
		primaryStage.setScene(startScene);
		primaryStage.show();
	}
	
	
}
