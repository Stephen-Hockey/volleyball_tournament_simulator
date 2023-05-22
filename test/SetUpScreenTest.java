package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.SetUpScreen;

public class SetupScreenTest {

	@Test
	public void validStringTest() {
		SetUpScreen window = new SetUpScreen(null);
		assertTrue(window.validString("John Smith", 3, 10));
	}

	@Test
	public void validStringSpecialCharactersTest() {
		SetUpScreen window = new SetUpScreen(null);
		assertFalse(window.validString("J*hn/$mith", 3, 10));
	}
	
	@Test
	public void validStringTooLongTest() {
		SetUpScreen window = new SetUpScreen(null);
		assertFalse(window.validString("Johnathan Smithington", 3, 10));
	}
	
	@Test
	public void validStringTooShortTest() {
		SetUpScreen window = new SetUpScreen(null);
		assertFalse(window.validString("JS", 3, 10));
	}

}
