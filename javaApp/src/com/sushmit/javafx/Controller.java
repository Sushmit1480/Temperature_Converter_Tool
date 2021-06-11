package com.sushmit.javafx;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public Label welcomeLabel;             // Define the attribute

    @FXML
    public ChoiceBox<String> choiceBox;    // Define the attribute

    @FXML
    public TextField textField;            // Define the attribute

    @FXML
    public Button convertButton;           // Define the attribute

    //Creating Constant values for the values in the choice box
    private static final String C_TO_F_TEXT = "Celsius to Fahrenheit";
    private static final String F_TO_C_TEXT = "Fahrenheit to Celsius";

    private boolean isC_TO_F = true;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        choiceBox.getItems().add(C_TO_F_TEXT);       //Add option in the choice box
        choiceBox.getItems().add(F_TO_C_TEXT);       //Add option in the choice box

        choiceBox.setValue(C_TO_F_TEXT);             //Default Value of the choice box

        //Onclick Function for choiceBox
        choiceBox.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals(C_TO_F_TEXT)){
                isC_TO_F = true;
            }else{
                isC_TO_F = false;
            }
        });

        //Set Onclick function of the button.
        convertButton.setOnAction(event -> {
            convert();
        });
    }

    private void convert() {
        String input = textField.getText();           // Input of the temperature

        float enteredTemperature = 0.0f;
        try{
            enteredTemperature = Float.parseFloat(input);     //Convert the string into the float value
        }catch (Exception e){
            warnUser();
            return;
        }
        float newTemperature = 0.0f;

        if(isC_TO_F){
            newTemperature = (enteredTemperature * 9 / 5) + 32;
        }else{
            newTemperature = (enteredTemperature - 32) * 5 / 9;
        }

        display(newTemperature);
    }

    private void warnUser() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Occurred");
        alert.setHeaderText("Invalid Temperature Entered");
        alert.setContentText("Please Enter a Valid Temperature");
        alert.show();
    }

    private void display(float newTemperature) {
        String unit = isC_TO_F? " F": " C";
        //System.out.println("New temperature is: "+ newTemperature + unit);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setContentText("New temperature is: "+ newTemperature + unit);
        alert.show();
    }
}

