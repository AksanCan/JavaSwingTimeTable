package EVStrategy;

import maxPlus.Matrix;
import maxPlus.MaxPlus;
import maxPlus.Vector;

public class Karp implements EigenValueMethod {

	/**
	 * berechnet den Eigenwert mittels Karp-Algorithmus und gibt eben diesen
	 * Eigenwert zurück
	 * 
	 * @author Martina
	 */
	@Override
	public Double specificAlgorithm(Matrix matrix) {

		Vector x0 = new Vector(matrix.getDimension(), 1); // Startvektor: erste Spalte der Einheitsmatrix

//System.out.println("Einheitsvektor als Startvektor x0: ");
//for (int i = 0; i < matrix.getDimension(); i++) {
//	System.out.println(x0.getMyVector()[i].getValue());
//}
		Vector[] iteratedVectors = new Vector[matrix.getDimension() + 1];

		iteratedVectors[0] = x0;

		for (int i = 0; i < matrix.getDimension(); i++) { // hier werden die xk iterativ berechnet

			iteratedVectors[i + 1] = iteratedVectors[i].mult(matrix);

		}
		
//System.out.println("iteratedVectors: ");
//for (int i = 0; i < matrix.getDimension() + 1; i++) {
//	System.out.println("Der " + i + ". Vektor aus iteratedVectors: ");
//	for (int j = 0; j < iteratedVectors[i].getMyVector().length; j++) {
//		System.out.println(iteratedVectors[i].getMyVector()[j].getValue());
//	}
//
//}


		Vector[] helpVectors = new Vector[matrix.getDimension()]; //helpVectors[i] entspricht der i-ten Spalte
																	//der MaxMin-Tabelle von Seite 52 in Dila Akins Bachelor-Thesis
		double a, b, c;

		for (int i = 0; i < helpVectors.length; i++) { // die Vektoren in helpVectors initialisieren, damit sie eine
														// passende Laenge haben
			helpVectors[i] = new Vector(matrix.getDimension());
		}

		for (int i = 0; i < matrix.getDimension(); i++) {
			for (int j = 0; j < matrix.getDimension(); j++) {
				if (!iteratedVectors[matrix.getDimension()].getMyVector()[i].isIstE()
						&& !iteratedVectors[j].getMyVector()[i].isIstE()) {
					a = (Double.parseDouble(iteratedVectors[matrix.getDimension()].getMyVector()[i].getValue())
							- Double.parseDouble(iteratedVectors[j].getMyVector()[i].getValue()))
							/ (matrix.getDimension() - j);
					helpVectors[i].getMyVector()[j] = new MaxPlus(a);
				} else if (iteratedVectors[matrix.getDimension()].getMyVector()[i].isIstE()
						&& !iteratedVectors[j].getMyVector()[i].isIstE()) { // (-inf - endliche Zahl)/(n-j) = -inf
					helpVectors[i].getMyVector()[j] = new MaxPlus("E");
				} else if (!iteratedVectors[matrix.getDimension()].getMyVector()[i].isIstE()
						&& iteratedVectors[j].getMyVector()[i].isIstE()) { // (endliche Zahl - (-inf))/(n-j) = inf
					// bleibt NULL
				} else { // beide sind E: -inf-(-inf)=0
					helpVectors[i].getMyVector()[j] = new MaxPlus(0);
				}
			}
		}
//int iplus1 = 0;
//for (int i = 0; i < matrix.getDimension(); i++) {
//	iplus1++;
//	System.out.println("Die Spalte i = " + iplus1 + " aus dem Skript auf Seite 52:");
//	for (int j = 0; j < matrix.getDimension(); j++) {
//		if (helpVectors[i].getMyVector()[j] == null) {
//			System.out.println("unendlich");
//		} else {
//			System.out.println(helpVectors[i].getMyVector()[j].getValue());
//		}
//		
//	}
//}
		MaxPlus min[] = new MaxPlus[matrix.getDimension()];

		for (int i = 0; i < helpVectors.length; i++) {
			for (int j = 0; j < helpVectors.length; j++) {
				if (min[i] == null) {
					if (helpVectors[i].getMyVector()[j] == null) {
						// tu nichts
					} else if (helpVectors[i].getMyVector()[j].isIstE()) {
						min[i] = new MaxPlus("E");
						j = helpVectors[i].getMyVector().length;
					} else {// helpVectors ist hier eine Zahl
						min[i] = new MaxPlus(Double.parseDouble(helpVectors[i].getMyVector()[j].getValue()));
					}
				} else if (helpVectors[i].getMyVector()[j] != null) {
					if (!helpVectors[i].getMyVector()[j].isIstE() && !min[i].isIstE()) {
						a = Double.parseDouble(helpVectors[i].getMyVector()[j].getValue()) * (-1);
						helpVectors[i].getMyVector()[j].setValue(Double.toString(a));
						b = Double.parseDouble(min[i].getValue()) * (-1);
						min[i].setValue(Double.toString(b));
						min[i].setValue(min[i].add(helpVectors[i].getMyVector()[j]).getValue());
						c = Double.parseDouble(min[i].getValue()) * (-1);
						min[i].setValue(Double.toString(c));
					} else if (helpVectors[i].getMyVector()[j].isIstE() || min[i].isIstE()) {
						min[i].setIstE(true);
						;
						j = helpVectors[i].getMyVector().length;
					}

				} else { // wenn helpVectors null ist
							// tu nichts/ ueberspringe den Wert
				}
			}
		}
//iplus1 = 0;
//for (int i = 0; i < min.length; i++) {
//	iplus1++;
//	System.out.println("Minimum für die " + iplus1 + "-te Spalte im Skript: " + min[i].getValue());
//}

		MaxPlus max = new MaxPlus();

		for (int i = 0; i < min.length; i++) {
			if (min[i] == null) { // Spezialfall, der nur vorkommt, wenn alle Eintraege für ein i in helpVectors
									// null (unendlich) waren
				max = min[i];
				i = min.length;
			} else {
				if (i == 0) {
					max = min[i];
				} else {
					max = max.add(min[i]);
				}
			}

		}
		
		if (max == null || max.isIstE()) { //wenn es also keinen Eigenwert gibt
			return null;
		}

//System.out.println("Maximum: " + max.getValue());
		
		return Double.parseDouble(max.getValue());
		
	}
}

