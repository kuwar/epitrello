/**
 * 
 */
package fr.epita.epitrello.dao;

import java.util.Collections;
import java.util.stream.Collectors;

import fr.epita.epitrello.util.SortTaskByPriority;
import fr.epita.epitrello.util.SortUserByPerformance;
import fr.epita.epitrello.util.SortUserByWorkload;

/**
 * @author kuwar
 *
 */
public class EpitrelloDataServerice {

	/**
	 * 
	 */
	public EpitrelloDataServerice() {
	}

	public String addUser(String name) {
		// check if user exist
		if (DataStore.getInstance().isUserExist(name)) {
			return "User already exists.";
		} else {
			User user = new User();
			user.addUser(name);

			return "Success";
		}
	}

	public String addList(String name) {
		if (DataStore.getInstance().isListExist(name)) {
			return "List string already exists";
		}

		List list = new List();
		list.addList(name);

		return "Success";
	}

	public String addTask(String list, String name, int estimatedTime, int priority, String description) {
		if (DataStore.getInstance().isTaskExist(name)) {
			return "Task already exists";
		}
		if (!DataStore.getInstance().isListExist(list)) {
			return "List does not exist";
		}

		Task task = new Task();

		task.addTask(DataStore.getInstance().getTaskList(list), name, estimatedTime, priority, description);

		DataStore.getInstance().getTaskList(list).setTasks(task);

		return "Success";
	}

	public String editTask(String task, int estimatedTime, int priority, String description) {
		if (DataStore.getInstance().isTaskExist(task)) {
			DataStore.getInstance().editTask(task, estimatedTime, priority, description);

			return "Success";
		}

		return "The task does not exist.";
	}

	public String assignTask(String task, String user) {
		User userDetail = DataStore.getInstance().getUser(user);
		Task taskDetail = DataStore.getInstance().getTask(task);

		if ((userDetail != null) && (taskDetail != null)) {
			// establishing two association with users and tasks
			userDetail.setTask(taskDetail);
			taskDetail.setUsers(userDetail);

			return "Success";
		}

		return "Unable to assign Task: " + task + "to User: " + user;
	}

	public String printTask(String task) {
		Task taskDetail = DataStore.getInstance().getTask(task);

		System.out.println(taskDetail.getName());
		System.out.println(taskDetail.getDescription());
		System.out.println("Priority: " + taskDetail.getPriority());
		System.out.println("Estimated Time: " + taskDetail.getEstimatedTime());
		// iterate over all the users assigned to the task
		if (taskDetail.getUsers().size() != 0) {
			for (User user : taskDetail.getUsers()) {
				System.out.println("Assigned to " + user.getName());
			}
		} else {
			System.out.println("Unassigned");
		}

		return "\n";
	}

	public String completeTask(String task) {
		if (DataStore.getInstance().isTaskExist(task)) {
			DataStore.getInstance().getTask(task).setCompleted(true);

			return "Success";
		}

		return "Task does not exist.";
	}

	public String printUsersByPerformance() {
		java.util.List<User> users = DataStore.getInstance().getUserList();

		Collections.sort(users, new SortUserByPerformance());
		for (User user : users) {
			System.out.println(user.getName());
		}

		return "\n";
	}

	public String printUsersByWorkload() {
		java.util.List<User> users = DataStore.getInstance().getUserList();

		Collections.sort(users, new SortUserByWorkload());
		for (User user : users) {
			System.out.println(user.getName());
		}

		return "\n";
	}

	public String printUnassignedTasksByPriority() {

		java.util.List<Task> tasks = DataStore.getInstance().getTasks();

		java.util.List<Task> unassignedTasks = tasks.stream().filter(task -> task.getUsers().isEmpty() == true)
				.collect(Collectors.toList());

		int count = 1;
		for (Task task : unassignedTasks) {
			System.out.println(count + " | " + task.getName() + " | Unassigned | " + task.getEstimatedTime() + "h");
			count++;
		}

		return "\n";
	}

	public String deleteTask(String task) {
		if (DataStore.getInstance().isTaskExist(task)) {
			DataStore.getInstance().deleteTask(task);

			return "Success";
		}

		return "Task does not exist";
	}

	public String printAllUnfinishedTasksByPriority() {

		// get all unfinished task
		java.util.List<Task> unfinishedTasks = DataStore.getInstance().getUnfinishedTask();

		// sort unfinished task by priority
		Collections.sort(unfinishedTasks, new SortTaskByPriority());
		int count = 1;
		for (Task task : unfinishedTasks) {
			System.out.println(count + " | " + task.getName() + " | " + task.getUsers().get(0).getName() + " | "
					+ task.getEstimatedTime() + "h");
			count++;
		}

		return "\n";
	}

	public String moveTask(String taskName, String listName) {

		Task task = DataStore.getInstance().getTask(taskName);
		List list = DataStore.getInstance().getTaskList(listName);

		if (task == null) {
			return "Task does not exist";
		}
		if (list == null) {
			return "List does not exist";
		}
		// remove the task from old list
		list.remove(task);

		// add new list to task
		list.setTasks(task);

		// set new list to task ie update task pointing to list
		task.setList(list);

		return "Success";
	}

	public void printList(String list) {

	}

	public String printAllLists() {
		DataStore.getInstance().printAll();

		return "----Done---";
	}

	public void printUserTasks(String user) {

	}
}
