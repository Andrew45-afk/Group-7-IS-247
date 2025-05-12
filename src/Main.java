import java.time.LocalDate;
import java.util.*;


// -------------------- Main.java --------------------
public class Main {
    public static void main(String[] args) {
        final String SYSTEM_NAME = "Course Management System"; // constant
        Scanner scanner = new Scanner(System.in);
        CourseManagementSystem cms = new CourseManagementSystem();
        CMSReport report = new CMSReport();
        Stack<String> history = new Stack<>(); // stack example
        Random random = new Random(); // Random class


        Instructor instructor = new Instructor("Dr. Smith", 101);
        Course javaCourse = new Course("Java Programming", 247, instructor);
        cms.addCourse(javaCourse);


        // Pre-existing students
        Student s1 = new Student("Hannah", 1);
        Student s2 = new Student("Joseph", 2);


        cms.enrollStudent(s1, javaCourse);
        history.push("Enrolled: " + s1.name);
        cms.enrollStudent(s2, javaCourse);
        history.push("Enrolled: " + s2.name);


        try {
            cms.assignGrade(s1, "Java Programming", 96.5);
            cms.assignGrade(s2, "Java Programming", 88.0);
            cms.assignGrade(null, "Java Programming", 90); // force exception
        } catch (Exception e) {
            System.out.println("Handled exception: " + e.getMessage());
        }


        System.out.println("Welcome to the " + SYSTEM_NAME);
        System.out.println("Course: " + javaCourse.getName());
        System.out.println("Instructor: " + javaCourse.getInstructor().name);


        // New: Allow user to add students manually
        System.out.print("Would you like to add a new student? (yes/no): ");
        String addStudentResponse = scanner.next().toLowerCase();
        while (addStudentResponse.equals("yes")) {
            scanner.nextLine(); // consume leftover newline
            System.out.print("Enter student name: ");
            String studentName = scanner.nextLine();


            int studentId = random.nextInt(1000) + 100; // simple random ID generator
            Student newStudent = new Student(studentName, studentId);
            cms.enrollStudent(newStudent, javaCourse);
            history.push("Enrolled: " + newStudent.name);


            System.out.print("Enter grade for " + newStudent.name + " in " + javaCourse.getName() + ": ");
            double grade = scanner.nextDouble();
            cms.assignGrade(newStudent, javaCourse.getName(), grade);


            System.out.print("Would you like to add another student? (yes/no): ");
            addStudentResponse = scanner.next().toLowerCase();
        }


        System.out.print("Choose report type (1 = Full, 2 = Summary): ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> report.generateReport(javaCourse.getStudents(), "Java Programming");
            case 2 -> System.out.println("Total students: " + javaCourse.getStudents().size());
            default -> System.out.println("Invalid choice.");
        }


        List<Double> s1Grades = new ArrayList<>(s1.getGrades().values());
        double total = GradeCalculator.calculateAverage(s1Grades, 0);
        double avg = total / s1Grades.size();


        String result = avg >= 90 ? "Excellent" : "Needs Improvement";
        System.out.printf("Average grade for %s: %.2f%n", s1.name, avg);
        System.out.println("Performance: " + result);
        System.out.println("Last action: " + history.pop());
        System.out.println("Report generated on: " + LocalDate.now());


        scanner.close();
    }
}
