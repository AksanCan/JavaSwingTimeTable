package unitTest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import maxPlus.Matrix;
import maxPlus.MaxPlus;

class MatrixTest {

	//Testet ob eine Liste in eine Matrix konvertiert werden kann
	@org.junit.Test
	void testArrayListToMatrix() {
		ArrayList<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>();
		Matrix test = new Matrix(3);
		boolean actual = test.arrayListToMatrix(arrayList);
		assertFalse(actual);
	}
	@org.junit.Test
	void testArrayListToMatrix2() {
		ArrayList<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>();
		ArrayList<String> innereListe = new ArrayList<String>();
		ArrayList<String> innereListe2 = new ArrayList<String>();
		ArrayList<String> innereListe3 = new ArrayList<String>();
		innereListe.add("1");
		innereListe.add("-3");
		innereListe.add("E");
		
		innereListe2.add("-1");
		innereListe2.add("E");
		innereListe2.add("3");
		
		innereListe3.add("E");
		innereListe3.add("3");
		innereListe3.add("-5");
		
		arrayList.add(innereListe);
		arrayList.add(innereListe2);
		arrayList.add(innereListe3);
		
		Matrix test = new Matrix(3);
		boolean actual = test.arrayListToMatrix(arrayList);
		assertTrue(actual);
	}
	
	//Testet Matrix-Addition in MaxPlus
	@org.junit.Test
	void testAdd() {
		Matrix m1 = new Matrix(2);
		Matrix m2 = new Matrix(2);
		Matrix expected = new Matrix(2);
		MaxPlus[][] mp1 = new MaxPlus[2][2];
		MaxPlus[][] mp2 = new MaxPlus[2][2];
		MaxPlus[][] mpexp = new MaxPlus[2][2];
		
		
		mp1[0][0] = new MaxPlus(1);
		mp1[0][1] = new MaxPlus(3);
		mp1[1][0] = new MaxPlus(3);
		mp1[1][1] = new MaxPlus(8);
		
		mp2[0][0] = new MaxPlus(5);
		mp2[0][1] = new MaxPlus(2);
		mp2[1][0] = new MaxPlus(7);
		mp2[1][1] = new MaxPlus(4);
		
		mpexp[0][0] = new MaxPlus(5);
		mpexp[0][1] = new MaxPlus(3);
		mpexp[1][0] = new MaxPlus(7);
		mpexp[1][1] = new MaxPlus(8);
		
		m1.setMyMatrix(mp1);
		m2.setMyMatrix(mp2);
		expected.setMyMatrix(mpexp);
		
		Matrix actual = m1.add(m2);
		
		expected.equals(actual);
	}
	@org.junit.Test
	void testAdd2() {
		
		Matrix m1 = new Matrix(2);
		Matrix m2 = new Matrix(2);
		Matrix expected = new Matrix(2);
		MaxPlus[][] mp1 = new MaxPlus[2][2];
		MaxPlus[][] mp2 = new MaxPlus[2][2];
		MaxPlus[][] mpexp = new MaxPlus[2][2];
		
		
		mp1[0][0] = new MaxPlus(-2);
		mp1[0][1] = new MaxPlus(3);
		mp1[1][0] = new MaxPlus(1);
		mp1[1][1] = new MaxPlus(-4);
		
		mp2[0][0] = new MaxPlus(5);
		mp2[0][1] = new MaxPlus(-7);
		mp2[1][0] = new MaxPlus(7);
		mp2[1][1] = new MaxPlus(8);
		
		mpexp[0][0] = new MaxPlus(5);
		mpexp[0][1] = new MaxPlus(3);
		mpexp[1][0] = new MaxPlus(7);
		mpexp[1][1] = new MaxPlus(-4);
		
		m1.setMyMatrix(mp1);
		m2.setMyMatrix(mp2);
		expected.setMyMatrix(mpexp);
		
		Matrix actual = m1.add(m2);
		
		expected.equals(actual);
		
	}
	@org.junit.Test
	void testAdd3() {
		Matrix m1 = new Matrix(2);
		Matrix m2 = new Matrix(2);
		Matrix expected = new Matrix(2);
		MaxPlus[][] mp1 = new MaxPlus[2][2];
		MaxPlus[][] mp2 = new MaxPlus[2][2];
		MaxPlus[][] mpexp = new MaxPlus[2][2];
		
		
		mp1[0][0] = new MaxPlus("E");
		mp1[0][1] = new MaxPlus("E");
		mp1[1][0] = new MaxPlus("E");
		mp1[1][1] = new MaxPlus("E");
		
		mp2[0][0] = new MaxPlus(-5);
		mp2[0][1] = new MaxPlus(2);
		mp2[1][0] = new MaxPlus(7);
		mp2[1][1] = new MaxPlus(-8);
		
		mpexp[0][0] = new MaxPlus(-5);
		mpexp[0][1] = new MaxPlus(2);
		mpexp[1][0] = new MaxPlus(7);
		mpexp[1][1] = new MaxPlus(-8);
		
		m1.setMyMatrix(mp1);
		m2.setMyMatrix(mp2);
		expected.setMyMatrix(mpexp);
		
		Matrix actual = m1.add(m2);
		
		expected.equals(actual);
		
	}
	@org.junit.Test
	void testAdd4() {
		Matrix m1 = new Matrix(2);
		Matrix m2 = new Matrix(2);
		Matrix expected = new Matrix(2);
		MaxPlus[][] mp1 = new MaxPlus[2][2];
		MaxPlus[][] mp2 = new MaxPlus[2][2];
		MaxPlus[][] mpexp = new MaxPlus[2][2];
		
		
		mp1[0][0] = new MaxPlus("E");
		mp1[0][1] = new MaxPlus("E");
		mp1[1][0] = new MaxPlus("E");
		mp1[1][1] = new MaxPlus("E");
		
		mp2[0][0] = new MaxPlus("E");
		mp2[0][1] = new MaxPlus("E");
		mp2[1][0] = new MaxPlus("E");
		mp2[1][1] = new MaxPlus("E");
		
		mpexp[0][0] = new MaxPlus("E");
		mpexp[0][1] = new MaxPlus("E");
		mpexp[1][0] = new MaxPlus("E");
		mpexp[1][1] = new MaxPlus("E");
		
		m1.setMyMatrix(mp1);
		m2.setMyMatrix(mp2);
		expected.setMyMatrix(mpexp);
		
		Matrix actual = m1.add(m2);
		
		expected.equals(actual);
		
	
	}
	
	
	//Testet Matrix-Multiplikation in MaxPlus
	@org.junit.Test
	void testMatrixMult() {
		Matrix m1 = new Matrix(2);
		Matrix m2 = new Matrix(2);
		Matrix expected = new Matrix(2);
		MaxPlus[][] mp1 = new MaxPlus[2][2];
		MaxPlus[][] mp2 = new MaxPlus[2][2];
		MaxPlus[][] mpexp = new MaxPlus[2][2];
		
		
		mp1[0][0] = new MaxPlus(1);
		mp1[0][1] = new MaxPlus(2);
		mp1[1][0] = new MaxPlus(3);
		mp1[1][1] = new MaxPlus(4);
		
		mp2[0][0] = new MaxPlus(5);
		mp2[0][1] = new MaxPlus(6);
		mp2[1][0] = new MaxPlus(7);
		mp2[1][1] = new MaxPlus(8);
		
		mpexp[0][0] = new MaxPlus(9);
		mpexp[0][1] = new MaxPlus(10);
		mpexp[1][0] = new MaxPlus(11);
		mpexp[1][1] = new MaxPlus(12);
		
		m1.setMyMatrix(mp1);
		m2.setMyMatrix(mp2);
		expected.setMyMatrix(mpexp);
		
		Matrix actual = m1.mult(m2);
		
		expected.equals(actual);
		
	}
	@org.junit.Test
	void testMatrixMult1() {
		
		Matrix m1 = new Matrix(2);
		Matrix m2 = new Matrix(2);
		Matrix expected = new Matrix(2);
		MaxPlus[][] mp1 = new MaxPlus[2][2];
		MaxPlus[][] mp2 = new MaxPlus[2][2];
		MaxPlus[][] mpexp = new MaxPlus[2][2];
		
		
		mp1[0][0] = new MaxPlus(-2);
		mp1[0][1] = new MaxPlus(1);
		mp1[1][0] = new MaxPlus(3);
		mp1[1][1] = new MaxPlus(-4);
		
		mp2[0][0] = new MaxPlus(1);
		mp2[0][1] = new MaxPlus(-5);
		mp2[1][0] = new MaxPlus(-2);
		mp2[1][1] = new MaxPlus(4);
		
		mpexp[0][0] = new MaxPlus(-1);
		mpexp[0][1] = new MaxPlus(5);
		mpexp[1][0] = new MaxPlus(4);
		mpexp[1][1] = new MaxPlus(0);
		
		m1.setMyMatrix(mp1);
		m2.setMyMatrix(mp2);
		expected.setMyMatrix(mpexp);
		
		Matrix actual = m1.mult(m2);
		
		expected.equals(actual);
		
	}
	@org.junit.Test
	void testMatrixMult2() {
		Matrix m1 = new Matrix(2);
		Matrix m2 = new Matrix(2);
		Matrix expected = new Matrix(2);
		MaxPlus[][] mp1 = new MaxPlus[2][2];
		MaxPlus[][] mp2 = new MaxPlus[2][2];
		MaxPlus[][] mpexp = new MaxPlus[2][2];
		
		
		mp1[0][0] = new MaxPlus("E");
		mp1[0][1] = new MaxPlus("E");
		mp1[1][0] = new MaxPlus("E");
		mp1[1][1] = new MaxPlus("E");
		
		mp2[0][0] = new MaxPlus(-5);
		mp2[0][1] = new MaxPlus(3);
		mp2[1][0] = new MaxPlus(7);
		mp2[1][1] = new MaxPlus(-6);
		
		mpexp[0][0] = new MaxPlus("E");
		mpexp[0][1] = new MaxPlus("E");
		mpexp[1][0] = new MaxPlus("E");
		mpexp[1][1] = new MaxPlus("E");
		
		m1.setMyMatrix(mp1);
		m2.setMyMatrix(mp2);
		expected.setMyMatrix(mpexp);
		
		Matrix actual = m1.mult(m2);
		
		expected.equals(actual);
	
	}
	@org.junit.Test
	void testMatrixMult3() {
		Matrix m1 = new Matrix(2);
		Matrix m2 = new Matrix(2);
		Matrix expected = new Matrix(2);
		MaxPlus[][] mp1 = new MaxPlus[2][2];
		MaxPlus[][] mp2 = new MaxPlus[2][2];
		MaxPlus[][] mpexp = new MaxPlus[2][2];
		
		
		mp1[0][0] = new MaxPlus("E");
		mp1[0][1] = new MaxPlus("E");
		mp1[1][0] = new MaxPlus("E");
		mp1[1][1] = new MaxPlus("E");
		
		mp2[0][0] = new MaxPlus("E");
		mp2[0][1] = new MaxPlus("E");
		mp2[1][0] = new MaxPlus("E");
		mp2[1][1] = new MaxPlus("E");
		
		mpexp[0][0] = new MaxPlus("E");
		mpexp[0][1] = new MaxPlus("E");
		mpexp[1][0] = new MaxPlus("E");
		mpexp[1][1] = new MaxPlus("E");
		
		m1.setMyMatrix(mp1);
		m2.setMyMatrix(mp2);
		expected.setMyMatrix(mpexp);
		
		Matrix actual = m1.mult(m2);
		
		expected.equals(actual);
		
	}

	//Testet Matrix-Exponentation in MaxPlus
	@org.junit.Test
	void testexponentiation() {
		Matrix m1 = new Matrix(2);
		Matrix expected= new Matrix(2);
		
		MaxPlus[][] mp1 = new MaxPlus[2][2];
		MaxPlus[][] exp = new MaxPlus[2][2];
		
		mp1[0][0] = new MaxPlus(1);
		mp1[0][1] = new MaxPlus(-2);
		mp1[1][0] = new MaxPlus(3);
		mp1[1][1] = new MaxPlus("E");
		
		exp[0][0] = new MaxPlus(35);
		exp[0][1] = new MaxPlus(120);
		exp[1][0] = new MaxPlus(125);
		exp[1][1] = new MaxPlus(490);
		m1.setMyMatrix(mp1);
		
		Matrix actual = m1.exponentiation(3);
		
		actual.equals(expected);
	}
	@org.junit.Test
	void testexponentiation2() {
		Matrix m1 = new Matrix(2);
		Matrix expected= new Matrix(2);
		
		MaxPlus[][] mp1 = new MaxPlus[2][2];
		MaxPlus[][] exp = new MaxPlus[2][2];
		
		mp1[0][0] = new MaxPlus(-5);
		mp1[0][1] = new MaxPlus("E");
		mp1[1][0] = new MaxPlus(3);
		mp1[1][1] = new MaxPlus("E");
		
		exp[0][0] = new MaxPlus(-5);
		exp[0][1] = new MaxPlus("e");
		exp[1][0] = new MaxPlus(3);
		exp[1][1] = new MaxPlus("e");
		m1.setMyMatrix(mp1);
		
		Matrix actual = m1.exponentiation(5);
		actual.equals(expected);
	}
	@org.junit.Test
	void testexponentiation3() {
		Matrix m1 = new Matrix(2);
		Matrix expected= new Matrix(2);
		
		MaxPlus[][] mp1 = new MaxPlus[2][2];
		MaxPlus[][] exp = new MaxPlus[2][2];
		
		mp1[0][0] = new MaxPlus("E");
		mp1[0][1] = new MaxPlus("E");
		mp1[1][0] = new MaxPlus("E");
		mp1[1][1] = new MaxPlus("E");
		
		exp[0][0] = new MaxPlus("E");
		exp[0][1] = new MaxPlus("e");
		exp[1][0] = new MaxPlus("E");
		exp[1][1] = new MaxPlus("e");
		m1.setMyMatrix(mp1);
		
		Matrix actual = m1.exponentiation(5);
		actual.equals(expected);
	}
	
	//Testet Konvertiert Matrix in eine Array List
	@org.junit.Test
	void testMatrixToArrayList() {

		Matrix m = new Matrix(2);
		m.getMyMatrix()[0][0] = new MaxPlus(1);
		m.getMyMatrix()[0][1] = new MaxPlus(0);
		m.getMyMatrix()[1][0] = new MaxPlus("E");
		m.getMyMatrix()[1][1] = new MaxPlus(-5);
		
		ArrayList<ArrayList<String>> arrayListActual = m.MatrixToArrayList();
		ArrayList<ArrayList<String>> arrayListExpected = new ArrayList<ArrayList<String>>();
		ArrayList<String> arrayListExpectedInnere1 = new ArrayList<String>();
		ArrayList<String> arrayListExpectedInnere2 = new ArrayList<String>();
		
		arrayListExpectedInnere1.add("1");
		arrayListExpectedInnere1.add("0");
		
		arrayListExpectedInnere2.add("E");
		arrayListExpectedInnere2.add("-5");
		
		arrayListExpected.add(arrayListExpectedInnere1);
		arrayListExpected.add(arrayListExpectedInnere2);
		
		arrayListActual.equals(arrayListExpected);
		
		
	}
	
	//Testet Transponieren einer Matrix
	@org.junit.Test
	void testTranspose() {
		Matrix m1 = new Matrix(3);
		Matrix expected = new Matrix(3);
		MaxPlus[][] mp1 = new MaxPlus[3][3];
		MaxPlus[][] mp1T = new MaxPlus[3][3];
		
		mp1[0][0] = new MaxPlus(-3);
		mp1[0][1] = new MaxPlus(2);
		mp1[0][2] = new MaxPlus("E");
		
		mp1[1][0] = new MaxPlus(5);
		mp1[1][1] = new MaxPlus("E");
		mp1[1][2] = new MaxPlus("E");
		
		mp1[2][0] = new MaxPlus(-3);
		mp1[2][1] = new MaxPlus(2);
		mp1[2][2] = new MaxPlus("E");
		
		mp1T[0][0] = new MaxPlus(-3);
		mp1T[0][1] = new MaxPlus(5);
		mp1T[0][2] = new MaxPlus(-3);
		
		mp1T[1][0] = new MaxPlus(2);
		mp1T[1][1] = new MaxPlus("E");
		mp1T[1][2] = new MaxPlus(2);
		
		mp1T[2][0] = new MaxPlus("E");
		mp1T[2][1] = new MaxPlus("E");
		mp1T[2][2] = new MaxPlus("E");
		
		m1.setMyMatrix(mp1);
		expected.setMyMatrix(mp1T);
		Matrix actual = m1.transpose(); 
		
		expected.equals(actual);
	}

}
