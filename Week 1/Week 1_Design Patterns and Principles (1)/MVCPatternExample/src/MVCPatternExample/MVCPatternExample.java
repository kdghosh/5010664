package MVCPatternExample;

//Student.java
 class Student {
 private String name;
 private String id;
 private String grade;

 // Constructor
 public Student(String id, String name, String grade) {
     this.id = id;
     this.name = name;
     this.grade = grade;
 }

 // Getters and setters
 public String getName() {
     return name;
 }

 public void setName(String name) {
     this.name = name;
 }

 public String getId() {
     return id;
 }

 public void setId(String id) {
     this.id = id;
 }

 public String getGrade() {
     return grade;
 }

 public void setGrade(String grade) {
     this.grade = grade;
 }
}

//StudentView.java
class StudentView {
 public void displayStudentDetails(String studentName, String studentId, String studentGrade) {
     System.out.println("Student Details: ");
     System.out.println("Name: " + studentName);
     System.out.println("ID: " + studentId);
     System.out.println("Grade: " + studentGrade);
 }
}

//StudentController.java
class StudentController {
 private Student model;
 private StudentView view;

 public StudentController(Student model, StudentView view) {
     this.model = model;
     this.view = view;
 }

 public void setStudentName(String name) {
     model.setName(name);
 }

 public String getStudentName() {
     return model.getName();
 }

 public void setStudentId(String id) {
     model.setId(id);
 }

 public String getStudentId() {
     return model.getId();
 }

 public void setStudentGrade(String grade) {
     model.setGrade(grade);
 }

 public String getStudentGrade() {
     return model.getGrade();
 }

 public void updateView() {
     view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
 }
}

//MVCPatternExample.java
public class MVCPatternExample {
 public static void main(String[] args) {
     // Create a Student model
     Student model = new Student("1", "John Doe", "A");

     // Create a view to display student details
     StudentView view = new StudentView();

     // Create a controller to manage the model and view
     StudentController controller = new StudentController(model, view);

     // Display initial student details
     controller.updateView();

     // Update student details
     controller.setStudentName("Jane Doe");
     controller.setStudentGrade("B");

     // Display updated student details
     controller.updateView();
 }
}

