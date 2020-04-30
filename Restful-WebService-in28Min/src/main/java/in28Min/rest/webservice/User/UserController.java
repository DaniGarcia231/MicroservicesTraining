package in28Min.rest.webservice.User;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {
	
	@Autowired
	private UserDaoService myUserService;
	
	
/********************************* C O N T R O L L E R  M E T H O D S *********************************/
	
//	------- GET METHODS ------
	
	@GetMapping("/Users")
	public List<User> retrieveAllUsers(){
		
		return myUserService.findAll();		
	}
	
	@GetMapping("/Users/{id}")
	public User retrieveUser(@PathVariable int id) {
		
		User user = myUserService.findUser(id);
		
		if(user == null) 
			throw new UserNotFoundException("Id " + id + " was not found, please enter a valid Id.");
			
		return user;
	}
	
	
//	------- POST METHODS ------
	
	/** 
	 * The @Valid annotation is a default implementation in the ClassPath because of 
	 * SpringBootStarterWeb dependency. This annotation allows us to check the request
	 * and see if it matches the validations we have in the User component class,
	 * min and max characters for name attribute or past dates for birthDate attribute.
	 * In case the request fails this validations, our CustomizedResponseEntityExceptionHandl class
	 * will take care of the exception returning what went wrong with the request.
	 */
	@PostMapping("/Users")
	public ResponseEntity<Object> addUser(@Valid @RequestBody User user) {
		
		User savedUser = myUserService.addUser(user);
		
		/**
		 * This is creating an URI location of the new user that has been created. 
		 * The purpose of this is to return the URI location of the new user for whatever 
		 * purpose is needed. For example you could do a GET request to the returned URI, etc. 
		 * This are the best practices of restful web services.
		 */
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
		
		/**
		 * Here we are returning the URI location of the new user as an HTTP Status Code 201, 
		 * that means "CREATED". This information is valuable for the customer because it can be 
		 * certain that the POST request created the new user, and we are returning the URI at 
		 * which it can be found.   
		 */
		return ResponseEntity.created(location).build();
	}
	
	
// ------- DELETE METHODS ------
	
	@DeleteMapping("/Users/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable int id) {
		
		User user = myUserService.deleteUserById(id);;
		
		if(user == null) 
			throw new UserNotFoundException("Id " + id + " was not found, please enter a valid Id.");
		
		return ResponseEntity.noContent().build();
	}
}
