package sai;
import java.util.Arrays;
class Employee {
    private String employeeId;
    private String name;
    private String position;
    private double salary;

    public Employee(String employeeId, String name, String position, double salary) 
    {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getEmployeeId() 
    {
        return employeeId;
    }

    public String getName() 
    {
        return name;
    }

    public String getPosition() 
    {
        return position;
    }

    public double getSalary() 
    {
        return salary;
    }

    @Override
    public String toString() 
    {
        return "Employee{" +"employeeId='" + employeeId + '\'' +", name='" + name + '\'' +", position='" + 
        position + '\'' +", salary=" + salary +'}';
    }
}

public class EmployeeManagementSystem 
{
    private Employee[] employees;
    private int size;

    public EmployeeManagementSystem(int capacity) 
    {
        employees = new Employee[capacity];
        size = 0;
    }

    // Add an employee
    public boolean addEmployee(Employee employee) 
    {
        if (size == employees.length) 
        {
            return false; // Array is full
        }
        employees[size++] = employee;
        return true;
    }

    // Search an employee by ID
    public Employee searchEmployee(String employeeId) 
    {
        for (int i = 0; i < size; i++) 
        {
            if (employees[i].getEmployeeId().equals(employeeId)) 
            {
                return employees[i];
            }
        }
        return null; // Not found
    }

    // Traverse all employees
    public void traverseEmployees() 
    {
        for (int i = 0; i < size; i++) 
        {
            System.out.println(employees[i]);
        }
    }

    // Delete an employee by ID
    public boolean deleteEmployee(String employeeId) 
    {
        for (int i = 0; i < size; i++) 
        {
            if (employees[i].getEmployeeId().equals(employeeId)) 
            {
                employees[i] = employees[size - 1]; // Replace with the last element
                employees[size - 1] = null; // Remove the last element
                size--;
                return true;
            }
        }
        return false; // Not found
    }

    public static void main(String[] args) 
    {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(5);

        ems.addEmployee(new Employee("1", "Alice", "Manager", 90000));
        ems.addEmployee(new Employee("2", "Bob", "Developer", 80000));
        ems.addEmployee(new Employee("3", "Charlie", "Designer", 70000));

        System.out.println("All Employees:");
        ems.traverseEmployees();

        System.out.println("\nSearching for Employee with ID 2:");
        Employee emp = ems.searchEmployee("2");
        if (emp != null) 
        {
            System.out.println(emp);
        } else 
        {
            System.out.println("Employee not found.");
        }

        System.out.println("\nDeleting Employee with ID 2:");
        if (ems.deleteEmployee("2")) 
        {
            System.out.println("Employee deleted successfully.");
        } 
        else 
        {
            System.out.println("Employee not found.");
        }
        System.out.println("\nAll Employees after deletion:");
        ems.traverseEmployees();
    }
}

