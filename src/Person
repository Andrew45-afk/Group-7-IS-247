//Group 7 Andrew Fulco, Hannah Howard, Senai G, Joseph Hwang
import java.util.*;
import java.time.LocalDate;

abstract class Person {
    protected String name;
    protected int id;


    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public abstract void displayInfo();
}

class Student extends Person {
    private Map<String, Double> grades = new HashMap<>();

    public Student(String name, int id) {
        super(name, id);
        this.name = name; // using 'this' keyword
    }

    public void addGrade(String course, double grade) {
        grades.put(course, grade);
    }

    // Method overloading
    public void addGrade(String course, int grade) {
        addGrade(course, (double) grade);
    }

    public Map<String, Double> getGrades() {
        return grades;
    }

    @Override
    public void displayInfo() {
        System.out.println("Student: " + name + ", ID: " + id);
    }
}

class Instructor extends Person {
    private List<String> courses = new ArrayList<>();

    public Instructor(String name, int id) {
        super(name, id);
    }

    public void addCourse(String courseName) {
        courses.add(courseName);
    }

    @Override
    public void displayInfo() {
        System.out.println("Instructor: " + name + ", ID: " + id);
    }
}

class Course {
    private String name;
    private int code;
    private Instructor instructor;
    private List<Student> enrolledStudents = new ArrayList<>();

    public Course(String name, int code, Instructor instructor) {
        this.name = name;
        this.code = code;
        this.instructor = instructor;
    }

    public void enrollStudent(Student student) {
        enrolledStudents.add(student);
    }

    public List<Student> getStudents() {
        return enrolledStudents;
    }

    public String getName() {
        return name;
    }

    public Instructor getInstructor() {
        return instructor;
    }
}

interface ReportGenerator {
    void generateReport(List<Student> students, String courseName);
}

class CMSReport implements ReportGenerator {
    @Override
    public void generateReport(List<Student> students, String courseName) {
        System.out.println("---- Course Report: " + courseName + " ----");
        for (Student s : students) {
            Double grade = s.getGrades().get(courseName);
            if (grade != null) {
                System.out.println(s.name + ": " + grade);
            } else {
                System.out.println(s.name + ": No grade available");
            }
        }
    }
}

class GradeCalculator {
    public static double calculateAverage(List<Double> grades, int index) {
        if (index == grades.size()) return 0;
        return grades.get(index) + calculateAverage(grades, index + 1);
    }
}

class DataStore<T> {
    private List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public List<T> getAll() {
        return items;
    }
}

class CourseManagementSystem {
    private List<Course> courses = new ArrayList<>();
    private DataStore<Student> students = new DataStore<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void enrollStudent(Student student, Course course) {
        students.add(student);
        course.enrollStudent(student);
    }

    public void assignGrade(Student student, String courseName, double grade) {
        if (student != null) {
            student.addGrade(courseName, grade);
        } else {
            throw new IllegalArgumentException("Student cannot be null");
        }
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Student> getAllStudents() {
        return students.getAll();
    }
}
