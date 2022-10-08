package EVStrategy;

import maxPlus.Vector;
import maxPlus.Matrix;

/**
 * Schnittstelle zu Eigenvektorberechnung. Stellt eine Strategy der abstrakten
 * Klasse EVAlgorithm im package MVC dar.
 * 
 * @author Can
 */
public interface EigenVectorMethod {

	/**
	 * 
	 * @author Can
	 * @param matrix     stellt die Matrix dar, von der die Eigenvektoren berechnet
	 *                   werden sollen.
	 * @param eigenvalue stellt den zugeh�rigen Eigenwert der Matrix dar.
	 * @return R�ckgabewert ist ein Array aus Eigenvektoren vom Typ Vector[].
	 */
	public Vector[] eigenvector(Matrix matrix, double... eigenvalue);

}
