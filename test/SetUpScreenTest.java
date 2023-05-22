package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.SetUpScreen;

public class SetUpScreenTest {

	@Test
	public void test() {
		String[] inputs = {"  !@## abc", "aa", "abcdefghijklmnopqrstuvwxyz"};
		SetUpScreen window = new SetUpScreen();
		for (String input: inputs) {
			assertEquals(false, window.validString(input, 3, 10));
		}
	}

}
