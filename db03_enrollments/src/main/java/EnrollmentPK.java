/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 03 - Student Enrollment
 * Student(s) Name(s):  Eesha Patel , Kyle Weidner, Selamawit Abdo
 */


import java.io.Serializable;
import javax.persistence.Embeddable;


/**
 * The Class EnrollmentPK.
 */
@Embeddable
public class EnrollmentPK implements Serializable {

    /** The code. */
    private String code;

    /** The id. */
    private int id;

    /**
     * Instantiates a new enrollment PK.
     *
     * @param studentId the student id
     * @param courseCode the course code
     */
    public EnrollmentPK(int studentId, String courseCode) {
        super();
        this.code = courseCode;
        this.id = studentId;
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
     * Instantiates a new enrollment PK.
     */
    public EnrollmentPK() {

    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override public String toString() {
        return "EnrollmentPK{" +
                "code='" + code + '\'' +
                ", id=" + id +
                '}';
    }
}

