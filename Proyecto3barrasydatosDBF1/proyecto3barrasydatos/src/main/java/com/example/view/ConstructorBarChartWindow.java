package com.example.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.example.App;
import com.example.Controllers.ConstructorResultsController;
import com.example.Models.ConstructorResult;

import java.util.Comparator;
import java.util.List;

public class ConstructorBarChartWindow extends Application {

    private BarChart<String, Number> barChart;
    private boolean ascendingOrder = false;  // Default to descending order
    private ObservableList<ConstructorResult> resultsList;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Barra de Constructores");

        ComboBox<Integer> yearComboBox = new ComboBox<>();
        for (int year = 1950; year <= 2023; year++) {
            yearComboBox.getItems().add(year);
        }
        yearComboBox.setOnAction(event -> {
            int selectedYear = yearComboBox.getValue();
            updateResults(selectedYear);
        });

        Button sortButton = new Button("Ordenar Menor a Mayor");
        sortButton.setOnAction(event -> {
            ascendingOrder = !ascendingOrder;
            sortButton.setText(ascendingOrder ? "Ordenar Menor a Mayor" : "Ordenar Mayor a Menor");
            sortResults();
        });

        Button backButton = new Button("Regresar");
        backButton.setOnAction(event -> {
            primaryStage.close();
            App mainApp = new App();
            try {
                mainApp.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Nombre del Constructor");
        xAxis.setTickLabelFill(javafx.scene.paint.Color.WHITE);
        xAxis.setTickLabelFont(javafx.scene.text.Font.font(null, javafx.scene.text.FontWeight.BOLD, 12));
        xAxis.setTickLabelRotation(45);

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Total de Puntos");
        yAxis.setTickLabelFill(javafx.scene.paint.Color.WHITE);
        yAxis.setTickLabelFont(javafx.scene.text.Font.font(null, javafx.scene.text.FontWeight.BOLD, 14));
        yAxis.setMinorTickVisible(true);
        yAxis.setAutoRanging(true);

        barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Puntos Totales por Constructor");

        HBox controls = new HBox(yearComboBox, sortButton, backButton);
        controls.setSpacing(10);

        VBox vbox = new VBox(controls, barChart);
        vbox.setSpacing(10);
        vbox.setStyle("-fx-padding: 20px;");

        BorderPane root = new BorderPane(vbox);
        root.getStyleClass().add("border-pane-dark");

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateResults(int year) {
        ConstructorResultsController controller = new ConstructorResultsController(null);
        List<ConstructorResult> results = controller.getConstructorResultsByYear(year);
        resultsList = FXCollections.observableArrayList(results);
        sortResults();
    }

    private void sortResults() {
        if (resultsList != null) {
            Comparator<ConstructorResult> comparator = Comparator.comparing(ConstructorResult::getTotalPoints);
            if (!ascendingOrder) {
                comparator = comparator.reversed();
            }
            SortedList<ConstructorResult> sortedData = new SortedList<>(resultsList, comparator);

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Puntos Totales");

            for (ConstructorResult result : sortedData) {
                if (result.getTotalPoints() > 0) {
                    series.getData().add(new XYChart.Data<>(result.getName(), result.getTotalPoints()));
                }
            }

            barChart.getData().clear();
            barChart.getData().add(series);
        }
    }
}
