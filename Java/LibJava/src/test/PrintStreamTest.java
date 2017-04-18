package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import test.base.BaseTest;

public class PrintStreamTest extends BaseTest{
 
	@Override
	public void test() {
		testSystemOutStream();
		testSystemInStream();      
	}
	
	/**测试System.setOut()*/
	private void testSystemOutStream(){
        try {       
//        	先定义输出流类型，不能放在System.setOut(out);
        	PrintStream out = System.out;
            System.setOut(new PrintStream("./log.txt"));  
            
            
            int age = 11;  
            System.out.println("年龄变量成功定义，初始值为11");  
            String sex = "女";  
            System.out.println("年龄变量成功定义，初始值为女");  
            // 整合2个变量  
            String info = "这是个" + sex + "孩子，应该有" + age + "岁了";  
            System.out.println(info);
            
            System.setOut(out);            
            System.out.println("程序运行完毕，请查看日志");  
        } catch (FileNotFoundException e) {  
            e.printStackTrace();  
        }
	}
	
	/**测试System.setIn()*/
	private void testSystemInStream(){
		 try {  
	            InputStream in = System.in; 
	            
	            InputStream ps = new FileInputStream("./log.txt");  
	            System.setIn(ps);
	            
	            Scanner scanner = new Scanner(System.in);  
	            String line = "";  
	            while (scanner.hasNextLine()) {  
	                line = scanner.nextLine();  
	                System.out.println(line);  
	            }  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        }  
	}
}
