package savekoalas.gui;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import savekoalas.engine.CellType;
import savekoalas.engine.GameEngine;

/**
 * Controller class for the GUI version of maze runner game.
 */
public class Controller implements Serializable{
    
    private int size = 600;
    private int spots = 10;
    private int squareSize = size/spots;
    private int gameDifficulty;
    GameEngine engine;

    @FXML
    Pane pane;
    @FXML
    Button startGame;
    @FXML
    Button up;
    @FXML
    Button down;
    @FXML
    Button left;
    @FXML
    Button right;
    @FXML
    Button saveGame;
    @FXML
    Button loadGame;
    @FXML
    TextField difficulty;
    @FXML
    TextArea gameState;

    @FXML
    public void initialize() {
        for (int i = 0; i < size; i += squareSize) {
            for (int j = 0; j < size; j += squareSize) {
                Rectangle r = new Rectangle(i, j, squareSize, squareSize);
                r.setFill(Color.WHITE);
                r.setStroke(Color.BLACK);
                pane.getChildren().add(r);
            }
        }
        startGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    startGameButton();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        saveGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveGameButton();
            }
        });

        loadGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                loadGameButton();
            }
        });

        difficulty.setText("5");

        up.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                engine.moveUp();
                try {
                    drawBoard();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        down.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                engine.moveDown();
                try {
                    drawBoard();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        left.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                engine.moveLeft();
                try {
                    drawBoard();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        right.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                engine.moveRight();
                try {
                    drawBoard();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    /**
     * Draws the current game state to the GUI
     */
    @FXML
    public void drawBoard() throws FileNotFoundException {
        for (int i = 0; i < size; i += squareSize) {
            for (int j = 0; j < size; j += squareSize) {
                Rectangle r = new Rectangle(i, j, squareSize, squareSize);
                r.setFill(Color.WHITE);
                r.setStroke(Color.BLACK);
                pane.getChildren().add(r);
            }
        }
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (engine.board.getBoardCell(row, col) == CellType.KOALA) {
                    FileInputStream koalaFile = new FileInputStream("src/main/resources/koala.png");
                    Image koalaImage = new Image(koalaFile);
                    ImageView koala = new ImageView(koalaImage);
                    koala.setTranslateX(squareSize * col);
                    koala.setTranslateY(squareSize * row);
                    koala.setFitWidth(squareSize);
                    koala.setFitHeight(squareSize);
                    pane.getChildren().add(koala);
                } else if (engine.board.getBoardCell(row, col) == CellType.TRAP) {
                    FileInputStream trapFile = new FileInputStream("src/main/resources/trap.png");
                    Image trapImage = new Image(trapFile);
                    ImageView trap = new ImageView(trapImage);
                    trap.setTranslateX(squareSize * col);
                    trap.setTranslateY(squareSize * row);
                    trap.setFitWidth(squareSize);
                    trap.setFitHeight(squareSize);
                    pane.getChildren().add(trap);
                } else if (engine.board.getBoardCell(row, col) == CellType.FOUNTAIN) {
                    FileInputStream fountainFile = new FileInputStream("src/main/resources/fountain.png");
                    Image fountainImage = new Image(fountainFile);
                    ImageView fountain = new ImageView(fountainImage);
                    fountain.setTranslateX(squareSize * col);
                    fountain.setTranslateY(squareSize * row);
                    fountain.setFitWidth(squareSize);
                    fountain.setFitHeight(squareSize);
                    pane.getChildren().add(fountain);
                } else if (engine.board.getBoardCell(row, col) == CellType.PLAYER) {
                    FileInputStream playerFile = new FileInputStream("src/main/resources/player.png");
                    Image playerImage = new Image(playerFile);
                    ImageView player = new ImageView(playerImage);
                    player.setTranslateX(squareSize * col);
                    player.setTranslateY(squareSize * row);
                    player.setFitWidth(squareSize);
                    player.setFitHeight(squareSize);
                    pane.getChildren().add(player);
                } else if (engine.board.getBoardCell(row, col) == CellType.EXIT) {
                    FileInputStream exitFile = new FileInputStream("src/main/resources/exit.png");
                    Image exitImage = new Image(exitFile);
                    ImageView exit = new ImageView(exitImage);
                    exit.setTranslateX(squareSize * col);
                    exit.setTranslateY(squareSize * row);
                    exit.setFitWidth(squareSize);
                    exit.setFitHeight(squareSize);
                    pane.getChildren().add(exit);
                }
            }
        }
        if (engine.numberOfTraps() < gameDifficulty) {
            engine.isTrap = true;
        }

        gameState.setText("Steps Remaining: " + (engine.stepLimit - engine.stepCounter) + "\n" +
        "Koalas Saved: " + engine.koalasSaved);

        if (engine.board.getBoardCell(0, 9) == CellType.PLAYER && engine.koalasSaved == 5) {
            gameState.setText("You Won!\nScore: " + (engine.stepLimit - engine.stepCounter) + "\nStart a new game or load a save");
            up.setDisable(true);
            down.setDisable(true);
            left.setDisable(true);
            right.setDisable(true);
            saveGame.setDisable(true);
        } else if (engine.stepCounter >= engine.stepLimit) {
            gameState.setText("You Lose!\nStart a new game or load a save");
            up.setDisable(false);
            down.setDisable(false);
            left.setDisable(false);
            right.setDisable(false);
            saveGame.setDisable(false);
        }
    }

    /**
     * Starts a new game 
     */
    public void startGameButton() throws FileNotFoundException {
        gameDifficulty = Integer.parseInt(difficulty.getText());
        if (gameDifficulty >= 1 && gameDifficulty <= 10) {
            engine = new GameEngine(gameDifficulty);
            drawBoard();
            up.setDisable(false);
            down.setDisable(false);
            left.setDisable(false);
            right.setDisable(false);
            saveGame.setDisable(false);
        } else {
            gameState.setText("Please input a integer\nbetween 1-10 for difficulty");
        }
    }

    /**
     * Saves the current game
     */
    private void saveGameButton() {
        try {
            FileOutputStream fos = new FileOutputStream("SaveKoalas.sav");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(engine);
            oos.flush();
            oos.close();
            gameState.setText("Game saved");
        } catch (Exception e) {
            System.out.println("Serialization Error! Can't save data.\n"
                    + e.getClass() + ": " + e.getMessage() + "\n");  
        }
    }

    /**
     * Loads a saved game 
     */
    private void loadGameButton() {
        try {
            FileInputStream fis = new FileInputStream("SaveKoalas.sav");
            ObjectInputStream ois = new ObjectInputStream(fis);
            engine = (GameEngine) ois.readObject();
            ois.close();
            difficulty.setText(String.valueOf(engine.difficulty));
            gameDifficulty = engine.difficulty;
            drawBoard();
            up.setDisable(false);
            down.setDisable(false);
            left.setDisable(false);
            right.setDisable(false);
            saveGame.setDisable(false);
        } catch (Exception e) {
            System.out.println("Serialization Error! Can't load data.\n"
                    + e.getClass() + ": " + e.getMessage() + "\n");  
        }
    }
}
