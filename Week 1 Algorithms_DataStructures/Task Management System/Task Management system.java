class Task:
    def __init__(self, taskId, taskName, status):
        self.taskId = taskId
        self.taskName = taskName
        self.status = status

    def __repr__(self):
        return f"Task(ID: {self.taskId}, Name: {self.taskName}, Status: {self.status})"

class Node:
    def __init__(self, task):
        self.task = task
        self.next = None

class LinkedList:
    def __init__(self):
        self.head = None

    def add_task(self, task):
        new_node = Node(task)
        if not self.head:
            self.head = new_node
        else:
            current = self.head
            while current.next:
                current = current.next
            current.next = new_node

    def search_task(self, taskId):
        current = self.head
        while current:
            if current.task.taskId == taskId:
                return current.task
            current = current.next
        return None

    def delete_task(self, taskId):
        current = self.head
        prev = None
        while current:
            if current.task.taskId == taskId:
                if prev:
                    prev.next = current.next
                else:
                    self.head = current.next
                return True
            prev = current
            current = current.next
        return False

    def traverse(self):
        tasks = []
        current = self.head
        while current:
            tasks.append(current.task)
            current = current.next
        return tasks

# Example usage:
if __name__ == "__main__":
    # Create the linked list
    task_list = LinkedList()

    # Add tasks
    task_list.add_task(Task(1, "Task One", "Pending"))
    task_list.add_task(Task(2, "Task Two", "Completed"))
    task_list.add_task(Task(3, "Task Three", "In Progress"))

    # Print all tasks
    print("All tasks:")
    for task in task_list.traverse():
        print(task)

    # Search for a task
    search_id = 2
    found_task = task_list.search_task(search_id)
    if found_task:
        print(f"\nTask with ID {search_id} found: {found_task}")
    else:
        print(f"\nTask with ID {search_id} not found.")

    # Delete a task
    delete_id = 1
    if task_list.delete_task(delete_id):
        print(f"\nTask with ID {delete_id} deleted.")
    else:
        print(f"\nTask with ID {delete_id} not found for deletion.")

    # Print all tasks after deletion
    print("\nAll tasks after deletion:")
    for task in task_list.traverse():
        print(task)
