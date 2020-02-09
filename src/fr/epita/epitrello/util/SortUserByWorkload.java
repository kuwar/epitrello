/**
 * 
 */
package fr.epita.epitrello.util;

import java.util.Comparator;
import java.util.List;

import fr.epita.epitrello.dao.Task;
import fr.epita.epitrello.dao.User;

/**
 * @author kuwar
 *
 */
public class SortUserByWorkload implements Comparator<User>{
	
	public int compare(User a, User b) {
		List<Task> aTask = a.getTask();
		List<Task> bTask = b.getTask();
		
		return aTask.get(0).getEstimatedTime() - bTask.get(0).getEstimatedTime();
	}

}
