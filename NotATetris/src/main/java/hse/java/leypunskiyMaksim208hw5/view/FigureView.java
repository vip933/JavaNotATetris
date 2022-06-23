package hse.java.leypunskiyMaksim208hw5.view;

import hse.java.leypunskiyMaksim208hw5.model.Figure;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.Objects;

public class FigureView extends VBox {
    private Pane[][] panes;
    private Figure figure;

    private static final Background clearColor = new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY));
    private static final Background okColor = new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY));
    private static final Background badColor = new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY));

    public FigureView(Figure figure) {
        GridPane gridPane = new GridPane();
        this.figure = figure;
        getChildren().add(gridPane);
        gridPane.setAlignment(Pos.TOP_CENTER);
        if (!Objects.isNull(figure)) {
            // Setup panes array.
            panes = new Pane[figure.getField().length][figure.getField()[0].length];
            // Making panes shape from figure's matrix shape.
            for (int i = 0; i < figure.getField().length; ++i) {
                for (int j = 0; j < figure.getField()[0].length; ++j) {
                    // Adding colorful pane.
                    if (figure.getField()[i][j] == 1) {
                        Pane pane = new Pane();
                        panes[i][j] = pane;
                        pane.setPrefSize(50, 50);
                        pane.setBackground(badColor);
                        gridPane.add(pane, j, i);
                        // Adding transparent pane.
                    } else {
                        Pane pane = new Pane();
                        panes[i][j] = pane;
                        pane.setPrefSize(50, 50);
                        pane.setBackground(clearColor);
                        gridPane.add(pane, j, i);
                    }
                }
            }
        }
    }

    public Pane[][] getPanes() {
        return panes;
    }

    public void setPanes(Pane[][] panes) {
        this.panes = panes;
    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public static Background getOkColor() {
        return okColor;
    }
}
