package EVStrategy;

import java.util.ArrayList;

import maxPlus.Matrix;
import maxPlus.MaxPlus;
import maxPlus.Vector;

/**
 * Klasse implementiert das Interface EigenVectorMethod und berechnet den
 * Eigenvektor einer Matrix mittels dem A-Lambda-Algorithmus.
 * 
 * @author Can
 */
public class ALambda implements EigenVectorMethod {

	private Matrix lambdaMatrix;
	private Matrix lambdaPlusMatrix;
	private MaxPlus eigenValue;
	private int dimension;
	private Vector[] eigenvector;

	/**
	 * Methode berechnet einen Eigenvektor mit dem A-Lambda-Algorithmus.
	 * 
	 * @author Can
	 */
	@Override
	public Vector[] eigenvector(Matrix matrix, double... eigenvalue) {

		if (eigenvalue == null)
			return null;

		this.eigenvector = null;
		ArrayList<Vector> vectorlist = new ArrayList<>();
		this.dimension = matrix.getDimension();
		this.eigenValue = new MaxPlus(eigenvalue[0]);
		this.lambdaMatrix = matrix.scalarMult(this.eigenValue.calculateNegativ());
		this.lambdaPlusMatrix = Matrix.identityMatrix(this.dimension).add(this.lambdaMatrix);
		iterate(2);
		verifyEigenvector(1, matrix, vectorlist);
		return this.eigenvector;
	}

	/**
	 * Methode führt rekursiv die Iterationen vom ALambda-Algorithmus durch und wird
	 * von der Methode eigenvector(...) aufgerufen.
	 * 
	 * @param counter
	 *            zählt die Iterationsschritte.
	 * 
	 * @author Can
	 */
	private void iterate(int counter) {

		if (counter > (this.dimension - 1))
			return;

		this.lambdaPlusMatrix = this.lambdaPlusMatrix.add(this.lambdaMatrix.exponentiation(counter));
		counter++;
		iterate(counter);
	}

	/**
	 * Methode überprüft rekursiv welche Spalten der Matrix (lambdaPlusMatrix)
	 * Eigenvektoren sind.
	 * 
	 * @param columnindex
	 *            Spalten Index der Matrix.
	 * @param matrix
	 *            Matrix dessen Eigenvektoren verifiziert werden sollen.
	 * @param vectorlist
	 *            enthaelt nach der Durchfuehrung der Methode alle Informationen
	 *            ueber die Eigenvektoren.
	 * 
	 * @author Can
	 */
	private void verifyEigenvector(int columnindex, Matrix matrix, ArrayList<Vector> vectorlist) {

		if (columnindex > this.dimension) {
			if (!vectorlist.isEmpty()) {
				this.eigenvector = new Vector[vectorlist.size()];
				for (int i = 0; i < vectorlist.size(); i++)
					this.eigenvector[i] = vectorlist.get(i);
			}
			return;
		}

		Vector temp = this.lambdaPlusMatrix.getColumn(columnindex);
		if (matrix.isEigenVector(temp, this.eigenValue)
				|| compareApproximately(temp.scalar_mult(this.eigenValue), temp.mult(matrix), 6)) {
			vectorlist.add(this.lambdaPlusMatrix.getColumn(columnindex));
		}
		columnindex++;
		verifyEigenvector(columnindex, matrix, vectorlist);
	}

	/**
	 * Hilfsmethode überprüft ungefähre Übereinstimmung zweier Vektoren.
	 * 
	 * @param v1
	 *            1. Vektor der überprüft werden soll.
	 * @param v2
	 *            2. Vektor der überprüft werden soll.
	 * @param n
	 *            Anzahl der Nachkommastellen
	 * 
	 * @return true wenn v1 und v2 bis auf n Nachkommastellen die selben Einträge
	 *         haben, sonst false.
	 * 
	 * @author Can
	 */
	private static boolean compareApproximately(Vector v1, Vector v2, int n) {

		boolean bool = true;
		v1.roundVector(n);
		v2.roundVector(n);

		if (!(v1.equals(v2)))
			bool = false;

		return bool;
	}

}
