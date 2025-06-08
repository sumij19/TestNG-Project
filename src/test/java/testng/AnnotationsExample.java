package testng;

import org.testng.annotations.Test;

public class AnnotationsExample extends BaseClass {
	
	@Test
	public void test1() {
		System.out.println("Inside testcase1");
	}

	@Test
	public void test2() {
		
		System.out.println("Inside test case2");
	}

}
