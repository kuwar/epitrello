/**
 * 
 */
package fr.epita.epitrello.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kuwar
 *
 */
public class DataStore {

	HashMap<String, List<User>> userMap = new HashMap<String, List<User>>();
	List<User> userList = new ArrayList<User>();

	List<fr.epita.epitrello.dao.List> taskList = new ArrayList<fr.epita.epitrello.dao.List>();

	List<Task> tasks = new ArrayList<Task>();

	HashMap<User, Task> userTask = new HashMap<User, Task>();

	private static DataStore dataStore;

	private DataStore() {
	}

	public static DataStore getInstance() {
		if (dataStore == null) {
			// if there is no instance available... create new one
			dataStore = new DataStore();
		}

		return dataStore;
	}

	public void storeUser(User user) {

		this.userList.add(user);
	}

	public List<User> getUserList() {
		return this.userList;
	}

	public User getUser(String name) {
		for (User user : this.userList) {
			if (user.name == name) {
				return user;
			}
		}

		return null;
	}

	public boolean isUserExist(String name) {
		for (User user : this.userList) {
			if (user.name == name) {
				return true;
			}
		}
		return false;
	}

	/**
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
	 * @param list
	 */
	public void storeList(fr.epita.epitrello.dao.List list) {
		this.taskList.add(list);
	}

	/**
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
	 * @param task
	 */
	public void storeTask(Task task) {
		this.tasks.add(task);
	}

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

	public Task getTask(String name) {
		for (Task task : this.tasks) {
			if (task.getName() == name) {
				return task;
			}
		}

		return null;
	}

	public List<Task> getTasks() {
		return this.tasks;
	}

	public void printAll() {
		System.out.println("---Users---");
		this.userList.stream().forEach(user -> System.out.println(user.getName()));

		System.out.println("---List---");
		this.taskList.stream().forEach(list -> System.out.println(list.getName()));

		System.out.println("---Task---");
		this.tasks.stream().forEach(task -> System.out.println(task.getName()));
	}

	/**
	 * @param task
	 * @param user
	 */
	public void assignTask(Task task, User user) {
		userTask.put(user, task);
	}

	/**
	 * @param task
	 */
	public void deleteTask(String name) {
		for (int i = 0; i < this.tasks.size(); i++) {
			if (this.tasks.get(i).getName() == name) {
				this.tasks.remove(i);
			}
		}
	}

	/**
	 * @return
	 */
	public List<Task> getUnfinishedTask() {
		return this.tasks.stream().filter(task -> task.isCompleted() == false).collect(Collectors.toList());
	}

}
