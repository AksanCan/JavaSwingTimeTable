package unitTest;

import org.junit.Test;

//import org.junit.jupiter.api.Test;
import maxPlus.Matrix;
import maxPlus.MaxPlus;
import maxPlus.Vector;

class VectorTest {

	//Testet Vektor Addition in MaxPlus
	@org.junit.Test
	void testAdd() {
		Vector v1 = new Vector(3);
		Vector v2 = new Vector(3);

		MaxPlus[] array1 = new MaxPlus[3];
		array1[0] = new MaxPlus(2);
		array1[1] = new MaxPlus(-5);
		array1[2] = new MaxPlus(0);
		v1.setMyVector(array1);

		MaxPlus[] array2 = new MaxPlus[3];
		array2[0] = new MaxPlus("E");
		array2[1] = new MaxPlus(2);
		array2[2] = new MaxPlus(-2);
		v2.setMyVector(array2);

		MaxPlus[] arrayexpected = new MaxPlus[3];
		arrayexpected[0] = new MaxPlus(2);
		arrayexpected[1] = new MaxPlus(2);
		arrayexpected[2] = new MaxPlus(0);

		Vector expected = new Vector(3);
		expected.setMyVector(arrayexpected);

		Vector actual = v1.add(v2);
		
		actual.equals(expected);
	}

	@Test
	void testAdd2() {
		Vector v1 = new Vector(3);
		Vector v2 = new Vector(3);

		MaxPlus[] array1 = new MaxPlus[3];
		array1[0] = new MaxPlus(2);
		array1[1] = new MaxPlus(-5);
		array1[2] = new MaxPlus(0);
		v1.setMyVector(array1);

		MaxPlus[] array2 = new MaxPlus[3];
		array2[0] = new MaxPlus("E");
		array2[1] = new MaxPlus("E");
		array2[2] = new MaxPlus("E");
		v2.setMyVector(array2);

		MaxPlus[] arrayexpected = new MaxPlus[3];
		arrayexpected[0] = new MaxPlus(2);
		arrayexpected[1] = new MaxPlus(-5);
		arrayexpected[2] = new MaxPlus(0);

		Vector expected = new Vector(3);
		expected.setMyVector(arrayexpected);

		Vector actual = v1.add(v2);

		
		actual.equals(expected);
	}

	@Test
	void testAdd3() {
		Vector v1 = new Vector(3);
		Vector v2 = new Vector(3);

		MaxPlus[] array1 = new MaxPlus[3];
		array1[0] = new MaxPlus("E");
		array1[1] = new MaxPlus("E");
		array1[2] = new MaxPlus("E");
		v1.setMyVector(array1);

		MaxPlus[] array2 = new MaxPlus[3];
		array2[0] = new MaxPlus("E");
		array2[1] = new MaxPlus("E");
		array2[2] = new MaxPlus("E");
		v2.setMyVector(array2);

		MaxPlus[] arrayexpected = new MaxPlus[3];
		arrayexpected[0] = new MaxPlus("E");
		arrayexpected[1] = new MaxPlus("E");
		arrayexpected[2] = new MaxPlus("E");

		Vector expected = new Vector(3);
		expected.setMyVector(arrayexpected);

		Vector actual = v1.add(v2);

		
		actual.equals(expected);
	}

	//Testet Vektor Multiplikation in MaxPlus
	@Test
	void testMult() {
		Vector v = new Vector(2);
		Matrix m = new Matrix(2);
		Vector expected = new Vector(2);

		MaxPlus[][] m1 = new MaxPlus[2][2];
		m1[0][0] = new MaxPlus(1);
		m1[0][1] = new MaxPlus(2);
		m1[1][0] = new MaxPlus(3);
		m1[1][1] = new MaxPlus(4);

		MaxPlus[] array1 = new MaxPlus[2];
		array1[0] = new MaxPlus(2);
		array1[1] = new MaxPlus(5);

		MaxPlus[] arrayexpected = new MaxPlus[2];
		arrayexpected[0] = new MaxPlus(7);
		arrayexpected[1] = new MaxPlus(9);

		expected.setMyVector(arrayexpected);
		v.setMyVector(array1);
		m.setMyMatrix(m1);
		Vector actual = v.mult(m);

		
		actual.equals(expected);
	}

	@Test
	void testMult2() {
		Vector v = new Vector(2);
		Matrix m = new Matrix(2);
		Vector expected = new Vector(2);

		MaxPlus[][] m1 = new MaxPlus[2][2];
		m1[0][0] = new MaxPlus(3);
		m1[0][1] = new MaxPlus("E");
		m1[1][0] = new MaxPlus(-2);
		m1[1][1] = new MaxPlus(0);

		MaxPlus[] array1 = new MaxPlus[2];
		array1[0] = new MaxPlus(-2);
		array1[1] = new MaxPlus("E");

		MaxPlus[] arrayexpected = new MaxPlus[2];
		arrayexpected[0] = new MaxPlus(1);
		arrayexpected[1] = new MaxPlus(-4);

		expected.setMyVector(arrayexpected);
		v.setMyVector(array1);
		m.setMyMatrix(m1);
		Vector actual = v.mult(m);

		actual.equals(expected);
	}

	//Testet Skalare Vektor Multi in MaxPlus
	@Test
	void testScalar_mult() {
		Vector v = new Vector(3);
		MaxPlus mp = new MaxPlus(2);
		Vector expected = new Vector(3);

		MaxPlus[] array1 = new MaxPlus[3];
		array1[0] = new MaxPlus(-1);
		array1[1] = new MaxPlus(5);
		array1[2] = new MaxPlus("E");

		MaxPlus[] arrayexpected = new MaxPlus[3];
		arrayexpected[0] = new MaxPlus(2);
		arrayexpected[1] = new MaxPlus(5);
		arrayexpected[2] = new MaxPlus(2);

		v.setMyVector(array1);
		Vector actual = v.scalar_mult(mp);
		expected.setMyVector(arrayexpected);

		actual.equals(expected);

	}

	@Test
	void testScalar_mult2() {
		Vector v = new Vector(3);
		MaxPlus mp = new MaxPlus("E");
		Vector expected = new Vector(3);

		MaxPlus[] array1 = new MaxPlus[3];
		array1[0] = new MaxPlus(-1);
		array1[1] = new MaxPlus(0);
		array1[2] = new MaxPlus("E");

		MaxPlus[] arrayexpected = new MaxPlus[3];
		arrayexpected[0] = new MaxPlus(-1);
		arrayexpected[1] = new MaxPlus(0);
		arrayexpected[2] = new MaxPlus("E");

		v.setMyVector(array1);
		Vector actual = v.scalar_mult(mp);
		expected.setMyVector(arrayexpected);

		actual.equals(expected);

	}

	//Testet Rundung eines Vektors in MaxPlus
	@Test
	void testRoundVector() {
		Vector roundVector = new Vector(3);
		Vector actual = new Vector(3);
		Vector expected = new Vector(3);
		
		MaxPlus[] mp = new MaxPlus[3];
		MaxPlus[] expc = new MaxPlus[3];
		
		mp[0] = new MaxPlus(3.1234);
		mp[1] = new MaxPlus(3.580);
		mp[2] = new MaxPlus(6.0246);
		
		expc[0] = new MaxPlus(3.123);
		expc[1] = new MaxPlus(3.58);
		expc[2] = new MaxPlus(6.025);
		
		roundVector.setMyVector(mp);
		
		actual = roundVector.roundVector(3);
		
		actual.equals(expected);
	}
}
