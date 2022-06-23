package hse.java.leypunskiyMaksim208hw5;

import hse.java.leypunskiyMaksim208hw5.geometry.Point;
import hse.java.leypunskiyMaksim208hw5.model.Figure;
import hse.java.leypunskiyMaksim208hw5.model.GameField;
import hse.java.leypunskiyMaksim208hw5.view.FigureView;
import hse.java.leypunskiyMaksim208hw5.view.InteractiveView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Optional;

public class MainApplication extends Application {
    private GridPane gridPane;
    private GameField gameField;
    private Pane[][] panes;
    private InteractiveView interactiveView;
    private double mouseAnchorX;
    private double mouseAnchorY;
    private double startX;
    private double startY;
    private boolean isStartCorrect = false;
    private int count;

    @Override
    public void start(Stage stage) {
        configureGridPane();
        configureScene(stage);
        configureGame();
        generateNewFigure();
        count = 0;
    }

    private void setup() {
        count = 0;
        configureGridPane();
        configureGame();
        generateNewFigure();
        startTimer();
        interactiveView.getFigureView().setTranslateX(startX);
        interactiveView.getFigureView().setTranslateY(startY);
    }

    private void startTimer() {
        interactiveView.getLeft().getTimeline().stop();
        interactiveView.getLeft().timeCountProperty().set(0);
        interactiveView.getLeft().getTimeline().play();
    }

    private void generateNewFigure() {
        interactiveView.getFigureView().getChildren().clear();
        Figure figure = Figure.generateRandom();
        FigureView figureView = new FigureView(figure);
        interactiveView.updateFigure(figureView);
        makeDraggable(interactiveView.getFigureView(), panes);
    }

    private void makeDraggable(FigureView node, Pane[][] panes) {
        if (!isStartCorrect) {
            startX = node.getTranslateX();
            startY = node.getTranslateY();
            isStartCorrect = true;
        }
        node.setOnMousePressed(mouseEvent -> {
            mouseAnchorX = mouseEvent.getSceneX() - node.getTranslateX();
            mouseAnchorY = mouseEvent.getSceneY() - node.getTranslateY();
        });

        node.setOnMouseDragged(mouseEvent -> {
            node.setTranslateX(mouseEvent.getSceneX() - mouseAnchorX);
            node.setTranslateY(mouseEvent.getSceneY() - mouseAnchorY);
        });

        node.setOnMouseReleased(mouseEvent -> {
            findLeftTopCell(node, panes);
            Optional<Point> point = findLeftTopCell(node, panes);
            if (point.isPresent()) {
                if (gameField.placeFigure(node.getFigure(), point.get())) {
                    System.out.println("successfully added");
                    updateField();
                    node.setTranslateX(startX);
                    node.setTranslateY(startY);
                    ++count;
                    generateNewFigure();
                }
            }
        });
    }

    private Optional<Point> findLeftTopCell(FigureView node, Pane[][] panes) {
        Node topLeft = node.getPanes()[0][0];
        Bounds bounds = topLeft.localToScene(topLeft.getBoundsInLocal());
        double x = bounds.getCenterX();
        double y = bounds.getCenterY();

        Bounds gridBound;
        double gridX;
        double gridY;
        for (int i = 0; i < panes.length; ++i) {
            for (int j = 0; j < panes[0].length; ++j) {
                gridBound = panes[i][j].localToScene(panes[i][j].getBoundsInLocal());
                gridX = gridBound.getCenterX();
                gridY = gridBound.getCenterY();
                if (Math.abs(gridX - x) < 25 && Math.abs(gridY - y) < 25) {
                    System.out.printf("found left top corner: [%d][%d]\n", i, j);
                    return Optional.of(new Point(j, i));
                }
            }
        }
        return Optional.empty();
    }

    private void updateField() {
        for (int i = 0; i < gameField.getField().length; ++i) {
            for (int j = 0; j < gameField.getField()[0].length; ++j) {
                if (gameField.getField()[i][j] == 1) {
                    panes[i][j].setBackground(FigureView.getOkColor());
                }
            }
        }
        gridPane.getChildren().clear();
        for (int i = 0; i < gameField.getField().length; ++i) {
            for (int j = 0; j < gameField.getField()[0].length; ++j) {
                gridPane.add(panes[i][j], j, i);
            }
        }
    }

    private void configureGame() {
        this.gameField = new GameField();
    }

    private void showResults() {
        interactiveView.getLeft().getTimeline().pause();
        Alert alert = new Alert(Alert.AlertType.INFORMATION,
                "Results: "
                        + interactiveView.getLeft().getTimeCount() +
                        " seconds\n"
                        + count
                        + " moves",
                ButtonType.OK);
        alert.showAndWait();
    }

    private void configureScene(Stage stage) {
        stage.setMinWidth(700);
        stage.setMaxWidth(700);
        stage.setMinHeight(800);
        stage.setMaxHeight(800);
        stage.setTitle("Not a Tetris");
        BorderPane root = new BorderPane();
        BorderPane bottomCenter = new BorderPane();
        BorderPane top = new BorderPane();
        this.interactiveView = new InteractiveView();
        this.interactiveView.getLeft().getNewGameButton().setOnAction(actionEvent -> {
            showResults();
            setup();
        });
        this.interactiveView.getLeft().getEndGameButton().setOnAction(actionEvent -> {
            showResults();
            Platform.exit();
            System.exit(0);
        });
        top.setCenter(interactiveView);

        bottomCenter.setCenter(gridPane);
        root.setBottom(bottomCenter);
        root.setTop(top);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void configureGridPane() {
        if (!Objects.isNull(this.gridPane)) {
            gridPane.getChildren().clear();
        } else {
            gridPane = new GridPane();
        }
        gridPane.setPadding(new Insets(4));
        gridPane.setMaxSize(466, 466);
        gridPane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        gridPane.setHgap(2);
        gridPane.setVgap(2);

        panes = new Pane[GameField.getHeight()][GameField.getWidth()];
        for (int i = 0; i < GameField.getHeight(); ++i) {
            for (int j = 0; j < GameField.getWidth(); ++j) {
                Pane pane = new Pane();
                pane.setPrefSize(50, 50);
                pane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
                gridPane.add(pane, j, i);
                panes[i][j] = pane;
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}