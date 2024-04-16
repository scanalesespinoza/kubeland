package org.scanalesespinoza.kubeland;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Welcome to JavaFX in Kubeland!");
        Separator separator = new Separator();
        StackPane root = new StackPane();
        root.getChildren().add(label);
        root.getChildren().add(separator);

        Button btnOpenForm = new Button("Enter Credentials");
        btnOpenForm.setOnAction(e -> CredentialForm.display());
        root.getChildren().addLast(btnOpenForm);

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Hello Kubeland!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
