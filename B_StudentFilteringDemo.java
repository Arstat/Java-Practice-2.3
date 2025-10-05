import java.util.*;
import java.util.stream.*;

class Student {
    private String name;
    private double marks;
    
    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }
    
    public String getName() {
        return name;
    }
    
    public double getMarks() {
        return marks;
    }
    
    @Override
    public String toString() {
        return String.format("Student{name='%s', marks=%.2f%%}", name, marks);
    }
}

public class B_StudentFilteringDemo {
    public static void main(String[] args) {
        // Create a list of students
        List<Student> students = Arrays.asList(
            new Student("Emma Wilson", 92.5),
            new Student("James Parker", 68.0),
            new Student("Sophia Martinez", 85.5),
            new Student("Oliver Taylor", 73.0),
            new Student("Isabella Anderson", 91.0),
            new Student("Liam Thomas", 79.5),
            new Student("Ava Jackson", 65.5),
            new Student("Noah White", 88.0),
            new Student("Mia Harris", 95.5),
            new Student("Ethan Clark", 71.5)
        );
        
        System.out.println("All Students:");
        students.forEach(System.out::println);
        
        // Filter students with marks > 75%, sort by marks, and display names
        System.out.println("\n--- Students with Marks > 75% (Sorted by Marks) ---");
        
        List<String> topStudentNames = students.stream()
            .filter(s -> s.getMarks() > 75.0)          // Filter marks > 75%
            .sorted(Comparator.comparing(Student::getMarks)) // Sort by marks (ascending)
            .map(Student::getName)                      // Extract names
            .collect(Collectors.toList());              // Collect to list
        
        topStudentNames.forEach(System.out::println);
        
        // Alternative: Display complete student information
        System.out.println("\n--- Students with Marks > 75% (Complete Info) ---");
        students.stream()
            .filter(s -> s.getMarks() > 75.0)
            .sorted(Comparator.comparing(Student::getMarks))
            .forEach(System.out::println);
        
        // Sort by marks in descending order
        System.out.println("\n--- Students with Marks > 75% (Descending Order) ---");
        students.stream()
            .filter(s -> s.getMarks() > 75.0)
            .sorted(Comparator.comparing(Student::getMarks).reversed())
            .map(Student::getName)
            .forEach(name -> System.out.println(name));
        
        // Additional statistics
        System.out.println("\n--- Statistics for Students > 75% ---");
        DoubleSummaryStatistics stats = students.stream()
            .filter(s -> s.getMarks() > 75.0)
            .mapToDouble(Student::getMarks)
            .summaryStatistics();
        
        System.out.println("Count: " + stats.getCount());
        System.out.println("Average: " + String.format("%.2f%%", stats.getAverage()));
        System.out.println("Highest: " + String.format("%.2f%%", stats.getMax()));
        System.out.println("Lowest: " + String.format("%.2f%%", stats.getMin()));
    }
}