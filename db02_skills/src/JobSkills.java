/*
 * CS3810 - Principles of Database Systems - Spring 2021
 * Instructor: Thyago Mota
 * Description: DB 02 - JobSkills
 * Student(s) Name(s): Eesha Patel
 */

import java.io.*;
import java.sql.*;
import java.util.*;

public class JobSkills {

    public static String DATASET = "data/job_skills.csv";
    public static String CONFIG = "data/config.properties";
    private static Connection conn;

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        // TODO: load database properties
        Properties prop = new Properties();
        prop.load(new FileInputStream(CONFIG));

        String server = prop.getProperty("server");
        String database = prop.getProperty("database");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        
        String connectURL = "jdbc:mysql://" + server + "/" + database + "?serverTimezone=UTC&user=" + user + "&password=" + password;

        // TODO: connect to the database
        conn = DriverManager.getConnection(connectURL);
         System.out.println("Connection to MySQL database " + database + " was successful!");
         splitFile(System.getProperty("user.dir") + "/", DATASET);
    }
    
    /*
    HERE IS WHAT YOU NEED TO CHANGE
    */ 

    public static void splitFile(String directory, String fileName) throws IOException, SQLException {

        // TODO: complete the data load
        try {
            File oldFile = new File(directory + DATASET);
            Scanner sc = new Scanner(oldFile);
            PreparedStatement insertJobs = conn.prepareStatement("INSERT ignore INTO Jobs (id, jobTitle) values (?, ?)");
            PreparedStatement insertSkills = conn.prepareStatement("INSERT ignore INTO Skills (skillsd, skillName) values (?, ?)");
            PreparedStatement insertJobSkills = conn.prepareStatement("INSERT ignore INTO job_skills (job_id, skill_id) values (?, ?)");
            conn.setAutoCommit(false);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] data = line.split(",");
                String[] data2 = data[0].split(":");
                int jId = Integer.parseInt(data2[0]);
                String jTitle = data2[1];
                String[] sArray = data[1].split(";");
                
                insertJobs.setInt(1, jId);
                insertJobs.setString(2, jTitle);
                insertJobs.addBatch();

                for(String data3: sArray) {
                    String[] skill = data3.split(":");
                    int sId = Integer.parseInt(skill[0]);
                    String sName = skil[1];

                    insertSkills.setInt(1, sId);
                    insertSkills.setString(2, sName);
                    insertSkills.addBatch();

                    insertJobSkills.setInt(1, jId);
                    insertJobSkills.setInt(2, sId);
                    insertJobSkills.addBatch();
                }
            }

            insertJobs.executeBatch();
            insertSkills.executeBatch();
            insertJobSkills.executeBatch();
            conn.commit();
            conn.setAutoCommit(true);

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        System.out.println("Goodbye!");
        conn.close();
    }
}
