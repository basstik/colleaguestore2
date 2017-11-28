package a;


import java.util.Date;

import org.junit.Test;
//import org.junit.runner.RunWith;

//@RunWith(JUnit4.class)
public class TestClass {

	@Test
	public void myTest() {
		Long t = 1511445648487L;
		System.out.println(new Date(t));
		
		Long t2 = 1511451869436L;
		System.out.println(new Date(t2));
	}
}
