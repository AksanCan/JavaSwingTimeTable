package EVStrategy;

import maxPlus.Matrix;

/**
 * Schnittstelle zu Eigenwertberechnung. Stellt eine Strategy der abstrakten
 * Klasse EVAlgorithm im package MVC dar.
 * 
 * @author Can
 */
public interface EigenValueMethod {

	/**
	 * @author Can
	 * @param matrix stellt die Matrix dar, von der die Eigenwerte berechnet werden
	 *               sollen.
	 * @return Rückgabewert ist ein Eigenwert vom Typ double.
	 */
	public Double specificAlgorithm(Matrix matrix);

}
