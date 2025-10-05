import java.util.*;

class Employee {
    private String name;
    private int age;
    private double salary;
    
    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    
    public String getName() {
        return name;
    }
    
    public int getAge() {
        return age;
    }
    
    public double getSalary() {
        return salary;
    }
    
    @Override
    public String toString() {
        return String.format("Employee{name='%s', age=%d, salary=%.2f}", 
                           name, age, salary);
    }
}

public class A_EmployeeSortingDemo {
    public static void main(String[] args) {
        // Create a list of employees
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("John Smith", 35, 75000.00));
        employees.add(new Employee("Alice Johnson", 28, 82000.00));
        employees.add(new Employee("Bob Williams", 42, 68000.00));
        employees.add(new Employee("Carol Davis", 31, 91000.00));
        employees.add(new Employee("David Brown", 29, 73000.00));
        
        System.out.println("Original List:");
        employees.forEach(System.out::println);
        
        // Sort by name (alphabetically) using lambda expression
        System.out.println("\n--- Sorted by Name (Alphabetically) ---");
        List<Employee> sortedByName = new ArrayList<>(employees);
        sortedByName.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
        sortedByName.forEach(System.out::println);
        
        // Alternative: Using Comparator.comparing
        System.out.println("\n--- Sorted by Name (Using Comparator.comparing) ---");
        List<Employee> sortedByName2 = new ArrayList<>(employees);
        sortedByName2.sort(Comparator.comparing(Employee::getName));
        sortedByName2.forEach(System.out::println);
        
        // Sort by age (ascending) using lambda expression
        System.out.println("\n--- Sorted by Age (Ascending) ---");
        List<Employee> sortedByAge = new ArrayList<>(employees);
        sortedByAge.sort((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()));
        sortedByAge.forEach(System.out::println);
        
        // Sort by salary (ascending) using lambda expression
        System.out.println("\n--- Sorted by Salary (Ascending) ---");
        List<Employee> sortedBySalaryAsc = new ArrayList<>(employees);
        sortedBySalaryAsc.sort((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        sortedBySalaryAsc.forEach(System.out::println);
        
        // Sort by salary (descending) using lambda expression
        System.out.println("\n--- Sorted by Salary (Descending) ---");
        List<Employee> sortedBySalaryDesc = new ArrayList<>(employees);
        sortedBySalaryDesc.sort((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()));
        sortedBySalaryDesc.forEach(System.out::println);
        
        // Alternative: Using Comparator.comparing with reversed
        System.out.println("\n--- Sorted by Salary (Descending - Alternative) ---");
        List<Employee> sortedBySalaryDesc2 = new ArrayList<>(employees);
        sortedBySalaryDesc2.sort(Comparator.comparing(Employee::getSalary).reversed());
        sortedBySalaryDesc2.forEach(System.out::println);
    }
}