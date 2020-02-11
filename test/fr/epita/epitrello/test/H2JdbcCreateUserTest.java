/**
 * 
 */
package fr.epita.epitrello.test;


import fr.epita.epitrello.services.Configuration;

/**
 * @author kuwar
 *
 */

public class H2JdbcCreateUserTest {

	private static final String INSERT_QUERY = "INSERT INTO USERS(NAME) VALUES (?)";

	public static void main(String[] args) {
		// url
		String url = Configuration.getValue("db.url");
		// user
		String user = Configuration.getValue("db.user");
		// password
		String password = Configuration.getValue("db.password");
	}

}
