/**
 * 
 */
package fr.epita.epitrello.dao;

import java.util.ArrayList;

/**
 * @author kuwar
 *
 */
public class List {

	String name;
	
	java.util.List<Task> tasks = new ArrayList<Task>();
	
	/**
	 * 
	 */
	public List() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 */
	public List(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}
	

	/**
	 * 
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
	
}
