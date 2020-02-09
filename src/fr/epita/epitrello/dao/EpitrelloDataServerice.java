/**
 * 
 */
package fr.epita.epitrello.dao;

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

		Task task = new Task(list);

		task.addTask(list, name, estimatedTime, priority, description);

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
		for (User user : taskDetail.getUsers()) {
			System.out.println("Assigned to " + user.getName());
		}

		return "\n";
	}

	public String completeTask(String task) {
		if (DataStore.getInstance().isTaskExist(task)) {
			DataStore.getInstance().getTask(task).setStatus(true);
			
			return "Success";
		}
		
		return "Task does not exist.";
	}

	public void printUsersByPerformance() {

	}

	public void printUsersByWorkload() {

	}

	public void printUnassignedTasksByPriority() {

	}

	public void deleteTask(String task) {

	}

	public void printAllUnfinishedTasksByPriority() {

	}

	public void moveTask(String task, String list) {

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
