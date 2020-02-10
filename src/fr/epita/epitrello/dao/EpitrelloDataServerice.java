/**
 * 
 */
package fr.epita.epitrello.dao;

import java.util.Collections;
import java.util.stream.Collectors;

import fr.epita.epitrello.services.FileLogger;
import fr.epita.epitrello.util.SortTaskByPriority;
import fr.epita.epitrello.util.SortUserByPerformance;
import fr.epita.epitrello.util.SortUserByWorkload;

/**
 * @author kuwar
 *
 */
public class EpitrelloDataServerice {

	/**
	 * Static message to display as a success
	 */
	final static String SUCCESS = "Success";

	/**
	 * 
	 */
	public EpitrelloDataServerice() {
	}

	/**
	 * Function to add user in the system Uniqueness of the user is checked
	 * 
	 * @param name
	 * @return
	 */
	public String addUser(String name) {
		// check if user exist
		if (DataStore.getInstance().isUserExist(name)) {
			return "User already exists.";
		} else {
			User user = new User();
			user.addUser(name);

			FileLogger.write(SUCCESS);

			return SUCCESS;
		}
	}

	/**
	 * Add list It is similar to category ie it shows the category of task
	 * 
	 * @param name
	 * @return
	 */
	public String addList(String name) {
		if (DataStore.getInstance().isListExist(name)) {
			return "List string already exists";
		}

		List list = new List();
		list.addList(name);

		FileLogger.write(SUCCESS);
		return SUCCESS;
	}

	/**
	 * Add task It is task that a assigned user will do based on its priority and
	 * within estimated time
	 * 
	 * @param list
	 * @param name
	 * @param estimatedTime
	 * @param priority
	 * @param description
	 * @return
	 */
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

		FileLogger.write(SUCCESS);
		return SUCCESS;
	}

	/**
	 * Edit task Update every parameters of task except name
	 * 
	 * @param task
	 * @param estimatedTime
	 * @param priority
	 * @param description
	 * @return
	 */
	public String editTask(String task, int estimatedTime, int priority, String description) {
		if (DataStore.getInstance().isTaskExist(task)) {
			DataStore.getInstance().editTask(task, estimatedTime, priority, description);

			FileLogger.write(SUCCESS);
			return SUCCESS;
		}

		return "The task does not exist.";
	}

	/**
	 * Assign task to user
	 * 
	 * @param task
	 * @param user
	 * @return
	 */
	public String assignTask(String task, String user) {
		User userDetail = DataStore.getInstance().getUser(user);
		Task taskDetail = DataStore.getInstance().getTask(task);

		if ((userDetail != null) && (taskDetail != null)) {
			// establishing two association with users and tasks
			userDetail.setTask(taskDetail);
			taskDetail.setUsers(userDetail);

			FileLogger.write(SUCCESS);

			return SUCCESS;
		}

		return "Unable to assign Task: " + task + "to User: " + user;
	}

	/**
	 * Print task on the basis of provided task name
	 * 
	 * @param task
	 * @return
	 */
	public String printTask(String task) {
		Task taskDetail = DataStore.getInstance().getTask(task);

		FileLogger.write(taskDetail.getName());
		System.out.println(taskDetail.getName());

		FileLogger.write(taskDetail.getDescription());
		System.out.println(taskDetail.getDescription());

		FileLogger.write(String.valueOf(taskDetail.getPriority()));
		System.out.println("Priority: " + taskDetail.getPriority());

		FileLogger.write(String.valueOf(taskDetail.getEstimatedTime()));
		System.out.println("Estimated Time: " + taskDetail.getEstimatedTime());
		// iterate over all the users assigned to the task
		if (taskDetail.getUsers().size() != 0) {
			for (User user : taskDetail.getUsers()) {
				FileLogger.write("Assigned to " + user.getName());
				System.out.println("Assigned to " + user.getName());
			}
		} else {
			FileLogger.write("Unassigned");
			System.out.println("Unassigned");
		}

		return "\n";
	}

	/**
	 * Make the task complete ie change is complete to true from false
	 * 
	 * @param task
	 * @return
	 */
	public String completeTask(String task) {
		if (DataStore.getInstance().isTaskExist(task)) {
			DataStore.getInstance().getTask(task).setCompleted(true);

			FileLogger.write(SUCCESS);

			return SUCCESS;
		}

		return "Task does not exist.";
	}

	/**
	 * Print users based on their performance ie sort users based on the performance
	 * 
	 * @return
	 */
	public String printUsersByPerformance() {
		java.util.List<User> users = DataStore.getInstance().getUserList();

		Collections.sort(users, new SortUserByPerformance());
		for (User user : users) {
			FileLogger.write(user.getName());
			System.out.println(user.getName());
		}

		return "\n";
	}

	/**
	 * Print user on the basis of workload
	 * 
	 * @return
	 */
	public String printUsersByWorkload() {
		java.util.List<User> users = DataStore.getInstance().getUserList();

		Collections.sort(users, new SortUserByWorkload());
		for (User user : users) {
			FileLogger.write(user.getName());
			System.out.println(user.getName());
		}

		return "\n";
	}

	/**
	 * Print all the task which is not yet assigned to the user
	 * 
	 * @return
	 */
	public String printUnassignedTasksByPriority() {

		java.util.List<Task> tasks = DataStore.getInstance().getTasks();

		java.util.List<Task> unassignedTasks = tasks.stream().filter(task -> task.getUsers().isEmpty() == true)
				.collect(Collectors.toList());

		int count = 1;
		for (Task task : unassignedTasks) {
			FileLogger.write(count + " | " + task.getName() + " | Unassigned | " + task.getEstimatedTime() + "h");
			System.out.println(count + " | " + task.getName() + " | Unassigned | " + task.getEstimatedTime() + "h");
			count++;
		}

		return "\n";
	}

	/**
	 * Delete task if it exist
	 * 
	 * @param task
	 * @return
	 */
	public String deleteTask(String task) {
		if (DataStore.getInstance().isTaskExist(task)) {
			DataStore.getInstance().deleteTask(task);

			FileLogger.write(SUCCESS);

			return SUCCESS;
		}

		return "Task does not exist";
	}

	/**
	 * Print all the task which is not finished
	 * 
	 * @return
	 */
	public String printAllUnfinishedTasksByPriority() {

		// get all unfinished task
		java.util.List<Task> unfinishedTasks = DataStore.getInstance().getUnfinishedTask();

		// sort unfinished task by priority
		Collections.sort(unfinishedTasks, new SortTaskByPriority());
		int count = 1;
		for (Task task : unfinishedTasks) {
			String userName = "Unassigned";
			if (task.getUsers().size() > 0) {
				userName = task.getUsers().get(0).getName();
			}
			FileLogger.write(count + " | " + task.getName() + " | " + userName + " | " + task.getEstimatedTime() + "h");
			System.out
					.println(count + " | " + task.getName() + " | " + userName + " | " + task.getEstimatedTime() + "h");
			count++;
		}

		return "\n";
	}

	/**
	 * Move task from one list to another First remove task from the list it belongs
	 * to assign task to new list change the association of task to new list
	 * 
	 * @param taskName
	 * @param listName
	 * @return
	 */
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

		FileLogger.write(SUCCESS);

		return SUCCESS;
	}

	/**
	 * Print the list and task associated to it and the user assigned to the list
	 * 
	 * @param name
	 * @return
	 */
	public String printList(String name) {
		if (DataStore.getInstance().isListExist(name)) {
			List list = DataStore.getInstance().getTaskList(name);
			System.out.println("List " + list.getName());
			FileLogger.write(list.getName());

			for (Task task : list.getTasks()) {
				String userName = "Unassigned";
				if (task.getUsers().size() > 0) {
					userName = task.getUsers().get(0).getName();
				}
				System.out.println(task.getPriority() + " | " + task.getName() + " | " + userName + " | "
						+ task.getEstimatedTime() + "h");
				FileLogger.write(task.getPriority() + " | " + task.getName() + " | " + userName + " | "
						+ task.getEstimatedTime() + "h");
			}
			return "\n";
		}

		return "List does not exist";
	}

	/**
	 * Print all the list
	 * 
	 * @return
	 */
	public String printAllLists() {
		for (List list : DataStore.getInstance().getTaskLists()) {
			this.printList(list.getName());
		}

		return "\n";
	}

	/**
	 * Print all the task user is responsible for ie print all the task user is
	 * assigned to
	 * 
	 * @param name
	 * @return
	 */
	public String printUserTasks(String name) {
		if (DataStore.getInstance().isUserExist(name)) {
			User user = DataStore.getInstance().getUser(name);
			int count = 1;
			for (Task task : user.getTask()) {
				System.out.println(count + " | " + task.getName() + " | " + user.getName() + " | "
						+ task.getEstimatedTime() + "h");
				FileLogger.write(count + " | " + task.getName() + " | " + user.getName() + " | "
						+ task.getEstimatedTime() + "h");
				count++;
			}

			return "\n";
		}

		return "User does not exist";
	}
}
