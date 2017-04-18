import plugins.MainInterface;

public class InterfaceTest implements MainInterface {

	@Override
	public String sayHello() {
		return "Hello World";
	}

}
