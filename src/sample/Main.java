/*Programmer: Austin Parker  Date: Aug. 29, 2020
Assignment: Chapter 15 PE 15.16
Purpose: Move two circles with connecting line to show distance between two circles*/


package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        /*creates pane object class*/
        Pane mainPane = new Pane();

        /*sets the radius*/
        double radius = 10;

        /*creates the first circle A*/
        Circle firstCircle = new Circle(40, 40, radius, Color.TRANSPARENT);

        /*draws the circle*/
        firstCircle.setStroke(Color.BLACK);
        Text textAreaOne = new Text(firstCircle.getCenterX(),
                firstCircle.getCenterY(), "");
        textAreaOne.xProperty().bind(firstCircle.centerXProperty());
        textAreaOne.yProperty().bind(firstCircle.centerYProperty());

        //create the second circle A which have radius (120,150)
        Circle secondCircle = new Circle(120, 150, radius, Color.TRANSPARENT);

        //draw circle for the given dimensions
        secondCircle.setStroke(Color.BLACK);

        //set the text to empty of the circle
        Text textAreaTwo = new Text(secondCircle.getCenterX(),
                secondCircle.getCenterY(), "");
        textAreaTwo.xProperty().bind(secondCircle.centerXProperty());
        textAreaTwo.yProperty().bind(secondCircle.centerYProperty());
        Text resultOne = new Text();

        /*creates the line object*/
        Line movableLine = new Line();

        /*calls the method based on dimensions of the circles*/
        moveLine(movableLine, firstCircle, secondCircle, resultOne);

        /*sets the text while the mouse is moving for first circle*/
        firstCircle.setOnMouseDragged(e ->
        {
            double x_axis = e.getX();
            double y_axis = e.getY();
            firstCircle.setCenterX(x_axis);
            firstCircle.setCenterY(y_axis);
            moveLine(movableLine, firstCircle, secondCircle, resultOne);
        });

        /*sets the text while the mouse is moving for second circle*/
        secondCircle.setOnMouseDragged(e ->
        {
            double x_axis_2 = e.getX();
            double y_axis_2 = e.getY();
            secondCircle.setCenterX(x_axis_2);
            secondCircle.setCenterY(y_axis_2);
            moveLine(movableLine, firstCircle, secondCircle, resultOne);
        });

        /*sets the controls for the view*/
        mainPane.getChildren().addAll(textAreaOne, textAreaTwo,
                firstCircle, secondCircle, movableLine, resultOne);

        /*view title*/
        primaryStage.setTitle("Exercise 15_16");

        //dimensions of the scene
        primaryStage.setScene(new Scene(mainPane, 500, 500));

        primaryStage.show();
    }

    //method to move the line
    private void moveLine(Line lineObj, Circle firstCircle,
                          Circle secondCircle, Text res) {

        //declared variables which hold functions to get the circle values
        double first_x = firstCircle.getCenterX();
        double first_y = firstCircle.getCenterY();
        double second_x = secondCircle.getCenterX();
        double second_y = secondCircle.getCenterY();
        double rad_circ = firstCircle.getRadius();

        //calculates first circle distance
        double distanceOne = Math.sqrt((second_y - first_y)
                * (second_y - first_y) + (second_x - first_x) * (second_x - first_x));

        int total_x_first = (int) (first_x - rad_circ * (first_x - second_x) / distanceOne);
        int total_y_first = (int) (first_y - rad_circ * (first_y - second_y) / distanceOne);
        int total_x_second = (int) (second_x + rad_circ * (first_x - second_x) / distanceOne);
        int total_y_second = (int) (second_y + rad_circ * (first_y - second_y) / distanceOne);

        lineObj.setStartX(total_x_first);
        lineObj.setStartY(total_y_first);
        lineObj.setEndX(total_x_second);
        lineObj.setEndY(total_y_second);

        //calculates second circle distance
        double distanceTwo = Math.sqrt((total_x_first - total_x_second)
                * (total_x_first - total_x_second)
                + (total_y_first - total_y_second)
                * (total_y_first - total_y_second));
        res.setText(String.format("%d", (int) distanceTwo));
        res.setX((total_x_first + total_x_second) / 2);
        res.setY((total_y_first + total_y_second) / 2);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
