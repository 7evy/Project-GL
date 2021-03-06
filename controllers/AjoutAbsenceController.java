package controllers;

import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import tools.Tool;

import tools.Query;
import tools.SQL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import tools.Stockage;
import user.Professor;


/**
 * 
 * This class contains all the methods and attributs linked with the management of absences
 * @author Alex JOBARD
 * @author Adam RIVIERE
 * 
 */

public class AjoutAbsenceController extends ControllerAbs {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane anchorAddNonattendance;

    @FXML
    private MenuItem backMenu;

    @FXML
    private MenuItem quitMenu;

    @FXML
    private Button addNonattendanceButton;

    @FXML
    private DatePicker dateNonattendancePicker;

    @FXML
    private ComboBox<String> comboNonattendanceStudent;

    @FXML
    private ComboBox<String> comboModuleNonattendance;

    @FXML
    private ComboBox<String> startingHourCombo;

    @FXML
    private ComboBox<String> startingMinuteCombo;

    @FXML
    private ComboBox<String> endingHourCombo;

    @FXML
    private ComboBox<String> endingMinuteFinCombo;

    String module = new String("");

    /**
     * Displays an error window
     * @author Alex JOBARD
     */
    private void alertFill(){
        Alert alertLogin = new Alert(Alert.AlertType.WARNING);
        alertLogin.setTitle("Incomplet");
        alertLogin.setContentText("Merci de remplir tous les champs");
        alertLogin.showAndWait();
    }

    /**
     * Displays an error window
     * @author Alex JOBARD
     * @author Adam RIVIERE
     */
    @FXML
    void addNonattendance(ActionEvent event) {
        String professor = ((Professor) Stockage.getPerson().getRole()).getLogin();
        String moduleValue = comboModuleNonattendance.getValue();
        String studentValue = Tool.getLogin(comboNonattendanceStudent.getValue());
        String startingHour = startingHourCombo.getValue();
        String startingMinute = startingMinuteCombo.getValue();
        String endingHour = endingHourCombo.getValue();
        String endingMinute = endingMinuteFinCombo.getValue();
        LocalDate date = dateNonattendancePicker.getValue();
        if(!((moduleValue == null) || (studentValue == null) || (startingHour == null) || (startingMinute == null) || (endingHour == null) || (endingMinute == null) || (date == null))){
            java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(date);
            Time beginTime = Tool.parseTime(Tool.addingZero(startingHour)+"H"+Tool.addingZero(startingMinute));
            Time endTime = Tool.parseTime(Tool.addingZero(endingHour)+"H"+Tool.addingZero(endingMinute));
            SQL.createAbsence(beginTime, endTime, moduleValue, studentValue, professor, gettedDatePickerDate);
        }else{
            alertFill();
        }
    }

    /**
     * Quits the application
     * @author Alex JOBARD
     */
    @FXML
    void quitFunction(ActionEvent event) {
        fromAnchorClose(anchorAddNonattendance);
    }

    /**
     * Returns to the previous window
     * @author Alex JOBARD
     */
    @FXML
    void backFunction(ActionEvent event) throws Exception {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../scenes/vue_prof_selection_note.fxml"));

        Scene sceneFromAnchor = anchorAddNonattendance.getScene();
        sceneFromAnchor.setRoot(pane);

    }

    /**
     * Gets the selected module
     * @author Alex JOBARD
     */
    @FXML
    void selectionModulenonattendance(ActionEvent event) {
        module = comboModuleNonattendance.getValue();
        if(!module.equalsIgnoreCase("")) {
            setStudent();
        }
    }

    /**
     * Creates a combobx
     * @author Alex JOBARD
     * @param combobox combobox to set
     */
    public void setMinute(ComboBox<String> combobox) {
        combobox.getItems().clear();
        int i;
        for (i = 0; i < 60; i++) {
            combobox.getItems().add(Integer.toString(i));
        }
    }

    /**
     * Creates a combobox
     * @author Alex JOBARD
     * @param combobox combobox to set
     */
    public void setHour(ComboBox<String> combobox) {
        combobox.getItems().clear();
        int i;
        for (i = 0; i < 24; i++) {
            combobox.getItems().add(Integer.toString(i));
        }

    }

    /**
     * Creates a combobox
     * @author Alex JOBARD
     * @param combobox combobox to set
     */
    public void setModule() {
        comboModuleNonattendance.getItems().clear();
        ArrayList<String> array = ((Professor) Stockage.getPerson().getRole()).viewListModules();
        for (int i = 0; i< array.size(); i++){
            comboModuleNonattendance.getItems().add(array.get(i));
        }
        comboModuleNonattendance.setEditable(false);
    }

    /**
     * Fills the students list
     * @author Alex JOBARD
     * @author Adam RIVIERE
     */
    public void setStudent(){
        comboNonattendanceStudent.getItems().clear();
        ArrayList<ArrayList<String>> array = Query.attendees(module);
            ArrayList<ArrayList<String>> students = new ArrayList<ArrayList<String>>();
            if(!array.isEmpty()){
                for(int i = 0;i < array.get(0).size();i++){
                    ArrayList<String> student = new ArrayList<String>();
                    student.add(array.get(0).get(i).toString());
                    student.add(array.get(1).get(i).toString());
                    student.add(array.get(2).get(i).toString());
                    students.add(student);
                }
                for (int i= 0; i< students.size(); i++){
                    comboNonattendanceStudent.getItems().add(Tool.stringForStudent(students.get(i).get(0),
                                                                    students.get(i).get(1),
                                                                    students.get(i).get(2)));
                }
            }
    }

    /**
     * Initializes the window
     * @author Alex JOBARD
     */
    @FXML
    void initialize() {
        setMinute(startingMinuteCombo);
        setMinute(endingMinuteFinCombo);
        setHour(startingHourCombo);
        setHour(endingHourCombo);
        setStudent();
        setModule();

    }
}
