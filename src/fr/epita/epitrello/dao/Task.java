/**
 * 
 */
package fr.epita.epitrello.dao;

/**
 * @author kuwar
 *
 */
public class Task extends List {

	/**
	 * @param name
	 */
	public Task(String name) {
		super(name);
	}

	String list;
	String name;
	int estimatedTime;
	int priority;
	String description;

	public void addTask(String list, String name, int estimatedTime, int priority, String description) {

	}

	public void editTask(String task, int estimatedTime, int priority, String description) {

	}
}
