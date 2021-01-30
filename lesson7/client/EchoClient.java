package lesson7.client;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lesson7.client.controllers.ViewController;
import lesson7.client.models.Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;


public class EchoClient extends Application {

    public static final List<String> USERS_TEST_DATA = List.of("Boris_Nikolaevich", "Martin_Luther_Cat",
            "Gandalf_the_Gray");

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(EchoClient.class.getResource("views/chat-view.fxml"));

        Parent root = loader.load();

        primaryStage.setTitle("Messenger");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setY(1400);
        primaryStage.setX(650);
        primaryStage.show();

        Network network = new Network();
        network.connect();

        ViewController viewController = loader.getController();
        viewController.setNetwork(network);

        network.waitMessage(viewController);


    }

    public static void main(String[] args) {
        launch(args);
    }
}