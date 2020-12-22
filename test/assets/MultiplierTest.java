package assets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import assets.Multiplier;
import assets.Business;
import game.Player;

class MultiplierTest {

	Business pentagrams;
	Multiplier cultMembers;
	Player p1;

	@BeforeEach
	void setUp() throws Exception {
		pentagrams = new Business(0.25, 0.50, 1, true, "Pentagrams", null, null);
		cultMembers = new Multiplier(1.50, 3, "Cult Members");
		p1 = new Player("Test Player");
	}

	@AfterEach
	void tearDown() throws Exception {
		pentagrams = null;
		cultMembers = null;
		p1 = null;
	}

	@Test
	void test() {
		assertFalse(cultMembers.isPurchased());
		assertEquals(1, pentagrams.getMultiplier());

		while (p1.getCurrentMoney() < cultMembers.getCost())
			p1.gainRevenue(pentagrams);

		p1.buyMultiplier(pentagrams, cultMembers);

		assertTrue(cultMembers.isPurchased());
		assertEquals(3, pentagrams.getMultiplier());
	}

}
