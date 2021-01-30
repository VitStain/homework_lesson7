package lesson7.client.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lesson7.client.EchoClient;
import lesson7.client.models.Network;

import java.io.IOException;

public class ViewController {

    @FXML
    public ListView<String> usersList;

    @FXML
    private Button sendButton;
    @FXML
    private TextArea chatHistory;
    @FXML
    private TextField textField;

    private Network network;

    public void setNetwork(Network network) {
        this.network = network;
    }

    @FXML
    public void initialize() {
        usersList.setItems(FXCollections.observableArrayList(EchoClient.USERS_TEST_DATA));
        sendButton.setOnAction(event -> ViewController.this.sendMessage());
        textField.setOnAction(event -> ViewController.this.sendMessage());
    }

    private void sendMessage() {
        String message = textField.getText();
//        appendMessage(message);
        textField.clear();

        try {
            network.getOut().writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при отправке сообщения");
        }

    }

    public void appendMessage(String message) {
        chatHistory.appendText(message);
        chatHistory.appendText(System.lineSeparator());
    }

}