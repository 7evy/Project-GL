package study;

import tools.Query;

/**
 * 
 * This class contains all methods and attributes linked to a Teaching unit
 * made of modules and which are included in the schedule for a graduating class.
 * @author Sébastien HERT 
 * 
 */
public class TeachingUnit {

    private String name;
    private Course course;

    public Course getCourse() {
        return this.course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public TeachingUnit() {}

    public TeachingUnit(String name) {
        this.name = name;
        this.course = new Course(Query.courseOfTU(name));
    }


    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public boolean equals(TeachingUnit unit)
    {
        return this.name.equals(unit.name) && this.course.equals(unit.course);
    }

}