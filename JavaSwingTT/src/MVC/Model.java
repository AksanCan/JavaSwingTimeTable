package MVC;

import maxPlus.Matrix;

/**
 * Das Interface Model erbt vom Interface Subject und stellt somit selbst ein
 * Subject dar.
 *
 * @author Can
 */
public interface Model extends Subject {

	/**
	 * @author Can
	 * @return Namen eines Model-Objektes als String.
	 */
	public String getName();

	/**
	 * @author Can
	 * @param matrix
	 *            Matrix von der der Eigenwert und Eigenvektor berechnet werden soll
	 */
	public void calculate(Matrix matrix);

}
