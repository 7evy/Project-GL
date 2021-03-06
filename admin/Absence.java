package admin;

import java.sql.Date;
import java.sql.Time;

/**
 * 
 * This class contains all the methods and attributs linked with the management of absences
 * @author Sébastien HERT 
 * @author Dejan PARIS
 * 
 */

public class Absence {

	private Date beginDate;
	private Date endDate;
	private Time beginHour;
	private Time endHour;
	private String loginStudent;
	private String moduleName;
    private String loginTeacher;
    private Boolean justified;


    /**
     * Constructor
     */
	public Absence () {

    }
    
    /**
     * Constructor
     * @param beginDate date of the begining of the absence
     * @param beginHour hour of the begining of the absence
     * @param endDate date of the end of the absence
     * @param endHour hour of the end of the absence
     * @param justified justification of the absence
     */
    public Absence (Date beginDate, Time beginHour, Date endDate, Time endHour, Boolean justified) {
        this.beginDate = beginDate;
        this.beginHour = beginHour;

        this.endDate = endDate;
        this.endHour = endHour;


        this.justified = justified;
    }

    public Absence (Date beginDate, Time beginHour, Date endDate, Time endHour, Boolean justified, String module) {
        this.beginDate = beginDate;
        this.beginHour = beginHour;

        this.endDate = endDate;
        this.endHour = endHour;

        this.justified = justified;

        this.moduleName = module;
    }
    
    /**
     * Constructor
     * @param date date of the absence
     * @param beginHour hour of the begining of the absence
     * @param endHour hour of the end of the absence
     * @param loginStudent login of the absent student
     * @param loginTeacher login of the teacher
     */
    public Absence(Date date, Time beginHour, Time endHour, String loginStudent, String loginTeacher){
        this.beginDate = date;
        this.endDate = date;
        this.beginHour = beginHour;
        this.endHour = endHour;
        this.loginStudent = loginStudent;
        this.loginTeacher = loginTeacher;
    }


    /**
     * @return Date return the beginDate
     */
    public Date getBeginDate() {
        return beginDate;
    }

    /**
     * @param beginDate the beginDate to set
     */
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * @return Date return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return Time return the beginHour
     */
    public Time getBeginHour() {
        return beginHour;
    }

    /**
     * @param beginHour the beginHour to set
     */
    public void setBeginHour(Time beginHour) {
        this.beginHour = beginHour;
    }

    /**
     * @return Time return the endHour
     */
    public Time getEndHour() {
        return endHour;
    }

    /**
     * @param endHour the endHour to set
     */
    public void setEndHour(Time endHour) {
        this.endHour = endHour;
    }

    /**
     * @return Student return the student
     */
    public String getStudent() {
        return loginStudent;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(String student) {
        this.loginStudent = student;
    }

    /**
     * @return Module return the module
     */
    public String getModuleName() {
        return moduleName;
    }

    /**
     * @param module the module to set
     */
    public void setModuleName(String module) {
        this.moduleName = module;
    }

    /**
     * @return Teacher return the teacher
     */
    public String getTeacher() {
        return loginTeacher;
    }

    /**
     * @param teacher the teacher to set
     */
    public void setTeacher(String teacher) {
        this.loginTeacher = teacher;
	}
    
    /**
     * @return justify
     */
    public Boolean isJustified() {
        return this.justified;
    }

    /**
     * @param justified boolean to set
     */
    public void setJustified(Boolean justified) {
        this.justified = justified;
    }

    /**
     * Tests if an absence is equal to an other
     * @author Dejan PARIS
     * @param absence
     * @return
     */
    public boolean equals(Absence absence)
    {
        return this.loginStudent.equals(absence.getStudent()) && this.moduleName.equals(absence.getModuleName()) && this.beginDate.equals(absence.getBeginDate()) && this.beginHour.equals(absence.getBeginHour()) && this.endDate.equals(absence.getEndDate()) && this.endHour.equals(absence.getEndHour());
    }

}