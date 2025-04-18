import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentService studentService = new StudentService();
        AttendanceService attendanceService = new AttendanceService();
        GradeService gradeService = new GradeService();

        while (true) {
            System.out.println("\n===== Student Management System =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Edit Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Mark Attendance");
            System.out.println("6. View Attendance");
            System.out.println("7. Attendance Summary");
            System.out.println("8. Add Grade");
            System.out.println("9. Generate Report Card");
            System.out.println("10. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter class: ");
                    String className = sc.nextLine();
                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    studentService.addStudent(name, className, age);
                    break;

                case 2:
                    studentService.showAllStudents();
                    break;

                case 3:
                    System.out.print("Enter student ID to edit: ");
                    int editId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New name: ");
                    String newName = sc.nextLine();
                    System.out.print("New class: ");
                    String newClass = sc.nextLine();
                    System.out.print("New age: ");
                    int newAge = sc.nextInt();
                    studentService.updateStudent(editId, newName, newClass, newAge);
                    break;

                case 4:
                    System.out.print("Enter student ID to delete: ");
                    int delId = sc.nextInt();
                    studentService.deleteStudent(delId);
                    break;

                case 5:
                    System.out.print("Enter student ID: ");
                    int stuId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter date (yyyy-mm-dd): ");
                    String date = sc.nextLine();
                    System.out.print("Is present (true/false): ");
                    boolean isPresent = sc.nextBoolean();
                    attendanceService.markAttendance(stuId, date, isPresent);
                    break;

                case 6:
                    System.out.print("Enter student ID: ");
                    int viewId = sc.nextInt();
                    attendanceService.viewAttendance(viewId);
                    break;

                case 7:
                    System.out.print("Enter student ID: ");
                    int sumId = sc.nextInt();
                    attendanceService.showAttendanceSummary(sumId);
                    break;

                case 8:
                    System.out.print("Enter student ID: ");
                    int sid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter subject: ");
                    String subject = sc.nextLine();
                    System.out.print("Enter grade: ");
                    String grade = sc.nextLine();
                    gradeService.addGrade(sid, subject, grade);
                    break;

                case 9:
                    System.out.print("Enter student ID: ");
                    int gid = sc.nextInt();
                    gradeService.generateReportCard(gid);
                    break;

                case 10:
                    System.out.println("👋 Exiting...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("❌ Invalid option. Try again.");
            }
        }
    }
}
