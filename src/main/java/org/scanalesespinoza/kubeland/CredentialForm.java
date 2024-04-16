package org.scanalesespinoza.kubeland;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;

public class CredentialForm {
    private static File kubeConfigFile;

    public static void display() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Load KubeConfig");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Kubeconfig file chooser
        Button loadKubeConfigButton = new Button("Load KubeConfig");
        GridPane.setConstraints(loadKubeConfigButton, 0, 0);
        loadKubeConfigButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open KubeConfig File");
            kubeConfigFile = fileChooser.showOpenDialog(window);
            if (kubeConfigFile != null) {
                loadKubeConfigButton.setText("Loaded: " + kubeConfigFile.getName());
            }
        });

        // Connect button
        Button connectButton = new Button("Connect");
        GridPane.setConstraints(connectButton, 0, 1);
        connectButton.setOnAction(e -> {
            KubernetesConnector connector = new KubernetesConnector();
            if (connector.connectUsingKubeConfig(kubeConfigFile)) {
                System.out.println("Connection successful");
            } else {
                System.out.println("Connection failed");
            }
            window.close();
        });

        grid.getChildren().addAll(loadKubeConfigButton, connectButton);

        Scene scene = new Scene(grid, 300, 100);
        window.setScene(scene);
        window.showAndWait();
    }
}
