import test.FileUtilsTest;
import test.PrintStreamTest;
import test.StringUtilsTest;

public class Main {

	public static void main(String[] args) {
		(new StringUtilsTest()).test();	
		(new FileUtilsTest()).test();
		(new PrintStreamTest()).test();
	}
	
}
