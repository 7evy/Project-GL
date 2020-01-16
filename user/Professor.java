package user;

import java.util.ArrayList;

import tools.Query;
import study.Module;

public class Professor implements Role{

    private String login;
    private ArrayList<Module> modules;
    private ArrayList<ArrayList<Object>> students;

    public Professor(){}

    public Professor(String login){
        this.login = login;
        this.createModule();
    }

    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public ArrayList<Module> getModules() {
        return this.modules;
    }

    public void setModules(ArrayList<Module> modules) {
        this.modules = modules;
    }

    public void createModule(){
        modules = new ArrayList<>();

        ArrayList<String> array = new ArrayList<>();
        array = Query.coursesTaught(this.login);

        for (int i = 0 ; i < array.size() ; i++){
            modules.add(new Module(array.get(i)));
        }
    }

    public void createListStudent(String module){
        ArrayList<ArrayList<String>> array = new ArrayList<>();
        array = Query.attendees(module);
        if (!array.isEmpty()){
            for ( int i = 0; i < array.get(0).size(); i++){
                ArrayList<Object> student = new ArrayList<>();
                for (int j = 0; j < 3; j++){
                    student.add(array.get(j).get(i));
                }
                student.add(Query.studentAverage(module, (String) student.get(2)));
                students.add(student);
            }
        }
    }

    public void newAbsence(){
    }

    public ArrayList<String> createListModules(){
        ArrayList<String> array = new ArrayList<String>();
        for(int i = 0;i < this.modules.size();i++){
            array.add(this.modules.get(i).getName());
        }
        return array;
    }

    public ArrayList<ArrayList<String>> createTableAttendees(String moduleName){
        ArrayList<ArrayList<String>> array = new ArrayList<>();
        ArrayList<ArrayList<?>> attendees = Query.moduleStudentsAverage(moduleName);
        if(!attendees.isEmpty()){
            int size = attendees.get(0).size();
            for(int i = 0;i < size;i++){
                ArrayList<String> student = new ArrayList<String>();
                for(int j = 0;j < attendees.size();j++){
                    student.add(attendees.get(j).get(i).toString());
                }
                array.add(student);
            }
        }
        return array;
    }
    
    public ArrayList<ArrayList<String>> createListAttendees(){
        ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
        if(!this.students.isEmpty()){
            for(int i = 0;i < students.size();i++){
                ArrayList<String> student = new ArrayList<String>();
                student.add(this.students.get(i).get(0).toString());
                student.add(this.students.get(i).get(1).toString());
                array.add(student);
            }
        }
        return array;
    }

    public ArrayList<ArrayList<String>> createMarksAttendee(String moduleName, String login){
        ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<?>> exams = Query.exams(moduleName, login);
        if(!exams.isEmpty()){
            for(int i = 0;i < exams.get(0).size();i++){
                ArrayList<String> exam = new ArrayList<String>();
                for(int j = 0;j < exams.size();j++){
                    exam.add(exams.get(j).get(i).toString());
                }
                array.add(exam);
            }
        }
        return array;
    }

    public ArrayList<ArrayList<String>> createTableSatisfaction(String moduleName){
        ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();
        return array;
    }

}