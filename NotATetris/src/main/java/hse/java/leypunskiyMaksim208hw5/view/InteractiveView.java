package hse.java.leypunskiyMaksim208hw5.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

public class InteractiveView extends VBox {
    private final FigureView figureView;
    private final InfoView left;
    private final BorderPane paneForFigure;

    public InteractiveView() {
        BorderPane root = new BorderPane();
        left = new InfoView();
        left.setPrefSize(350, 300);
        figureView = new FigureView(null);
        paneForFigure = new BorderPane();
        paneForFigure.setPadding(new Insets(70));
        paneForFigure.setCenter(figureView);
        figureView.setAlignment(Pos.CENTER);

        root.setLeft(left);
        root.setRight(paneForFigure);
        getChildren().add(root);
    }

    public void updateFigure(FigureView figureView) {
        this.figureView.setFigure(figureView.getFigure());
        this.figureView.setPanes(figureView.getPanes());
        this.figureView.getChildren().clear();
        this.figureView.getChildren().addAll(figureView.getChildren());
        paneForFigure.getChildren().clear();
        paneForFigure.setCenter(this.figureView);
    }

    public InfoView getLeft() {
        return left;
    }

    public FigureView getFigureView() {
        return figureView;
    }
}
