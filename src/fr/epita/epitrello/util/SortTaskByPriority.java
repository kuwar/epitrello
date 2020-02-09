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
public class SortTaskByPriority implements Comparator<Task>{
	
	public int compare(Task a, Task b) {
		
		return a.getPriority() - b.getPriority();
	}

}
