package TimeTableStrategy;

import maxPlus.Vector;

/**
 * Schnittstelle der Klasse TimeTable zum Erzeugen von Fahrpl‰nen.
 * 
 * @author Can
 *
 */
public interface TimeTableGenerator {

	/**
	 * Methode zum erzeugen eines Takt-Fahrplans. Die Methode generiert aus einem
	 * Eigenvektor und einem Eigenwert einen Fahrplan. Die Eintr‰ge im Eigenvektor
	 * representiert dabei die Abfahrszeiten der Linien und der Eigenwert die L‰nge
	 * von einem Zyklus. Die Dimension vom Vektor legt auﬂerdem fest wie viele
	 * Linien vorhanden sind.
	 * 
	 * @author Can
	 * @param eigenvalue
	 *            jeweiliger Eigenwert
	 * @param vector
	 *            jeweiliger Eigenvektor
	 *
	 */
	public void generateFile(double eigenvalue, Vector vector);

}
