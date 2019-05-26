/**
 * 
 */
package com.jmbargueno.festapp.festappv1;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jmbargueno.festapp.festappv1.model.Consumable;
import com.jmbargueno.festapp.festappv1.service.ConsumableService;

/**
 * @author jmbargueno
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumableServiceTest {
	@Autowired
	ConsumableService consumableService;
	@Autowired
	Consumable consumable;
	
	@Test
	public void searcByIdTest(){
		consumableService.save(consumable = new Consumable(100L, "Prueba", null, 24, 24, null, null));
		assertEquals(consumable, consumableService.findById(100L));
		
		
	}
	
}
