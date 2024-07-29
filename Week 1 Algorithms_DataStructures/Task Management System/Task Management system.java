public class TaskManagementSystem {

    static class Task {
        private String taskId;
        private String taskName;
        private String status;

        public Task(String taskId, String taskName, String status) {
            this.taskId = taskId;
            this.taskName = taskName;
            this.status = status;
        }

        public String getTaskId() { return taskId; }
        public String getTaskName() { return taskName; }
        public String getStatus() { return status; }

        @Override
        public String toString() {
            return "Task{" +
                   "taskId='" + taskId + '\'' +
                   ", taskName='" + taskName + '\'' +
                   ", status='" + status + '\'' +
                   '}';
        }
    }

    static class Node {
        Task task;
        Node next;

        Node(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    static class TaskLinkedList {
        private Node head;

        public TaskLinkedList() {
            this.head = null;
        }

        public void addTask(Task task) {
            Node newNode = new Node(task);
            if (head == null) {
                head = newNode;
            } else {
                Node current = head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = newNode;
            }
        }

        public Task searchTaskById(String taskId) {
            Node current = head;
            while (current != null) {
                if (current.task.getTaskId().equals(taskId)) {
                    return current.task;
                }
                current = current.next;
            }
            return null;
        }

        public void traverseTasks() {
            Node current = head;
            while (current != null) {
                System.out.println(current.task);
                current = current.next;
            }
        }

        public void deleteTaskById(String taskId) {
            if (head == null) return;

            if (head.task.getTaskId().equals(taskId)) {
                head = head.next;
                return;
            }

            Node current = head;
            while (current.next != null && !current.next.task.getTaskId().equals(taskId)) {
                current = current.next;
            }

            if (current.next != null) {
                current.next = current.next.next;
            }
        }
    }

    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        Task t1 = new Task("1", "Task 1", "Pending");
        Task t2 = new Task("2", "Task 2", "In Progress");
        Task t3 = new Task("3", "Task 3", "Completed");

        taskList.addTask(t1);
        taskList.addTask(t2);
        taskList.addTask(t3);

        System.out.println("All Tasks:");
        taskList.traverseTasks();

        System.out.println("\nSearch Task with ID 2:");
        System.out.println(taskList.searchTaskById("2"));

        System.out.println("\nDeleting Task with ID 2:");
        taskList.deleteTaskById("2");

        System.out.println("\nAll Tasks After Deletion:");
        taskList.traverseTasks();
    }
}
