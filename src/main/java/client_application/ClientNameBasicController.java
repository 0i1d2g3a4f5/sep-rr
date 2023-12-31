package client_application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import messages.MessagePlayerValues;

import java.util.List;
/**
 * @author Sarp Erdogan
 */
public class ClientNameBasicController {
    ClientApplication clientApplication;

    List<CheckBox> checkBoxList;
    int currentFigure=7;
    String currentName;
    boolean active=true;

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
    private GridPane checkGrid;

    @FXML
    private Text feedBack;

    @FXML
    private TextField nameInput;


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
            } else {
                active=false;
                currentName = nameInput.getText().trim();
                clientApplication.basicClient.sendSelf(new MessagePlayerValues(currentName, currentFigure));
            }
        }
    }

    /**
     * selects new checkbox of selected robot and unselects current selected (if it exists)
     *
     * @param index
     */
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

    public void setFeedback(String string)
    {
        feedBack.setText(string);
    }

    public void activate()
    {
        active=true;
    }

}
