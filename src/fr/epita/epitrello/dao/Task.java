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
//		super(name);
	}

	String list;
	String name;
	int estimatedTime;
	int priority;
	String description;

	public void addTask(String list, String name, int estimatedTime, int priority, String description) {
		this.name = name;
		this.estimatedTime = estimatedTime;
		this.priority = priority;
		this.description = description;

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

}
