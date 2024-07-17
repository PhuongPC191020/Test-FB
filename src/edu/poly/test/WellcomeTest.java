package edu.poly.test;

import org.testng.annotations.Test;

public class WellcomeTest {
	@Test(groups = "regression")
	public void test1() {
		System.out.println("Wellcome test 1");
	}
	
	@Test(groups = "regression")
	public void test2() {
		System.out.println("Wellcome test 2");
	}
	@Test(groups = "smoketest")
	public void test3() {
		System.out.println("Wellcome test 3");
	}
	
	@Test(groups = "regression")
	public void test4() {
		System.out.println("Wellcome test 4");
	}
}
