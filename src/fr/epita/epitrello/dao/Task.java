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

	List list = new List();
	String name;
	int estimatedTime;
	int priority;
	String description;
	boolean completed = false;

	java.util.List<User> users = new ArrayList<User>();

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

	public void setUsers(User user) {
		this.users.add(user);
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean status) {
		this.completed = status;
	}

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
