/**
 * 
 */
package fr.epita.epitrello.test;

import fr.epita.epitrello.dao.EpitrelloDataServerice;

/**
 * @author kuwar
 *
 */
public class UserStoreTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EpitrelloDataServerice dataserverice = new EpitrelloDataServerice();
		
		System.out.println(dataserverice.addUser("Thomas")); // addUser(string username)
		System.out.println(dataserverice.addUser("AmirAli"));
		System.out.println(dataserverice.addUser("Rabih"));

	}

}
