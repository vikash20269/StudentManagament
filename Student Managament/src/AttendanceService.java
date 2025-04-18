import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AttendanceService {

    //  Mark Attendance
    public void markAttendance(int studentId, String date, boolean isPresent) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "INSERT INTO attendance(student_id, date, is_present) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, studentId);
            ps.setString(2, date);
            ps.setBoolean(3, isPresent);
            ps.executeUpdate();
            System.out.println("✅ Attendance marked for student ID: " + studentId + " on " + date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  View Attendance
    public void viewAttendance(int studentId) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT * FROM attendance WHERE student_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n📅 Attendance Records for Student ID: " + studentId);
            while (rs.next()) {
                String date = rs.getString("date");
                boolean isPresent = rs.getBoolean("is_present");
                System.out.println("Date: " + date + " | Present: " + (isPresent ? "Yes" : "No"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  Show Attendance Summary (Percentage of attendance)
    public void showAttendanceSummary(int studentId) {
        try (Connection con = DBConnection.getConnection()) {
            String query = "SELECT COUNT(*) AS total, SUM(CASE WHEN is_present = true THEN 1 ELSE 0 END) AS present FROM attendance WHERE student_id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int total = rs.getInt("total");
                int present = rs.getInt("present");
                double attendancePercentage = ((double) present / total) * 100;

                System.out.println("\n📊 Attendance Summary for Student ID: " + studentId);
                System.out.println("Total Classes: " + total);
                System.out.println("Classes Attended: " + present);
                System.out.println("Attendance Percentage: " + String.format("%.2f", attendancePercentage) + "%");
            } else {
                System.out.println("❌ No attendance records found for this student.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
