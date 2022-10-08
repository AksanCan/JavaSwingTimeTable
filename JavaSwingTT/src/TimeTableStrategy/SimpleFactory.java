package TimeTableStrategy;

import MVC.FileType;
import TimeTableStrategy.DefaultGenerator;
import TimeTableStrategy.GenerateExcelTimeTable;
import TimeTableStrategy.GeneratePdfTimeTable;
import TimeTableStrategy.GenerateTimeTable;
import TimeTableStrategy.GenerateTxtTimeTable;

/**
 * 
 * Klasse, welches die Erzeugung von GenerateTimeTable-Objekten zusammenfasst
 * (einfache Fabrik-Methode).
 * 
 * @author Can
 *
 */
public class SimpleFactory {

	/**
	 * 
	 * Methode die eine GenerateTimeTable-Instanz erzeugt - Konkrete Strategy von
	 * TimeTable.
	 * 
	 * @author Can
	 * @param typ
	 *            File-Format bestimmt was für eine Instanz erzeugt wird.
	 * @return ein Objekt vom typ GenerateTimeTable (eine konkrete Strategy von
	 *         TimeTable)
	 * 
	 * 
	 */
	public static GenerateTimeTable buildTimeTableGenerator(FileType typ) {
		GenerateTimeTable generator;
		switch (typ) {
		case EXCEL:
			generator = new GenerateExcelTimeTable();
			break;
		case PDF:
			generator = new GeneratePdfTimeTable();
			break;
		case TXTFILE:
			generator = new GenerateTxtTimeTable();
			break;
		default:
			generator = new DefaultGenerator();
		}
		return generator;
	}
}
