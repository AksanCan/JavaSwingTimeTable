package TimeTableStrategy;

import java.awt.FileDialog;
import java.awt.Frame;
import java.util.ArrayList;
import maxPlus.MaxPlus;
import maxPlus.Vector;

/**
 * Abstrakte Klasse GenerateTimeTable stellt die Basisklasse der konkreten
 * Strategys der Klasse TimeTable dar. Diese Klasse implementiert das Interface
 * TimeTableGenerator (die Strategy von TimeTable). Diese Klasse sammelt alle
 * Schnittstellen der konkreten Klassen zum Erstellen eines Fahrplans, wie zum
 * Beispiel eine Methode, die den Speicherort einer Datei festlegt, damit es
 * innerhalb der Unterklassen zu keiner Codeverdoppelung kommt.
 * 
 * @author Can
 *
 */
public abstract class GenerateTimeTable implements TimeTableGenerator {

	private String fileNameAndPath;
	private int counter = 0;
	private ArrayList<String[]> timeTable;
	private static final int LIMIT = 15;
	private int dimension;
	private String outputDescription;
	private FileDialog dialog;

	public GenerateTimeTable(String outputDescription) {
		this.outputDescription = outputDescription;
	}

	protected int getDimension() {
		return dimension;
	}

	protected String getFileNameAndPath() {
		return fileNameAndPath;
	}

	protected ArrayList<String[]> getTimeTable() {
		return timeTable;
	}

	protected static int getLimit() {
		return LIMIT;
	}

	public String getOutputDescription() {
		return this.outputDescription;
	}

	/**
	 * Methode erstellt eine Arrayliste, die einen zyklischen Fahrplan beschreibt.
	 * 
	 * @param eigenvalue
	 *            die Laenge eines Zykluses
	 * @param vector
	 *            die Abfahrtszeiten
	 * @return Methode gibt einen Fahrplan zurueck
	 * 
	 * @author Can
	 *
	 */
	private ArrayList<String[]> generateTimeTable(double eigenvalue, Vector vector) {

		ArrayList<String[]> temp = new ArrayList<>();
		String[] departureTime = new String[this.dimension];
		Vector actual = vector;
		temp.add(beginningColumn());

		while (this.counter <= LIMIT + 1) {
			departureTime = departureTime(actual);
			temp.add(departureTime);
			actual = actual.scalar_mult(new MaxPlus(eigenvalue));
			this.counter++;
		}
		this.counter = 0;
		return temp;
	}

	/**
	 * Hilfsmethode convertiert eine Variable vom Typ Vector zu einem String-Array.
	 * 
	 * @param vector
	 *            Vektor der konvertiert werden soll.
	 * @return Methode gibt einen String-Array zurück.
	 * 
	 * @author Can
	 *
	 */
	private String[] departureTime(Vector vector) {

		String[] departureTime = new String[this.dimension];
		for (int i = 0; i < dimension; i++) {
			if (!vector.getMyVector()[i].isIstE())
				departureTime[i] = convert(vector.getDoubleValue(i));
			else
				departureTime[i] = "----------";
		}
		return departureTime;
	}

	/**
	 * Hilfsmethode convertiert eine Variable vom Typ double zu einem String, derart
	 * dass zum Beispiel aus 34, 10:00 Uhr wird. Jeder Wert wird also Modulo 24
	 * gerechnet und als Uhrzeit interpretiert.
	 * 
	 * @param d
	 *            Wert der konvertiert werden soll.
	 * @return Methode gibt einen String zurück.
	 * 
	 * @author Can
	 *
	 */
	private String convert(double d) {
		if (d < 0)
			d = d % 24 + 24;
		String temp;
		String temp0;
		String temp1;
		if ((10 - (int) Math.floor(d % 24)) > 0)
			temp = "0" + Integer.toString((int) Math.floor(d % 24));
		else
			temp = Integer.toString((int) Math.floor(d % 24));

		if (10 - (int) (((d % 24) * 60) % 60) > 0)
			temp0 = "0" + Integer.toString((int) (((d % 24) * 60) % 60));
		else
			temp0 = Integer.toString((int) (((d % 24) * 60) % 60));

		temp1 = temp + ":" + temp0 + " Uhr";
		return temp1;
	}

	/**
	 * 
	 * @return Hilfsmethode erzeugt einen String[] der Form: {"Linie 1: ", "Linie 2:
	 *         ",...} mit der Länge this.dimension.
	 * 
	 * @author Can
	 *
	 */
	private String[] beginningColumn() {

		String[] temp = new String[this.dimension];
		for (int i = 0; i < this.dimension; i++)
			temp[i] = "Linie " + String.valueOf(i + 1) + ": ";
		return temp;
	}

	/**
	 * Methode wird von den Unterklassen zum Festlegen des Speicherorts des
	 * Fahrplanes, sowie zum Festlegen der Dateiart aufgerufen.
	 * 
	 * @param typ
	 *            gibt den Dateityp an. z.B. ".txt" für Textdatei.
	 * @author Can
	 *
	 */
	protected void callLocation(String typ) {
		this.dialog = new FileDialog((Frame) null, "Export Timetable");
		this.dialog.setMode(FileDialog.SAVE);
		this.dialog.setVisible(true);
		this.fileNameAndPath = this.dialog.getDirectory() + "\\" + this.dialog.getFile();

		if (this.dialog.getFile() == null)
			return;
		if (!this.fileNameAndPath.contains(typ)) {
			this.fileNameAndPath = this.fileNameAndPath + typ;
		}
	}

	/**
	 * Konkrete implementierung der Methode generateFile(eigenvalue : double,vector
	 * : Vector) aus dem Interface TimeTableGenerator. Diese Methode deklariert ihre
	 * Klassenattribute mit ihren Übergabewerten, auf dessen Basis dann die Methode
	 * generateFile() (abstrakte Methode der Basisklasse, über die jede Unterklasse
	 * verfügt) dann einen entsprechenden Fahrplan im jeweiligen Fileformat
	 * erstellt.
	 * 
	 * @author Can
	 */
	@Override
	public void generateFile(double eigenvalue, Vector vector) {
		// TODO Auto-generated method stub
		this.dimension = vector.getDimension();
		this.timeTable = generateTimeTable(eigenvalue, vector);
		generateFile();

	}

	/**
	 * Abstrakte Methode über die jede Unterklasse zum Erstellen des Fahrplans
	 * verfügen muss. Diese wird von der Methode generateFile(eigenvalue :
	 * double,vector : Vector) aus der Basisklasse aufgerufen.
	 * 
	 * @author Can
	 *
	 */
	protected abstract void generateFile();

}
