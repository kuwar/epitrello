/**
 * 
 */
package fr.epita.epitrello.dao;

/**
 * @author kuwar
 *
 */
public class User {

	String name;

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

}
