package in28Min.rest.webservice.User;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/* This annotation binds the HTTP Status Code 404 (Not Found) to the exception we created for users not found
 * and will return this code each time a user is not found. */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String message) {
		super(message);
		
		
	}

	
}
