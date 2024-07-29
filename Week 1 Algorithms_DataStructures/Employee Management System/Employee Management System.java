public class EmployeeManagementSystem {

    static class Employee {
        private String employeeId;
        private String name;
        private String position;
        private double salary;

        public Employee(String employeeId, String name, String position, double salary) {
            this.employeeId = employeeId;
            this.name = name;
            this.position = position;
            this.salary = salary;
        }

        public String getEmployeeId() { return employeeId; }
        public String getName() { return name; }
        public String getPosition() { return position; }
        public double getSalary() { return salary; }

        @Override
        public String toString() {
            return "Employee{" +
                   "employeeId='" + employeeId + '\'' +
                   ", name='" + name + '\'' +
                   ", position='" + position + '\'' +
                   ", salary=" + salary +
                   '}';
        }
    }

    static class EmployeeManagement {
        private Employee[] employees;
        private int size;
        private static final int INITIAL_CAPACITY = 10;

        public EmployeeManagement() {
            employees = new Employee[INITIAL_CAPACITY];
            size = 0;
        }

        public void addEmployee(Employee employee) {
            if (size == employees.length) {
                increaseCapacity();
            }
            employees[size++] = employee;
        }

        public Employee searchEmployeeById(String employeeId) {
            for (int i = 0; i < size; i++) {
                if (employees[i].getEmployeeId().equals(employeeId)) {
                    return employees[i];
                }
            }
            return null;
        }

        public void traverseEmployees() {
            for (int i = 0; i < size; i++) {
                System.out.println(employees[i]);
            }
        }

        public void deleteEmployeeById(String employeeId) {
            int index = -1;
            for (int i = 0; i < size; i++) {
                if (employees[i].getEmployeeId().equals(employeeId)) {
                    index = i;
                    break;
                }
            }
            if (index != -1) {
                for (int i = index; i < size - 1; i++) {
                    employees[i] = employees[i + 1];
                }
                employees[--size] = null;
            }
        }

        private void increaseCapacity() {
            int newCapacity = employees.length * 2;
            Employee[] newEmployees = new Employee[newCapacity];
            System.arraycopy(employees, 0, newEmployees, 0, size);
            employees = newEmployees;
        }
    }

    public static void main(String[] args) {
        EmployeeManagement ems = new EmployeeManagement();

        Employee e1 = new Employee("E001", "Alice", "Manager", 75000);
        Employee e2 = new Employee("E002", "Bob", "Developer", 60000);
        Employee e3 = new Employee("E003", "Charlie", "Analyst", 55000);

        ems.addEmployee(e1);
        ems.addEmployee(e2);
        ems.addEmployee(e3);

        System.out.println("All Employees:");
        ems.traverseEmployees();

        System.out.println("\nSearch Employee E002:");
        System.out.println(ems.searchEmployeeById("E002"));

        System.out.println("\nDeleting Employee E002:");
        ems.deleteEmployeeById("E002");

        System.out.println("\nAll Employees After Deletion:");
        ems.traverseEmployees();
    }
}
