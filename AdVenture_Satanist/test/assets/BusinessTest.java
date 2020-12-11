package assets;

import static org.junit.jupiter.api.Assertions.*;

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
		pentagrams = new Business(0.25, 0.50, 1, true, "Pentagrams");
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
		assertEquals(0.50, pentagrams.getCost());
		assertEquals(1, pentagrams.getWaitTime());

		assertEquals(1, pentagrams.getQuantityPurchased());

		p1.gainRevenue(pentagrams);
		p1.gainRevenue(pentagrams);
		p1.buyBusiness(pentagrams);

		assertEquals(1, pentagrams.getRevenue());
		assertEquals(1.50, pentagrams.getCost());

		assertEquals(2, pentagrams.getQuantityPurchased());

	}

}
