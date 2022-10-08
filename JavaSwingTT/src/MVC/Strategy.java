package MVC;

import maxPlus.Matrix;

/**
 * Strategy der GUI.
 * 
 * @author Can
 * 
 */
public interface Strategy {

	/**
	 * Methode zur Berechnung der Eigenwerte und Eigenvektoren einer Matrix über ein
	 * Model-Objekt, das über seinen Namen ausgewählt wird.
	 * 
	 * @param matrix
	 *            Matrix von der die Eigenwerte und Eigenvektoren berechnet werden
	 *            soll.
	 * @param algorithm_name
	 *            Name vom Model-Objekt.
	 * 
	 * @author Can
	 * 
	 */
	public void calculate(Matrix matrix, String algorithm_name);

	/**
	 * Methode zum Erstellen eines Fahrplans vom Typ FileType (z.B. als
	 * Excel-Datei).
	 * 
	 * @param typ
	 *            FileType (Enumeration), das den Datei-Typ festlegt
	 * @author Can
	 * 
	 */
	public void generateFile(FileType typ);

	/**
	 * @return String-Array, das alle Namen der Model-Objekte enthält, auf die die
	 *         GUI zugriff hat.
	 * @author Can
	 * 
	 */
	public String[] getModelNames();

}
