package in28Min.rest.webservice.HelloWorld;

public class HelloWorldBean {

	private String beanBody;
	
	
/**************************************** C O N S T R U C T O R S ********************************/
	public HelloWorldBean(String beanBody) {
		
		this.beanBody = beanBody;
	}
	
	public HelloWorldBean() {
		
	}
	
	
/************************************** M E T H O D S *********************************************/
	
	@Override
	public String toString() {
		
		return "HelloWorldBean [beanBody=" + beanBody + "]";
	}

/*************************************** G E T T E R S  &  S E T T E R S **************************/
	public String getBeanBody() {
		return beanBody;
	}

	public void setBeanBody(String beanBody) {
		this.beanBody = beanBody;
	}
	
}
