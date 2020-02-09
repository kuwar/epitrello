/**
 * 
 */
package fr.epita.epitrello.dao;

/**
 * @author kuwar
 *
 */
public class List {

	String name;

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
}
