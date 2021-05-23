/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 03 - Student Enrollment
 * Student(s) Name(s):  Eesha Patel , Kyle Weidner, Selamawit Abdo
 */


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;


/**
 * The Class Enrollment.
 */
@Entity
@Table(name = "enrollments")

public class Enrollment implements Serializable {

    /** The code. */
    @Id
    private String code;

    /** The id. */
    private int id;

    /**
     * Gets the id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the code.
     *
     * @param code the new code
     */
    public void setCode(String code) {
        this.code = code;
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
        if (!(o instanceof Enrollment)) return false;
        Enrollment that = (Enrollment) o;
        return getId () == that.getId () && getCode ().equals (that.getCode ());
    }

    /**
     * Hash code.
     *
     * @return the int
     */
    @Override
    public int hashCode() {
        return Objects.hash (getCode (), getId ());
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return "Enrollment{" +
                "code='" + code + '\'' +
                ", id=" + id +
                '}';
    }
}
