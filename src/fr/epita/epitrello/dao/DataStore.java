/**
 * 
 */
package fr.epita.epitrello.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author kuwar
 *
 */
public class DataStore {

	HashMap<String, List<User>> userMap = new HashMap<String, List<User>>();
	List<User> userList = new ArrayList<User>();

	List<fr.epita.epitrello.dao.List> taskList = new ArrayList<fr.epita.epitrello.dao.List>();

	List<Task> tasks = new ArrayList<Task>();

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

}
