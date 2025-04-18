import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GradeService {

    //  Add Grade
    public void addGrade(int studentId, String subject, String grade) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "INSERT INTO grades(student_id, subject, grade) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, studentId);
            ps.setString(2, subject);
            ps.setString(3, grade);
            ps.executeUpdate();
            System.out.println("✅ Grade added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  Generate Report Card
    public void generateReportCard(int studentId) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM grades WHERE student_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            boolean isFail = false;
            boolean hasGrades = false;

            System.out.println("\n📄 Report Card:");
            System.out.println("Student ID: " + studentId);

            while (rs.next()) {
                hasGrades = true;
                String subject = rs.getString("subject");
                String grade = rs.getString("grade");
                System.out.println("Subject: " + subject + " | Grade: " + grade);

                if (grade.equalsIgnoreCase("F")) {
                    isFail = true;
                }
            }

            if (!hasGrades) {
                System.out.println("❌ No grades found for this student.");
            } else {
                System.out.println("\n🎯 Overall Result: " + (isFail ? "❌ Fail" : "✅ Pass"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
