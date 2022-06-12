package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import newmessages.MessageHashedCode;
import newmessages.MessagePlayerValues;

import java.util.ArrayList;
import java.util.List;

public class NameController {
    Application application;
    List<CheckBox> checkBoxList;
    int currentFigure;
    String pass, currentName;
    boolean active=true;
    @FXML
    private GridPane checkGrid;
    @FXML
    private CheckBox check1;

    @FXML
    private CheckBox check2;

    @FXML
    private CheckBox check3;

    @FXML
    private CheckBox check4;

    @FXML
    private CheckBox check5;

    @FXML
    private CheckBox check6;
    @FXML
    private Text feedBack;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField passInput;

    @FXML
    void choose1(ActionEvent event) {
        if(check1.isSelected()){
            selectAndUncheckCurrent(1);
        }
        else{
            unselect(check1);
        }
    }

    @FXML
    void choose2(ActionEvent event) {
        if(check2.isSelected()){
            selectAndUncheckCurrent(2);
        }
        else{
            unselect(check2);
        }
    }

    @FXML
    void choose3(ActionEvent event) {
        if(check3.isSelected()){
            selectAndUncheckCurrent(3);
        }
        else{
            unselect(check3);
        }
    }

    @FXML
    void choose4(ActionEvent event) {
        if(check4.isSelected()){
            selectAndUncheckCurrent(4);
        }
        else{
            unselect(check4);
        }
    }

    @FXML
    void choose5(ActionEvent event) {
        if(check5.isSelected()){
            selectAndUncheckCurrent(5);
        }
        else{
            unselect(check5);
        }
    }

    @FXML
    void choose6(ActionEvent event) {
        if(check6.isSelected()){
            selectAndUncheckCurrent(6);
        }
        else{
            unselect(check6);
        }
    }

    @FXML
    void connectEnter(KeyEvent event) {
        if(event.getCode()== KeyCode.ENTER){
            submit();
        }
    }

    @FXML
    void startButton(ActionEvent event) {
        submit();

    }
    void submit(){
        if(active){
            if (currentFigure == 7) {
                feedBack.setText("Select valid figure.");
            } else if (nameInput.getText().trim() == "") {
                feedBack.setText("Select valid name.");
            } else if (passInput.getText().trim().length() < 4) {
                feedBack.setText("Select valid pass with at least 4 characters.");
            } else {
                active=false;
                currentName = nameInput.getText().trim();
                pass = application.client.getNewEncoded(passInput.getText().trim());
                application.client.sendSelf(new MessagePlayerValues(nameInput.getText().trim(), currentFigure));

            }
        }

    }
    void initialize(){
        currentFigure=7;
    }
    void selectAndUncheckCurrent(int index){
        CheckBox temp1 = (CheckBox) checkGrid.getChildren().get(index-1);
        temp1.setSelected(true);
        if(currentFigure!=7) {
            CheckBox temp2 = (CheckBox) checkGrid.getChildren().get(currentFigure - 1);
            temp2.setSelected(false);
        }
        currentFigure = index;
    }
    void unselect(CheckBox checkBox){
        checkBox.setSelected(false);
        currentFigure=7;
    }
    public void setFeedback(String string){
        feedBack.setText(string);
    }
    public void activeTrue(){
        active=true;
    }

}
