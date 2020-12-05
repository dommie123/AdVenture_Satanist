package assets;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import assets.SpeedBoost;
import game.Game;

class BoostTest {
	
	private SpeedBoost shortBoost;
	private SpeedBoost midBoost;
	private SpeedBoost longBoost;
	
	private List<Business> businesses = Arrays.asList(new Business(0, 5, 0, false, "Test"));
	
	private Game game;
	
	@BeforeEach
	void setUp() throws Exception {
		shortBoost = new SpeedBoost(2, 1, 0, "1 hour Boost");
		midBoost = new SpeedBoost(3, 4, 0, "4 hour Boost");
		longBoost = new SpeedBoost(5, 24, 0, "24 hour Boost");
		
		game = new Game();
	}

	@AfterEach
	void tearDown() throws Exception {
		shortBoost = null;
		midBoost = null;
		longBoost = null;
		game = null;
	}

	@Test
	void testLongSetPurchased() {
		assertEquals(false, longBoost.isPurchased());
		assertEquals(null, longBoost.getExpireDate());
		assertEquals(null, longBoost.getExpireTime());
		
		//game.getPlayer().buySpeedBoost(businesses, longBoost);
		
		assertTrue(longBoost.isPurchased());
		assertEquals(LocalTime.now().plusHours((int) longBoost.getLength()), longBoost.getExpireTime());
		assertEquals(LocalDate.now().plusDays((int) longBoost.getLength() / 24), longBoost.getExpireDate());
	}

	@Test
	void testSetPurchased() {
		assertEquals(false, shortBoost.isPurchased());
		assertEquals(null, shortBoost.getExpireDate());
		assertEquals(null, shortBoost.getExpireTime());
		assertEquals(false, midBoost.isPurchased());
		assertEquals(null, midBoost.getExpireDate());
		assertEquals(null, midBoost.getExpireTime());
		
		game.getPlayer().buySpeedBoost(businesses, shortBoost);
		game.getPlayer().buySpeedBoost(businesses, midBoost);
		
		assertTrue(shortBoost.isPurchased());
		//assertEquals(LocalDate.now().plusDays((int) shortBoost.getLength() / 24), shortBoost.getExpireDate());
		assertEquals(LocalTime.now().plusHours((int) shortBoost.getLength()), shortBoost.getExpireTime());
		assertTrue(midBoost.isPurchased());
		//assertEquals(LocalDate.now().plusDays((int) midBoost.getLength() / 24), midBoost.getExpireDate());
		assertEquals(LocalTime.now().plusHours((int) midBoost.getLength()), midBoost.getExpireTime());
	}

	@Test
	void testCheckTime() {
		game.getPlayer().buySpeedBoost(businesses, shortBoost);
		game.getPlayer().buySpeedBoost(businesses, midBoost);
		game.getPlayer().buySpeedBoost(businesses, longBoost);
		
		if (LocalDate.now().equals(null)) {
			if (LocalTime.now().equals(shortBoost.getExpireTime()))
				assertTrue(!shortBoost.isPurchased());
			else 
				assertTrue(shortBoost.isPurchased());
			
			if (LocalTime.now().equals(midBoost.getExpireTime()))
				assertTrue(!midBoost.isPurchased());
			else
				assertTrue(midBoost.isPurchased());
			
			if (LocalTime.now().equals(longBoost.getExpireTime()))
				assertTrue(!longBoost.isPurchased());
			else
				assertTrue(longBoost.isPurchased());
		}
		
		else {
			if (LocalTime.now().equals(shortBoost.getExpireTime()) && LocalDate.now().equals(shortBoost.getExpireDate()))
				assertTrue(!shortBoost.isPurchased());
			else 
				assertTrue(shortBoost.isPurchased());
			
			if (LocalTime.now().equals(midBoost.getExpireTime()) && LocalDate.now().equals(midBoost.getExpireDate()))
				assertTrue(!midBoost.isPurchased());
			else
				assertTrue(midBoost.isPurchased());
			
			if (LocalTime.now().equals(longBoost.getExpireTime()) && LocalDate.now().equals(longBoost.getExpireDate()))
				assertTrue(!longBoost.isPurchased());
			else
				assertTrue(longBoost.isPurchased());
		}
	}
}
