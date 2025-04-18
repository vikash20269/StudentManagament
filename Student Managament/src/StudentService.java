import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StudentService {

    public void addStudent(String name, String studentClass, int age) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "INSERT INTO students(name, class, age) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, studentClass);
            ps.setInt(3, age);
            ps.executeUpdate();
            System.out.println("✅ Student added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAllStudents() {
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM students";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n👨‍🎓 All Students:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Class: " + rs.getString("class") +
                        ", Age: " + rs.getInt("age"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  Update Student Info
    public void updateStudent(int id, String name, String studentClass, int age) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "UPDATE students SET name=?, class=?, age=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, studentClass);
            ps.setInt(3, age);
            ps.setInt(4, id);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("✅ Student updated successfully.");
            else
                System.out.println("❌ Student not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  Delete Student
    public void deleteStudent(int id) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "DELETE FROM students WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("🗑️ Student deleted successfully.");
            else
                System.out.println("❌ Student not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
