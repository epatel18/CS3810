import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;


/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 03 - Student Enrollment
 * Student(s) Name(s):  Eesha Patel , Kyle Weidner, Selamawit Abdo
 */

/**
 * The Class Course.
 */
@Entity
@Table(name = "courses")

public class Course implements Serializable {

    /** The code. */
    @Id
    private String code;

    /** The max. */
    private int max;

    /** The actual. */
    private int actual;

    /** The title. */
    private String title;

    /** The instructor. */
    private String instructor;

    /** The remain. */
    private int remain;

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Gets the max.
     *
     * @return the max
     */
    public int getMax() {
        return max;
    }

    /**
     * Sets the max.
     *
     * @param max the new max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Gets the actual.
     *
     * @return the actual
     */
    public int getActual() {
        return actual;
    }

    /**
     * Sets the actual.
     *
     * @param actual the new actual
     */
    public void setActual(int actual) {
        this.actual = actual;
    }

    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title.
     *
     * @param title the new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the instructor.
     *
     * @return the instructor
     */
    public String getInstructor() {
        return instructor;
    }

    /**
     * Sets the instructor.
     *
     * @param instructor the new instructor
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    /**
     * Gets the remain.
     *
     * @return the remain
     */
    public int getRemain() {
        return this.max - this.actual;
    }

    /**
     * Sets the remain.
     *
     * @param remain the new remain
     */
    public void setRemain(int remain) {
        this.remain = remain;
    }

    /**
     * Equals.
     *
     * @param o the o
     * @return true, if successful
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return getMax () == course.getMax () && getActual () == course.getActual () && getRemain () == course.getRemain () && getCode ().equals (course.getCode ()) && getTitle ().equals (course.getTitle ()) && getInstructor ().equals (course.getInstructor ());
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash (getCode (), getMax (), getActual (), getTitle (), getInstructor (), getRemain ());
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override public String toString() {
        return   String.format("%-7s| %-40s| %-15s| %-3s | %-6s | %-3s",
                code , title ,instructor , max ,actual , getRemain () );
    }
}
