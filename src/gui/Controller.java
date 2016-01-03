package gui;

import ai.minimax.Minimax;
import ai.minimax.TicTacToeNode;
import game.Board;
import game.Game;
import game.GameException;
import game.States;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Enumeration;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Created with IntelliJ IDEA.<br/>
 * User: Carlo<br/>
 * Date: 25/12/2015<br/>
 * Time: 12:39<br/>
 */
public class Controller implements Initializable {
    private static final char DEF_PLAYER_1 = 'X';
    private static final char DEF_PLAYER_2 = 'O';
    private static final int AI_TREE_DEPTH = 4;

    private Game game;
    private int moves;
    private Minimax ai1;
    private Minimax ai2;
    @FXML
    private GridPane boardGridPane;

    private Stage logicStage;
    private TabPane tabPane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logicStage = new Stage();
        logicStage.setTitle("AI - Logic");
        tabPane = new TabPane();
        logicStage.setScene(new Scene(tabPane, 500, 500));
    }

    public void newGame() {
        boardGridPane.getChildren().clear();
        tabPane.getTabs().clear();
        logicStage.close();
        moves = 0;
        ai1 = null;
        ai2 = null;
        game = new Game(DEF_PLAYER_1, DEF_PLAYER_2);

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Opponent options");
        alert.setHeaderText("How many AI should there be?");
        ButtonType buttonTypeNone = new ButtonType("None");
        ButtonType buttonTypeOne = new ButtonType("One");
        ButtonType buttonTypeTwo = new ButtonType("Two");
        alert.getButtonTypes().setAll(buttonTypeNone, buttonTypeOne, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == buttonTypeOne) {
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION);
            alert2.setTitle("Opponent options");
            alert2.setHeaderText("Do you want to start?");
            ButtonType buttonTypeYes = new ButtonType("Yes");
            ButtonType buttonTypeNo = new ButtonType("No");
            alert2.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            Optional<ButtonType> result2 = alert2.showAndWait();

            if (result2.get() == buttonTypeYes)
                ai1 = new Minimax(DEF_PLAYER_2, DEF_PLAYER_1, AI_TREE_DEPTH);
            else
                ai1 = new Minimax(DEF_PLAYER_1, DEF_PLAYER_2, AI_TREE_DEPTH);

            initBoard();
            aiMove(ai1);
        } else if (result.get() == buttonTypeTwo) {
            ai1 = new Minimax(DEF_PLAYER_1, DEF_PLAYER_2, AI_TREE_DEPTH);
            ai2 = new Minimax(DEF_PLAYER_2, DEF_PLAYER_1, AI_TREE_DEPTH);

            initBoard();

            while (game.getGameState().getState() == States.NORMAL) {
                aiMove(ai1.getAiPlayer() == game.getCurrentPlayer() ? ai1 : ai2);
            }
        } else if (result.get() == buttonTypeNone) {
            initBoard();
        }
    }

    private void initBoard() {
        char[][] boardArray = game.getBoard().getBoardArray();

        for (int y = 0; y < boardArray.length; y++) {
            for (int x = 0; x < boardArray[y].length; x++) {
                Button button = new TicTacToeButton(Board.yxToPos(y, x));
                button.setText(Board.yxToPos(y, x) + "");
                button.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
                button.setOnAction(this::handleTTTButton);

                boardGridPane.add(button, x, y);
            }
        }
    }

    private void handleTTTButton(ActionEvent actionEvent) {
        TicTacToeButton button = (TicTacToeButton) actionEvent.getSource();
        try {
            button.setValue(game.getCurrentPlayer());
            game.makeMove(button.getPos());
            button.setDisable(true);
            moves++;
            if (game.getGameState().getState() != States.NORMAL) {
                endGame();
                return;
            }
        } catch (GameException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Game error");
            alert.setContentText(e.getMessage());
            alert.show();
        }

        if (ai1 != null && ai1.getAiPlayer() == game.getCurrentPlayer())
            aiMove(ai1);
    }

    private void aiMove(Minimax ai) {
        if (ai.getAiPlayer() == game.getCurrentPlayer()) {
            //Get the correct move
            int pos = ai.move(game.getBoard());

            //Draw the logic
            GridPane logicGridPane = new GridPane();
            drawNode(ai.getLatestNode(), 0, 0, logicGridPane);

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(logicGridPane);

            Tab tab = new Tab();
            tab.setText("Move " + moves + " - " + ai.getAiPlayer());
            tab.setContent(scrollPane);
            tabPane.getTabs().add(tab);

            if (!logicStage.isShowing()) {
                double windowGap = 5;
                Stage currentStage = (Stage) boardGridPane.getScene().getWindow();

                logicStage.setX(currentStage.getX() + currentStage.getWidth() + windowGap);
                logicStage.setY(currentStage.getY());
                logicStage.show();
            }


            //Press the correct button
            boardGridPane.getChildren().forEach(ch -> {
                TicTacToeButton TTTB = (TicTacToeButton) ch;
                if (TTTB.getPos() == pos) {
                    TTTB.fire();
                }
            });
        }
    }

    private int drawNode(TicTacToeNode node, int row, int column, GridPane logicGridPane) {
        Enumeration e = node.children();
        int colspan = 0;
        while (e.hasMoreElements()) {
            TicTacToeNode child = (TicTacToeNode) e.nextElement();
            colspan += drawNode(child, row + 1, column + colspan, logicGridPane);
        }

        //Make the label
        Label label = new Label(node.toString());
        label.setMaxSize(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
        label.setTextAlignment(TextAlignment.CENTER);
        label.paddingProperty().setValue(new Insets(5, 5, 5, 5));
        label.setStyle("-fx-border-color:black;");
        if (node.isChosen())
            label.setStyle("-fx-background-color: lawngreen;");

        if (colspan == 0) colspan = 1;
        logicGridPane.add(label, column, row, colspan, 1);
        return colspan;
    }

    private void endGame() {
        if (game.getGameState().getState() != States.NORMAL) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            if (game.getGameState().getState() == States.WON) {
                alert.setTitle("Congratulations");
                alert.setContentText("Player " + game.getGameState().getWinner() + " won!");
            } else {
                alert.setTitle("That's it");
                alert.setContentText("The Game has ended in a tie.");
            }
            alert.show();
        }
    }
}
