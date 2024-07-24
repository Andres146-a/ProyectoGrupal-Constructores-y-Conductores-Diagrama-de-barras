package com.example;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import com.example.view.DriverResultsWindow;
import com.example.view.ConstructorResultsWindow;
import com.example.view.DriverBarChartWindow;
import com.example.view.ConstructorBarChartWindow;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Resultados: Conductores y Constructores");

        // Cargar el ícono
        Image icon = new Image(getClass().getResourceAsStream("/imagen/OIG4.jpeg"));
        if (icon.isError()) {
            System.out.println("Error al cargar el ícono.");
        } else {
            primaryStage.getIcons().add(icon);
        }

        Button driverButton = new Button("Ver Resultados de Conductores");
        driverButton.setOnAction(e -> {
            DriverResultsWindow driverResultsWindow = new DriverResultsWindow();
            Stage driverStage = new Stage();
            driverResultsWindow.start(driverStage);
            primaryStage.close();
        });

        Button constructorButton = new Button("Ver Resultados de Constructores");
        constructorButton.setOnAction(e -> {
            ConstructorResultsWindow constructorResultsWindow = new ConstructorResultsWindow();
            Stage constructorStage = new Stage();
            constructorResultsWindow.start(constructorStage);
            primaryStage.close();
        });

        Button driverBarButton = new Button("Ver Barra de Conductores");
        driverBarButton.setOnAction(e -> {
            DriverBarChartWindow driverBarChartWindow = new DriverBarChartWindow();
            Stage driverBarStage = new Stage();
            driverBarChartWindow.start(driverBarStage);
            primaryStage.close();
        });

        Button constructorBarButton = new Button("Ver Barra de Constructores");
        constructorBarButton.setOnAction(e -> {
            ConstructorBarChartWindow constructorBarChartWindow = new ConstructorBarChartWindow();
            Stage constructorBarStage = new Stage();
            constructorBarChartWindow.start(constructorBarStage);
            primaryStage.close();
        });

        HBox buttonBox = new HBox(20, driverButton, constructorButton, driverBarButton, constructorBarButton);
        buttonBox.setAlignment(Pos.CENTER);

        BorderPane root = new BorderPane();
        root.setCenter(buttonBox);
        root.getStyleClass().add("border-pane");

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        // Añadir imagen de fondo
        Image backgroundImage = new Image(getClass().getResourceAsStream("/imagen/F1.jpg"));
        if (backgroundImage.isError()) {
            System.out.println("Error al cargar la imagen de fondo.");
        } else {
            BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            root.setBackground(new Background(background));
        }

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
