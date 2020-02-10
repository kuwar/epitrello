/**
 * 
 */
package fr.epita.epitrello.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kuwar
 *
 */
public class User {

	/**
	 * User name
	 */
	String name;

	/**
	 * List of task user is responsible for
	 */
	List<Task> tasks = new ArrayList<Task>();

	/**
	 * Create user
	 */
	public void addUser(String name) {
		this.name = name;

		DataStore.getInstance().storeUser(this);
	}

	/**
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Assign task to the user
	 * 
	 * @param task
	 */
	public void setTask(Task task) {
		this.tasks.add(task);
	}

	/**
	 * Get the list of task user is responsible for
	 * 
	 * @return
	 */
	public List<Task> getTask() {
		return this.tasks;
	}

}
