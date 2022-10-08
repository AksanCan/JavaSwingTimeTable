package MVC;

import maxPlus.Vector;

public interface Observer {

	/**
	 * Methode aktuallisiert den Eigenwert und die Eigenvektoren konkreter
	 * Beobachter-Instanzen.
	 * 
	 * @param eigenwert
	 *            Eigenwert einer Matrix
	 * @param vector
	 *            Eigenvektoren einer Matrix
	 * @author Can
	 */
	public void update(Double eigenwert, Vector... vector);

}
