/**
 * 
 */
package fr.epita.epitrello.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kuwar
 * 
 *         A data structure to store the data of the system. It follows the
 *         singleton class structure User, List and Task are stored in ArrayList
 * 
 *         The task list is similar to the category of the task. And the task
 *         belongs to specific category.
 */
public class DataStore {

	/**
	 * Data structure to store the users of the system
	 */
	List<User> userList = new ArrayList<User>();

	/**
	 * Data structure to store lists
	 */
	List<fr.epita.epitrello.dao.List> taskList = new ArrayList<fr.epita.epitrello.dao.List>();

	/**
	 * Data structure to store tasks
	 */
	List<Task> tasks = new ArrayList<Task>();

	private static DataStore dataStore;

	private DataStore() {
	}

	/**
	 * get the single instance of the class
	 * 
	 * @return
	 */
	public static DataStore getInstance() {
		if (dataStore == null) {
			// if there is no instance available... create new one
			dataStore = new DataStore();
		}

		return dataStore;
	}

	/**
	 * Store users in the data structure
	 * 
	 * @param user
	 */
	public void storeUser(User user) {

		this.userList.add(user);
	}

	/**
	 * get the list of users store in the system
	 * 
	 * @return
	 */
	public List<User> getUserList() {
		return this.userList;
	}

	/**
	 * Get user matching the user name
	 * 
	 * @param name
	 * @return
	 */
	public User getUser(String name) {
		for (User user : this.userList) {
			if (user.name == name) {
				return user;
			}
		}

		return null;
	}

	/**
	 * Check user
	 * 
	 * @param name
	 * @return
	 */
	public boolean isUserExist(String name) {
		for (User user : this.userList) {
			if (user.name == name) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check list
	 * 
	 * @param name
	 * @return
	 */
	public boolean isListExist(String name) {
		for (fr.epita.epitrello.dao.List task : this.taskList) {
			if (task.getName() == name) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Store list in data structure
	 * 
	 * @param list
	 */
	public void storeList(fr.epita.epitrello.dao.List list) {
		this.taskList.add(list);
	}

	/**
	 * get list based on list name
	 * 
	 * @param name
	 * @return
	 */
	public fr.epita.epitrello.dao.List getTaskList(String name) {
		for (fr.epita.epitrello.dao.List list : this.taskList) {
			if (list.getName() == name) {
				return list;
			}
		}

		return null;
	}

	/**
	 * get all the list
	 * 
	 * @return
	 */
	public List<fr.epita.epitrello.dao.List> getTaskLists() {
		return this.taskList;
	}

	/**
	 * Check task
	 * 
	 * @param name
	 * @return
	 */
	public boolean isTaskExist(String name) {
		for (Task task : this.tasks) {
			if (task.getName() == name) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Store task
	 * 
	 * @param task
	 */
	public void storeTask(Task task) {
		this.tasks.add(task);
	}

	/**
	 * Edit task
	 * 
	 * @param name
	 * @param estimatedTime
	 * @param priority
	 * @param description
	 * @return
	 */
	public boolean editTask(String name, int estimatedTime, int priority, String description) {
		for (int i = 0; i < this.tasks.size(); i++) {
			if (this.tasks.get(i).getName() == name) {
				this.tasks.get(i).setEstimatedTime(estimatedTime);
				this.tasks.get(i).setPriority(priority);
				this.tasks.get(i).setDescription(description);
			}
		}

		return false;
	}

	/**
	 * Get task
	 * 
	 * @param name
	 * @return
	 */
	public Task getTask(String name) {
		for (Task task : this.tasks) {
			if (task.getName() == name) {
				return task;
			}
		}

		return null;
	}

	/**
	 * Get all tasks
	 * 
	 * @return
	 */
	public List<Task> getTasks() {
		return this.tasks;
	}

	/**
	 * print data for visualizing purpose
	 * 
	 * Print individual data
	 */
	public void printAll() {
		System.out.println("---Users---");
		this.userList.stream().forEach(user -> System.out.println(user.getName()));

		System.out.println("---List---");
		this.taskList.stream().forEach(list -> System.out.println(list.getName()));

		System.out.println("---Task---");
		this.tasks.stream().forEach(task -> System.out.println(task.getName()));
	}

	/**
	 * Delete task
	 * 
	 * @param nanme
	 */
	public void deleteTask(String name) {
		for (int i = 0; i < this.tasks.size(); i++) {
			if (this.tasks.get(i).getName() == name) {
				this.tasks.remove(i);
			}
		}
	}

	/**
	 * Get list of unfinished task
	 * 
	 * @return
	 */
	public List<Task> getUnfinishedTask() {
		return this.tasks.stream().filter(task -> task.isCompleted() == false).collect(Collectors.toList());
	}

}
