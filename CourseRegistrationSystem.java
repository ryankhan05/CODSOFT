import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String courseCode;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private int registeredStudents;

    public Course(String courseCode, String title, String description, int capacity, String schedule) {
        this.courseCode = courseCode;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = 0;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public int getRegisteredStudents() {
        return registeredStudents;
    }

    public void registerStudent() {
        if (registeredStudents < capacity) {
            registeredStudents++;
            System.out.println("Successfully registered for the course: " + title);
        } else {
            System.out.println("Sorry, the course " + title + " is already full.");
        }
    }

    public void removeStudent() {
        if (registeredStudents > 0) {
            registeredStudents--;
            System.out.println("Successfully removed from the course: " + title);
        } else {
            System.out.println("You are not registered in the course " + title);
        }
    }

    public String toString() {
        return "Course Code: " + courseCode + "\nTitle: " + title + "\nDescription: " + description +
                "\nCapacity: " + capacity + "\nSchedule: " + schedule + "\nRegistered Students: " + registeredStudents + "\n";
    }
}

class Student {
    private int studentID;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(Course course) {
        registeredCourses.add(course);
        course.registerStudent();
    }

    public void removeCourse(Course course) {
        registeredCourses.remove(course);
        course.removeStudent();
    }

    public String toString() {
        return "Student ID: " + studentID + "\nName: " + name + "\nRegistered Courses: " + registeredCourses.size() + "\n";
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("21HS61B", "Principles of Management & Economics", 3, "Tue/Thu 1:00 PM"));
        courses.add(new Course("21EI62", "PLC and SCADA Systems ", 4, "Mon/Wed/Fri 10:00 AM"));
        courses.add(new Course("21EI63", "Digital Signal Processing", 4, "Tue/Thu 1:00 PM"));
        courses.add(new Course("21EI64DX", "Professional Core Elective 1", 3, "Tue/Thu 1:00 PM"));
        courses.add(new Course("21EI65EX", "Professional Core Elective 2", 3, "Tue/Thu 1:00 PM"));
        courses.add(new Course("21IE66FX", "Institutional Electives", 3, "Tue/Thu 1:00 PM"));
        // Add more courses...

        List<Student> students = new ArrayList<>();
        students.add(new Student(1001, "Rayyan"));
        students.add(new Student(1002, "Zehra"));
        // Add more students...

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Course Listing");
            System.out.println("2. Student Registration");
            System.out.println("3. Course Removal");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayCourses(courses);
                    break;
                case 2:
                    displayCourses(courses);
                    registerStudent(students, courses, scanner);
                    break;
                case 3:
                    displayStudentCourses(students);
                    removeCourse(students, scanner);
                    break;
                case 4:
                    System.out.println("Exiting the Course Registration System.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
                    break;
            }
        }
    }

    public static void displayCourses(List<Course> courses) {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public static void displayStudentCourses(List<Student> students) {
        System.out.println("Students and Their Courses:");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public static void registerStudent(List<Student> students, List<Course> courses, Scanner scanner) {
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();
        Student student = findStudent(students, studentID);

        if (student != null) {
            System.out.print("Enter course code to register: ");
            scanner.nextLine(); // Clear the newline character
            String courseCode = scanner.nextLine();
            Course course = findCourse(courses, courseCode);

            if (course != null) {
                student.registerCourse(course);
            } else {
                System.out.println("Course not found.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void removeCourse(List<Student> students, Scanner scanner) {
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();
        Student student = findStudent(students, studentID);

        if (student != null) {
            System.out.print("Enter course code to remove: ");
            scanner.nextLine(); // Clear the newline character
            String courseCode = scanner.nextLine();
            Course course = findCourse(student.getRegisteredCourses(), courseCode);

            if (course != null) {
                student.removeCourse(course);
            } else {
                System.out.println("Course not found in student's registered courses.");
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    public static Student findStudent(List<Student> students, int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }

    public static Course findCourse(List<Course> courses, String courseCode) {
        for (Course course : courses) {
            if (course.getCourseCode().equalsIgnoreCase(courseCode)) {
                return course;
            }
        }
        return null;
    }
}

