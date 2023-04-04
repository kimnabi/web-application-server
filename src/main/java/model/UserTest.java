package model;

import static org.junit.Assert.*;

import org.junit.Test;

import db.DataBase;

public class UserTest {
	
	public static User TEST_USER = new User("aaa", "password", "name", "test@gmail.net");

	@Test
	public void matchPassword() {
		//User user = new User("aaa", "password", "name", "test@gmail.net");
		boolean result = TEST_USER.matchPassword("password");
		assertTrue(result);
		//fail("Not yet implemented");
	}
	@Test
	public void notMatchPassword() {
		//User user = new User("aaa", "password", "name", "test@gmail.net");
		boolean result = TEST_USER.matchPassword("password2");
		assertFalse(result);
		//fail("Not yet implemented");
	}
	@Test
	public void login() throws Exception {
		User user = UserTest.TEST_USER;
		DataBase.addUser(user);
		User.login(TEST_USER.getUserId(),TEST_USER.getPassword());
	}
	@Test(expected=UserNotFoundException.class)
	public void loginWhenNotExistedUser() throws Exception {
		User.login("testUser",TEST_USER.getPassword());
	}

}
