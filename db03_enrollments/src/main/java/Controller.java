/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 03 - Student Enrollment
 * Student(s) Name(s):  Eesha Patel , Kyle Weidner, Selamawit Abdo
 */

import org.hibernate.Session;
import org.hibernate.Transaction;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;


/**
 * The Class Controller.
 */
public class Controller {

    /** The em. */
    private EntityManager em;

    /** The session. */
    private Session session;

    /**
     * Instantiates a new controller.
     */
    public Controller() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("db03");
        em = emf.createEntityManager();
        session = em.unwrap(Session.class);
    }

    /**
     * Gets the student.
     *
     * @param id the id
     * @return the student
     */
    // TODOd: return a Student entity from the given id (or null if the entity does not exist)
    public Student getStudent(int id) {
        try {
            return em.getReference(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Adds the student.
     *
     * @param student the student
     * @return true, if successful
     */
    // TODOd: add the given student entity, returning true/false depending whether the operation was successful or not
    public boolean addStudent(final Student student) {
        try {
            em.persist(student);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Gets the courses.
     *
     * @return the courses
     */
    // TODOd: return a list of all Course entities
    public List<Course> getCourses() {
        List<Course> courses = session.createQuery("FROM Course").getResultList();
        return courses;
    }

    /**
     * Enroll student.
     *
     * @param code the code
     * @param id the id
     * @return true, if successful
     */
    // TODOd: enroll a student to a course based on the given parameters, returning true/false depending whether the operation was successful or not
    public boolean enrollStudent(String code, int id) {
        try{
            session.beginTransaction();
            Enrollment enroll = new Enrollment();
            enroll.setCode(code);
            enroll.setId(id);
            session.save(enroll);
            session.getTransaction().commit();
            session.evict(enroll);
            session.clear();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * Drop student.
     *
     * @param code the code
     * @param id the id
     * @return true, if successful
     */
    // TODOd: drop a student from a course based on the given parameters, returning true/false depending whether the operation was successful or not
    public boolean dropStudent(String code, int id) {
        try{
            Transaction transaction = session.beginTransaction();
            String sql = ("DELETE FROM Enrollment WHERE code= :code AND id= :id");
            Query query = session.createQuery(sql);
            query.setParameter("code", code);
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }


    /**
     * Gets the students enrolled.
     *
     * @param course the course
     * @return the students enrolled
     */
    // TODOd: return a list of all Student entities enrolled in the given course (hint: use the stored procedure 'list_students')
    public List<Student> getStudentsEnrolled(String course) {
        List<Student> students = (List<Student>) session.createSQLQuery("CALL list_students(:course_code)")
                .addEntity(Student.class)
                .setParameter("course_code", course).list();
        return students;
    }
}
