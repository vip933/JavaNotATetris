package hse.java.leypunskiyMaksim208hw5.view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class InfoView extends VBox {
    private final Button newGameButton;
    private final Button endGameButton;
    private final Timeline timeline;
    private final IntegerProperty timeCount;

    public InfoView() {
        this.newGameButton = new Button("New game");
        newGameButton.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        newGameButton.setTextAlignment(TextAlignment.CENTER);
        this.endGameButton = new Button("End game");
        endGameButton.setTextAlignment(TextAlignment.CENTER);
        endGameButton.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        Label timerLabel = new Label("Time: 0");
        timerLabel.setAlignment(Pos.CENTER);
        timerLabel.setTextAlignment(TextAlignment.CENTER);

        FlowPane flowPane = new FlowPane();
        flowPane.setOrientation(Orientation.VERTICAL);
        flowPane.setAlignment(Pos.CENTER);
        flowPane.setVgap(50);
        flowPane.getChildren().addAll(
                timerLabel,
                newGameButton,
                endGameButton
        );
        getChildren().add(flowPane);
        // Setting up timer.
        timeCount = new SimpleIntegerProperty(0);
        timeline = new Timeline(new KeyFrame(Duration.seconds(1000), new KeyValue(timeCount, 1000)));
        timerLabel.textProperty().bind(Bindings.createStringBinding(() ->
                "Time:  " + timeCount.get(), timeCount)
        );
        timeline.play();
    }

    public Button getNewGameButton() {
        return newGameButton;
    }

    public Button getEndGameButton() {
        return endGameButton;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public int getTimeCount() {
        return timeCount.get();
    }

    public IntegerProperty timeCountProperty() {
        return timeCount;
    }
}
