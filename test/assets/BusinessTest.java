package assets;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.Player;
import assets.Business;

class BusinessTest {

	Business pentagrams;
	Player p1;

	@BeforeEach
	void setUp() throws Exception {
		pentagrams = new Business(0.25, 0.5, 1.0, true, "Pentagrams", new ArrayList<HiddenMultiplier>(Arrays.asList(new HiddenMultiplier(2, 10), new HiddenMultiplier(3, 20))));
		p1 = new Player("Test Player");
	}

	@AfterEach
	void tearDown() throws Exception {
		pentagrams = null;
		p1 = null;
	}

	@Test
	void test() {
		assertEquals(0.50, pentagrams.getRevenue());
		assertEquals(0.25, pentagrams.getCost());
		assertEquals(1, pentagrams.getWaitTime());

		assertEquals(1, pentagrams.getQuantityPurchased());
		assertTrue(!pentagrams.getMultipliers().isEmpty());

		for (int i = 0; i < 9; i++) {
			while (p1.getCurrentMoney() < pentagrams.getCost()) {
				p1.gainRevenue(pentagrams);
			}
			p1.buyBusiness(pentagrams);
		}

		//assertEquals(1, pentagrams.getRevenue());
		//assertEquals(1.50, pentagrams.getCost());

		assertEquals(10, pentagrams.getQuantityPurchased());
		assertEquals(2, pentagrams.getMultiplier());
		
		for (int i = 0; i < 10; i++) {
			while (p1.getCurrentMoney() < pentagrams.getCost()) {
				p1.gainRevenue(pentagrams);
			}
			p1.buyBusiness(pentagrams);
		}
		
		assertEquals(20, pentagrams.getQuantityPurchased());
		assertEquals(6, pentagrams.getMultiplier());
	}

}
