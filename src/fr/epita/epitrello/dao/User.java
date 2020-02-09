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

	String name;
	List<Task> tasks = new ArrayList<Task>();

	/**
	 * 
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

	public void setTask(Task task) {
		this.tasks.add(task);
	}

	public List<Task> getTask() {
		return this.tasks;
	}

}
