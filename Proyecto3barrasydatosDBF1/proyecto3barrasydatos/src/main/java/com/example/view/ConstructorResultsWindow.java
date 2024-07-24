package com.example.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.example.App;
import com.example.Controllers.ConstructorResultsController;
import com.example.Models.ConstructorResult;

import java.util.Comparator;
import java.util.List;

public class ConstructorResultsWindow extends Application {

    private TableView<ConstructorResult> tableView;
    private FilteredList<ConstructorResult> filteredData;
    private boolean ascendingOrder = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Resultados de Constructores");

        ComboBox<Integer> yearComboBox = new ComboBox<>();
        for (int year = 1950; year <= 2023; year++) {
            yearComboBox.getItems().add(year);
        }
        yearComboBox.setOnAction(event -> {
            int selectedYear = yearComboBox.getValue();
            updateResults(selectedYear);
        });

        TextField searchField = new TextField();
        searchField.setPromptText("Buscar...");
        searchField.textProperty().addListener((observable, oldValue, newValue) ->
            filteredData.setPredicate(result ->
                result.getName().toLowerCase().contains(newValue.toLowerCase())
            )
        );

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

        Button sortButton = new Button("Ordenar Mayor a Menor");
        sortButton.setOnAction(event -> {
            ascendingOrder = !ascendingOrder;
            sortButton.setText(ascendingOrder ? "Ordenar Mayor a Menor" : "Ordenar Menor a Mayor");
            sortResults();
        });

        tableView = new TableView<>();

        TableColumn<ConstructorResult, String> nameColumn = new TableColumn<>("Nombre");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<ConstructorResult, Integer> winsColumn = new TableColumn<>("Victorias");
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("wins"));

        TableColumn<ConstructorResult, Float> pointsColumn = new TableColumn<>("Puntos");
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("totalPoints"));

        TableColumn<ConstructorResult, Integer> rankColumn = new TableColumn<>("Rango");
        rankColumn.setCellValueFactory(new PropertyValueFactory<>("seasonRank"));

        tableView.getColumns().addAll(nameColumn, winsColumn, pointsColumn, rankColumn);

        HBox controls = new HBox(yearComboBox, searchField, sortButton, backButton);
        controls.setSpacing(10);

        VBox vbox = new VBox(controls, tableView);
        vbox.setSpacing(10);
        vbox.setStyle("-fx-padding: 10px;");
        BorderPane root = new BorderPane(vbox);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateResults(int year) {
        ConstructorResultsController controller = new ConstructorResultsController(tableView);
        List<ConstructorResult> results = controller.getConstructorResultsByYear(year);
        filteredData = new FilteredList<>(FXCollections.observableArrayList(results));
        sortResults();
    }

    private void sortResults() {
        Comparator<ConstructorResult> comparator = Comparator.comparing(ConstructorResult::getTotalPoints);
        if (!ascendingOrder) {
            comparator = comparator.reversed();
        }
        SortedList<ConstructorResult> sortedData = new SortedList<>(filteredData);
        sortedData.setComparator(comparator);
        tableView.setItems(sortedData);
    }
}
