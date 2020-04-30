package in28Min.rest.webservice.Exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import in28Min.rest.webservice.User.UserNotFoundException;


/**
 * ControllerAdvice annotations lets us share multiple things across our other 
 * controller classes 
 */

/** 
 * ResponseEntityExceptionHandler is an Abstract class that can basically be extended 
 * to provide centralized exception handling across all exception handling methods. 
 * This is going to be use as a base class to provide some default exception handling 
 * for all our requests  
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	
// 	----------- EXCEPTION -------------	
	
	/** 
	 * ExceptionHandler annotation lets us specify that the method that is binded with 
	 * will control the exception handling of ALL exceptions. You can specify which 
	 * exceptions you want the method to take care it can be only one or more. 
	 */
	
	/**
	 * This method exists in the abstract class ResponseEntityExceptionHandler 
	 * and we have overwritten it.We specify how our exceptions across the whole 
	 * project will be handled 
	 */
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {

		/**
		 * We create a new object of our ExceptionResponse class and we fill 
		 * it with the details provided  by the exception "ex" and the description 
		 * from the request.
		 */
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		/**
		 * This will make sure that every exception response has our customized 
		 * format and each one will return and HTTP Status Code of 500 
		 */
		return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);	
	}
	
	
// 	----------- EXCEPTION -------------	
	
	/**
	 * This method is specifically in charge of taking care of our custom exception
	 * UserNotFoundException in order to return a correct HTTP Status Code - 404
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> 
	handleUserNotFoundException(UserNotFoundException ex, WebRequest request) throws Exception {

		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	

	
// 	----------- EXCEPTION -------------	
	
	/** 
	 * This method was taken from our extended abstract class and is in charge of
	 * handling the exceptions that have a Not Valid Argument, this goes in hand with the 
	 * @Valid annotation we are using in the UserController and the numerous annotations
	 * in the User component that are use as the validations. If the request fails any of
	 * the validations, this exception will handle it. 
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		/**
		 * In here, we use the .getBindingResult() method because it contains the information
		 * of what went wrong in the request. 		 
		 */
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(new Date(), "Validation Failed.", ex.getBindingResult().toString());
		
		return new ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	
}
