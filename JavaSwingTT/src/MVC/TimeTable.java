package MVC;

import javax.swing.JOptionPane;
import TimeTableStrategy.TimeTableGenerator;
import TimeTableStrategy.SimpleFactory;
import maxPlus.Vector;

/**
 * Diese Klasse implementiert das Interface Observer und wird mit den
 * aktuallisierten Eigenwerten und Eigenvektoren beliefert, sobald durch die GUI
 * ein Algorithmus durchgeführt wurde. Diese Klasse verfügt zudem über Methoden
 * zum Erstellen eines Fahrplans.
 * 
 * @author Can
 *
 */
public class TimeTable implements Observer {

	private TimeTableGenerator generator;
	private Double eigenvalue;
	private Vector vector;
	private static final String MESSAGE = "Damit ein Fahrplan erstellt werden kann, muss ein Eigenwert und Eigenvektor vorliegen. Stellen Sie sicher, dass ein Eigenwert und Eigenvektor berechnet wurde, bevor Sie diese Funktion auswählen.";
	private static final String CAPTION = "FEHLER!";

	/**
	 * Konstruktor
	 * 
	 * @author Can
	 * @param typ
	 *            legt zu Beginn fest welches Fileformat erstellt werden kann
	 * @param subject
	 *            die Instanzen die beobachtet werden soll
	 *
	 */
	public TimeTable(FileType typ, Subject... subject) {
		for (Subject s : subject)
			s.addObserver(this);
		setFileForm(typ);
	}

	@Override
	public void update(Double eigenvalue, Vector... vector) {
		this.eigenvalue = eigenvalue;

		if (vector != null)
			this.vector = vector[0];
		else
			this.vector = null;
	}

	/**
	 * Methode erzeugt aus der Kenntniss über einen Eigenvektor und einem Eigenwert
	 * einen Fahrplan in einem gewünschten Fileformat. Im Falle, dass die Datei
	 * nicht erstellt werden kann, wird ein Nachrichtenfenster ausgegeben.
	 * 
	 * @author Can
	 *
	 */
	public void generateFile() {
		if (this.eigenvalue!=null && this.vector != null)
			this.generator.generateFile(this.eigenvalue, this.vector);
		else
			pop_up(MESSAGE, CAPTION);
	}

	/**
	 * Über diese Methode kann der Algorithmus zum Fahrplan erstellen (die konkrete
	 * Strategy) ausgetauscht werden. Es kann Wahlweise zwischen den Filetypen
	 * Excel, Pdf und Text-Datei gewählt werden.
	 * 
	 * @author Can
	 * @param typ
	 *            legt den Filetyp fest
	 *
	 */
	public void setFileForm(FileType typ) {
		this.generator = SimpleFactory.buildTimeTableGenerator(typ);
	}

	/**
	 * Statische Methode die eine Nachricht ausgibt.
	 * 
	 * @param message
	 *            Nachrichtentext
	 * @param caption
	 *            Überschrift vom Nachrichtenfenster
	 * @author Can
	 *
	 */
	private static void pop_up(String message, String caption) {
		JOptionPane.showMessageDialog(null, message, caption, JOptionPane.WARNING_MESSAGE);
	}

}
