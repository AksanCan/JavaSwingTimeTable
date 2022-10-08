package TimeTableStrategy;

import java.io.File;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 
 * Unerklasse von GenerateTimeTable
 * 
 * @author Can
 *
 */
public class GenerateExcelTimeTable extends GenerateTimeTable {

	public GenerateExcelTimeTable() {
		super("Fahrplan als Excel-Datei");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generateFile() {
		// TODO Auto-generated method stub
		generateExcelFile();
	}

	/**
	 * 
	 * Methode erzeugt mit Hilfe der Methoden aus ihrer Basisklasse einen Fahrplan
	 * als Excel-Datei. Beim Aufruf dieser Methode wird der Nutzer über
	 * super.callLocation(".xls") (Methode aus der Basisklasse) gefragt an welchem
	 * Speicherort die Datei abgelegt werden soll.
	 * 
	 * @author Can
	 */
	private void generateExcelFile() {

		try {
			super.callLocation(".xls");
			WritableWorkbook workbook = Workbook.createWorkbook(new File(getFileNameAndPath()));
			WritableSheet sheet = workbook.createSheet("Zugahrplan", 0);
			sheet.addCell(generateLabel("Abfahrtszeiten der Linien :", 0, 0));
			sheet.setRowView(0, 500);

			for (int j = 0; j <= getLimit(); j++) {
				sheet.setColumnView(j, 13);
				if (j > 0)
					sheet.addCell(generateLabel(Integer.toString(j) + ". Abfahrt", 2, j));
				for (int i = 0; i < getDimension(); i++) {
					sheet.addCell(generateLabel(getTimeTable().get(j)[i], i + 4, j));
				}
			}
			workbook.write();
			workbook.close();
		} catch (Exception e) {
			return;
		}
	}

	/**
	 * 
	 * Hilfsmethode um Zellen in einer Excel Datei zu Befüllen. Stellt eine bessere
	 * Übersicht in der Methode generateExcelFile() dar.
	 * 
	 * @author Can
	 * @param cellContent
	 *            Inhalt der Zelle
	 * @param rowIndex
	 *            Zeilennummer nach Excel
	 * @param columnIndex
	 *            Spaltennummer nach Excel
	 * @return Label-Instanz, die der Excel-Datei (Workbook-Instanz) übergeben wird.
	 */
	private static Label generateLabel(String cellContent, int rowIndex, int columnIndex) {
		Label label = new Label(columnIndex, rowIndex, cellContent);
		return label;
	}

}
