package com.jmbargueno.festapp.festappv1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jmbargueno.festapp.festappv1.model.UserFA;
import com.jmbargueno.festapp.festappv1.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	UserService userService;

	@Test
	public void searchByName() {
		UserFA user = new UserFA("test", "test", null, null, null, "test", "1234", "test", true, null, null);
		userService.save(user);

		assertEquals(user, userService.findByName("test"));

	}
}
