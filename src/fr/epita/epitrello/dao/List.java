/**
 * 
 */
package fr.epita.epitrello.dao;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * @author kuwar
 *
 */
public class List {

	/**
	 * Name of list
	 */
	String name;

	/**
	 * List of tasks in the list
	 */
	java.util.List<Task> tasks = new ArrayList<Task>();

	/**
	 * 
	 */
	public List() {
	}

	/**
	 * 
	 */
	public List(String name) {
		this.name = name;
	}

	/**
	 * Add list
	 * 
	 * @param name
	 */
	public void addList(String name) {
		this.name = name;

		DataStore.getInstance().storeList(this);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public java.util.List<Task> getTasks() {
		return this.tasks;
	}

	public void setTasks(Task task) {
		this.tasks.add(task);
	}

	/**
	 * Remove task from the list
	 * 
	 * @param taskToFilterOut
	 */
	public void remove(Task taskToFilterOut) {
		this.tasks = this.tasks.stream().filter(task -> task.getName() != taskToFilterOut.getName())
				.collect(Collectors.toList());
	}

}
