package in28Min.rest.webservice.HelloWorld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("/hello-world")
	public String getHelloWorld() {
		
		return "Hello World";
	}
	
	@GetMapping("/hello-world-bean")
	public HelloWorldBean getHelloWorldBean() {
		
		return new HelloWorldBean("Hello World, im a bean");
	}
	
	
	@GetMapping("/hello-world-bean/PathVar/{message}")
	public HelloWorldBean getHelloWorldBeanPathVar(@PathVariable String message) {
		
		return new HelloWorldBean(String.format("Hello World, im a bean, %s", message));
	}
	
}
