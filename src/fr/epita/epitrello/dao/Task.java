/**
 * 
 */
package fr.epita.epitrello.dao;

import java.util.ArrayList;

/**
 * @author kuwar
 *
 */
public class Task {

	/**
	 * list in which task is associated with
	 */
	List list = new List();

	/**
	 * name of list
	 */
	String name;

	/**
	 * estimated time for list
	 */
	int estimatedTime;

	/**
	 * Priority of the list
	 */
	int priority;

	/**
	 * detail description of the list
	 */
	String description;

	/**
	 * Status to check whether list is complete or not
	 */
	boolean completed = false;

	/**
	 * List of users associated with the list
	 * 
	 * For further extension a task can be assigned to multiple users
	 */
	java.util.List<User> users = new ArrayList<User>();

	/**
	 * Add task
	 * 
	 * @param list
	 * @param name
	 * @param estimatedTime
	 * @param priority
	 * @param description
	 */
	public void addTask(List list, String name, int estimatedTime, int priority, String description) {
		this.name = name;
		this.estimatedTime = estimatedTime;
		this.priority = priority;
		this.description = description;
		this.setList(list);

		DataStore.getInstance().storeTask(this);
	}

	public void editTask(String task, int estimatedTime, int priority, String description) {

	}

	/**
	 * @param estimatedTime2
	 */
	public void setEstimatedTime(int estimatedTime2) {
		this.estimatedTime = estimatedTime2;
	}

	/**
	 * @param priority2
	 */
	public void setPriority(int priority2) {
		this.priority = priority2;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param description2
	 */
	public void setDescription(String description2) {
		this.description = description2;
	}

	public int getEstimatedTime() {
		return estimatedTime;
	}

	public int getPriority() {
		return priority;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * Assign user to the task
	 * 
	 * @param user
	 */
	public void setUsers(User user) {
		this.users.add(user);
	}

	public boolean isCompleted() {
		return completed;
	}

	/**
	 * Complete the task
	 * 
	 * @param status
	 */
	public void setCompleted(boolean status) {
		this.completed = status;
	}

	/**
	 * Get users assigned to the task
	 * 
	 * @return
	 */
	public java.util.List<User> getUsers() {
		return this.users;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

}
