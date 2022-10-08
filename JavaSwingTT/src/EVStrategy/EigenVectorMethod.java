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
	 * @param eigenvalue stellt den zugehörigen Eigenwert der Matrix dar.
	 * @return Rückgabewert ist ein Array aus Eigenvektoren vom Typ Vector[].
	 */
	public Vector[] eigenvector(Matrix matrix, double... eigenvalue);

}
