package TimeTableStrategy;

import java.io.PrintWriter;

/**
 * 
 * Unerklasse von GenerateTimeTable
 * 
 * @author Can
 *
 */
public class GenerateTxtTimeTable extends GenerateTimeTable {

	public GenerateTxtTimeTable() {
		super("Fahrplan als Text-Datei");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generateFile() {
		// TODO Auto-generated method stub
		generateTxtFile();
	}

	/**
	 * 
	 * Methode erzeugt mit Hilfe der Methoden aus ihrer Basisklasse einen Fahrplan
	 * als Textdatei. Beim Aufruf dieser Methode wird der Nutzer über
	 * super.callLocation(".txt") (Methode aus der Basisklasse) gefragt an welchem
	 * Speicherort die Datei abgelegt werden soll.
	 * 
	 * @author Can
	 *
	 */
	private void generateTxtFile() {

		try {
			super.callLocation(".txt");
			PrintWriter writer = new PrintWriter(getFileNameAndPath());
			writer.println("Zugfahrplan: ");
			writer.println("");

			for (int j = 0; j <= getLimit(); j++) {
				if (j > 0) {
					writer.println();
					writer.println(Integer.toString(j) + ".Abfahrt ");
				}
				for (int i = 0; i < getDimension(); i++) {
					writer.print(getTimeTable().get(j)[i] + " ");
				}
				writer.println("");
			}
			writer.close();
		} catch (Exception e) {
			return;
		}
	}

}
