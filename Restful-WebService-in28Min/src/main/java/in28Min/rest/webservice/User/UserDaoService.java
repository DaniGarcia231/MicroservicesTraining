package in28Min.rest.webservice.User;

import java.util.Date;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<>();
	
	private static int usersCount = 4;
	
	static {
		
		users.add(new User(1, "Adam", new Date()));
		users.add(new User(2, "Tom", new Date()));
		users.add(new User(3, "Jane", new Date()));
		users.add(new User(4, "Allison", new Date()));
	}
	
	
	public List<User> findAll(){
		
		return users;
	}
	
	public User addUser(User user) {
		
		if(user.getId() == null) {			
			user.setId(++usersCount);	
		}
		
		users.add(user);
		
		return user;
	}
	
	
	public User findUser(int id) {
		
		for(User u: users) {
			
			if(u.getId() == id) 
				return u;
		}
		
		return null;
	}

	
	public User deleteUserById(int id) {
		
		Iterator<User> iterator = users.iterator();
		
		while(iterator.hasNext()) {
			
			User user = iterator.next();
			
			if(user.getId() == id) {
				iterator.remove();
				return user;
			}
		}
		
		return null;
	}
	
}
