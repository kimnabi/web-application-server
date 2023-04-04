package db;

import static org.junit.Assert.*;

import org.junit.Test;

import model.User;
import model.UserTest;

public class DataBaseTest {

//	@Test
//	public void addAndFind() {
//		User user = new User("aaa", "password", "name", "test@gmail.net");
//		DataBase.addUser(user);
//		
//		User dbUser= DataBase.findUserById(user.getUserId());
//		//fail("Not yet implemented");
//		assertEquals(user,dbUser);
//	}
	@Test
	public void addAndFindWhenExisted() {
		//User user = new User("aaa", "password", "name", "test@gmail.net");
		User user = UserTest.TEST_USER;
		DataBase.addUser(user);
		
		User dbUser= DataBase.findUserById(user.getUserId());
		//fail("Not yet implemented");
		assertEquals(user,dbUser);
	}
	@Test
	public void addAndFindWhenNotExisted() {
		
		User dbUser= DataBase.findUserById("aaa");
		assertNull(dbUser);
	}

}
