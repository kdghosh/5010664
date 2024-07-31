package sai;

class Task 
{
    int taskId;
    String taskName;
    String status;

    public Task(int taskId, String taskName, String status) 
    {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "Task ID: " + taskId + ", Task Name: " + taskName + ", Status: " + status;
    }
}

class Node {
    Task task;
    Node next;

    public Node(Task task) {
        this.task = task;
        this.next = null;
    }
}

public class TaskManagementSystem 
{
    private Node head;

    // Add a new task
    public void addTask(Task task) 
    {
        Node newNode = new Node(task);
        if (head == null) 
        {
            head = newNode;
        } 
        else 
        {
            Node current = head;
            while (current.next != null) 
            {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Search for a task by ID
    public Task searchTask(int taskId) 
    {
        Node current = head;
        while (current != null) 
        {
            if (current.task.taskId == taskId) 
            {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    // Traverse the linked list and print all tasks
    public void traverse() 
    {
        Node current = head;
        while (current != null) 
        {
            System.out.println(current.task);
            current = current.next;
        }
    }

    // Delete a task by ID
    public boolean deleteTask(int taskId) 
    {
        if (head == null) 
        {
            return false;
        }

        if (head.task.taskId == taskId) 
        {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null && current.next.task.taskId != taskId) 
        {
            current = current.next;
        }

        if (current.next != null) 
        {
            current.next = current.next.next;
            return true;
        } 
        else 
        {
            return false;
        }
    }

    public static void main(String[] args) 
    {
    	TaskManagementSystem taskList = new TaskManagementSystem();
        taskList.addTask(new Task(1, "Task 1", "Pending"));
        taskList.addTask(new Task(2, "Task 2", "In Progress"));
        taskList.addTask(new Task(3, "Task 3", "Completed"));

        System.out.println("All tasks:");
        taskList.traverse();

        System.out.println("Searching for task with ID 2:");
        Task task = taskList.searchTask(2);
        if (task != null) 
        {
            System.out.println(task);
        } 
        else 
        {
            System.out.println("Task not found");
        }

        System.out.println("Deleting task with ID 1:");
        if (taskList.deleteTask(1)) 
        {
            System.out.println("Task deleted successfully");
        } 
        else 
        {
            System.out.println("Task not found");
        }

        System.out.println("All tasks after deletion:");
        taskList.traverse();
    }
}
