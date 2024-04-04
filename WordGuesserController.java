package com.example.assignment_3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Arrays;

public class WordGuesserController {

    GameRunner myGame;
    State myState;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField guessField;
    @FXML
    private GridPane wordGrid;
    // Labels for first guess
    @FXML
    private Label L00;
    @FXML
    private Label L10;
    @FXML
    private Label L20;
    @FXML
    private Label L30;
    @FXML
    private Label L40;
    // Labels for second guess
    @FXML
    private Label L01;
    @FXML
    private Label L11;
    @FXML
    private Label L21;
    @FXML
    private Label L31;
    @FXML
    private Label L41;
    // Labels for third guess
    @FXML
    private Label L02;
    @FXML
    private Label L12;
    @FXML
    private Label L22;
    @FXML
    private Label L32;
    @FXML
    private Label L42;
    // Labels for fourth guess
    @FXML
    private Label L03;
    @FXML
    private Label L13;
    @FXML
    private Label L23;
    @FXML
    private Label L33;
    @FXML
    private Label L43;
    // Labels for fifth guess
    @FXML
    private Label L04;
    @FXML
    private Label L14;
    @FXML
    private Label L24;
    @FXML
    private Label L34;
    @FXML
    private Label L44;
    // Labels for sixth guess
    @FXML
    private Label L05;
    @FXML
    private Label L15;
    @FXML
    private Label L25;
    @FXML
    private Label L35;
    @FXML
    private Label L45;
    // Initialize 2d array containing all labels
    public Label[][] arrayOfLabels;

    // Labels for how to play
    @FXML
    private Label RedWords;
    @FXML
    private Label BlueWords;
    @FXML
    private Label GreenWords;
    @FXML
    private Label EnterWord;

    public int guessNum = 0;
    private Stage stage;
    private Scene scene;
    private Parent root;

    static Stage applicationStage;
    public void switchToWinScene() throws IOException {
        Parent root = FXMLLoader.load(WordGuesserController.class.getResource("Win-Scene.fxml"));
        scene = new Scene(root);
        applicationStage.setScene(scene);

    }

    public void switchToGameScene() throws IOException {
        Parent root = FXMLLoader.load(WordGuesserController.class.getResource("Game-Scene.fxml"));
        scene = new Scene(root);
        applicationStage.setScene(scene);
    }

    public void switchToLoseScene() throws IOException {
        Parent root = FXMLLoader.load(WordGuesserController.class.getResource("Lose-Scene.fxml"));
        scene = new Scene(root);
        applicationStage.setScene(scene);
    }



    public WordGuesserController() {
        myGame = new GameRunner();
        myState = new State();
        statusLabel = new Label();
        guessField = new TextField();
        wordGrid = new GridPane();
            }

    public WordGuesserController(GameRunner myGame) {
        this.myGame = myGame;
        statusLabel = new Label();
        guessField = new TextField();
    }


    @FXML
    protected void onHowToPlayButtonClick(){
        EnterWord.setText("Type a five letter word and press guess to lock in your guess. For each character of your guess color coded feedback will be provided");
        RedWords.setText("Red characters indicate that the character does not exist in the word");
        BlueWords.setText("Blue characters indicate that the character is in the word but in the wrong spot ");
        GreenWords.setText("Green characters indicate that the character is in the word in that exact spot");
    }



    @FXML
    protected void onPlayButtonClick() throws IOException {
    switchToGameScene();
    }


    @FXML
    protected void onGuessButtonClick() throws IOException {
        // Creating 2d array containing all labels in the grid
        arrayOfLabels = new Label[][]{
                {L00, L10, L20, L30, L40},
                {L01, L11, L21, L31, L41},
                {L02, L12, L22, L32, L42},
                {L03, L13, L23, L33, L43},
                {L04, L14, L24, L34, L44},
                {L05, L15, L25, L35, L45}
        };
        String currentWord = guessField.getText();
        Guess currentGuess = myGame.processInput(currentWord);

        // do something to output currentGuess.

        if (currentGuess.hasWin()) {
            System.out.println("your a winner!");
            switchToWinScene();
        }

        else if (guessNum == 5){
        switchToLoseScene();
        }



        if (myGame.wasGoodGuess()) {
            guessField.clear();
            int[] letterStatus = currentGuess.getLetterStatus();
            // Loops through each character in the word
            for (int i = 0; i < letterStatus.length ; i++){
                // For each character in the guess, set the corresponding label to that char
                arrayOfLabels[guessNum][i].setText(String.valueOf(currentWord.charAt(i)));

                // Switch cases to change color of labels to provide feedback
                switch(letterStatus[i]){
                    // Blue = In the word but wrong spot
                    case 1:
                        arrayOfLabels[guessNum][i].setTextFill(Color.rgb(36,24,224,1));
                        break;
                    // Green = in word and right spot
                    case  2:
                        arrayOfLabels[guessNum][i].setTextFill(Color.rgb(34,156,25,1));
                        break;
                    // Red = Not in the word
                    case 0:
                        arrayOfLabels[guessNum][i].setTextFill(Color.rgb(242,13,13,1));
                        break;

                }
            } guessNum ++;

        } else {
            // Determine the type of error the user has made
            if (!myState.isWordInDictionary(currentWord)) {
                statusLabel.setText("Error: Your guess is not a valid word.");
            }else if ( currentWord.length() < myState.getSecretWordLength()){
                statusLabel.setText("Error: your guess is shorter than the secret word");
            }else if (currentWord.length() > myState.getSecretWordLength()) {
                statusLabel.setText("Error: Your guess is longer than the secret word.");
            }
        }
    }





}
