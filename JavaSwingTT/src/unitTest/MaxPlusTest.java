package unitTest;

import static org.junit.Assert.*;
import org.junit.experimental.theories.suppliers.TestedOn;
import maxPlus.MaxPlus;

class MaxPlusTest {

	//Test der Max_Plus Addition
	@org.junit.Test
	void testAdd() {
		MaxPlus mp1 = new MaxPlus(1);
		MaxPlus mp2 = new MaxPlus(-2);
		MaxPlus actual = mp1.add(mp2);
		MaxPlus expected = new MaxPlus(1);
		actual.equals(expected);
	}
	@org.junit.Test
	void testAdd2() {
		MaxPlus mp1 = new MaxPlus(1);
		MaxPlus mp2 = new MaxPlus("E");
		MaxPlus actual = mp1.add(mp2);
		MaxPlus expected = new MaxPlus(1); 
		actual.equals(expected);
	}
	@org.junit.Test
	void testAdd3() {
		MaxPlus mp1 = new MaxPlus("E");
		MaxPlus mp2 = new MaxPlus("E");
		MaxPlus actual = mp1.add(mp2);
		MaxPlus expected = new MaxPlus("E"); 
		actual.equals(expected);
	}
	//Test der Max_Plus Multiplikation
	@org.junit.Test
		void testMult() {
			MaxPlus mp1 = new MaxPlus(1);
			MaxPlus mp2 = new MaxPlus(12);
			MaxPlus actual = mp1.mult(mp2);
			MaxPlus expected = new MaxPlus(13); 
			actual.equals(expected);
		}
	@org.junit.Test
		void testMult1() {
			MaxPlus mp1 = new MaxPlus(1);
			MaxPlus mp2 = new MaxPlus(-2);
			MaxPlus actual = mp1.mult(mp2);
			MaxPlus expected = new MaxPlus(-1); 
			actual.equals(expected);
		}
	@org.junit.Test
			void testMult2() {
				MaxPlus mp1 = new MaxPlus(-3);
				MaxPlus mp2 = new MaxPlus(-4);
				MaxPlus actual = mp1.mult(mp2);
				MaxPlus expected = new MaxPlus(-7); 
				actual.equals(expected);
			}
		@org.junit.Test
		void testMult3() {
			MaxPlus mp1 = new MaxPlus(1);
			MaxPlus mp2 = new MaxPlus("E");
			MaxPlus actual = mp1.mult(mp2);
			MaxPlus expected = new MaxPlus("E"); 
			actual.equals(expected);
		}
		@org.junit.Test
		void testMult4() {
			MaxPlus mp1 = new MaxPlus("E");
			MaxPlus mp2 = new MaxPlus("E");
			MaxPlus actual = mp1.mult(mp2);
			MaxPlus expected = new MaxPlus("E"); 
			actual.equals(expected);
		}
	
	//Vergleichsmethoden von zwei MaxPlus Objekten. 
		@org.junit.Test
	void testCompare1() {
		MaxPlus mp1 = new MaxPlus("E");
		MaxPlus mp2 = new MaxPlus("E");
		assertTrue(mp1.compare1(mp2));
	}
		@org.junit.Test
	void test2Compare1() {
		MaxPlus mp1 = new MaxPlus("E");
		MaxPlus mp2 = new MaxPlus(3);
		assertFalse(mp1.compare1(mp2));
	}
		@org.junit.Test
	void test3Compare1() {
		MaxPlus mp1 = new MaxPlus(-8);
		MaxPlus mp2 = new MaxPlus(3);
		assertFalse(mp1.compare1(mp2));
	}

		@org.junit.Test
	void testCompare2() {
		MaxPlus mp1 = new MaxPlus("E");
		MaxPlus mp2 = new MaxPlus("E");
		assertTrue(mp1.compare2(mp2));
	}
		@org.junit.Test
	void test2Compare2() {
		MaxPlus mp1 = new MaxPlus(3);
		MaxPlus mp2 = new MaxPlus("E");
		assertTrue(mp1.compare2(mp2));
	}
		@org.junit.Test
	void test3Compare2() {
		MaxPlus mp1 = new MaxPlus("E");
		MaxPlus mp2 = new MaxPlus(3);
		assertTrue(mp1.compare2(mp2));
	}
	@org.junit.Test
	void test4Compare2() {
		MaxPlus mp1 = new MaxPlus(3);
		MaxPlus mp2 = new MaxPlus(5);
		assertFalse(mp1.compare2(mp2));
	}

//	@Test
//	void testCalculateNegativ() {
//		MaxPlus mp1 = new MaxPlus("E");
//		MaxPlus actual = mp1.calculateNegativ();
//		MaxPlus expected = null;
//		actual.equals(expected);
//	}
	
	//Test zur Berechnung des Negativen einer MaxPlus Zahl (value)
	@org.junit.Test
	void test2CalculateNegativ() {
		MaxPlus mp1 = new MaxPlus("4");
		MaxPlus actual = mp1.calculateNegativ();
		MaxPlus expected = new MaxPlus("-4");
		actual.equals(expected);
	}
	@org.junit.Test
	void tes3CalculateNegativ() {
		MaxPlus mp1 = new MaxPlus("-6");
		MaxPlus actual = mp1.calculateNegativ();
		MaxPlus expected = new MaxPlus("6");
		actual.equals(expected);
	}

}
