package TimeTableStrategy;

import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 
 * Unerklasse von GenerateTimeTable
 * 
 * @author Can
 *
 */
public class GeneratePdfTimeTable extends GenerateTimeTable {

	public GeneratePdfTimeTable() {
		super("Fahrplan als Pdf-Datei");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void generateFile() {
		generatePdfFile();
	}

	/**
	 * 
	 * Methode erzeugt mit Hilfe der Methoden aus ihrer Basisklasse einen Fahrplan
	 * als Pdf-Datei. Beim Aufruf dieser Methode wird der Nutzer über
	 * super.callLocation(".pdf") (Methode aus der Basisklasse) gefragt an welchem
	 * Speicherort die Datei abgelegt werden soll.
	 * 
	 * @author Can
	 */
	private void generatePdfFile() {

		try {
			super.callLocation(".pdf");
			Document document = new Document();
			PdfWriter.getInstance(document, new FileOutputStream(getFileNameAndPath()));
			document.open();
			Paragraph paragraph = new Paragraph("Abfahrtszeiten der Linien :");
			document.add(paragraph);
			lineSkip(document, 1);
			PdfPTable table = new PdfPTable(getDimension() + 1);
			table.addCell("");

			for (int j = 0; j <= getLimit(); j++) {
				if (j > 0)
					table.addCell(Integer.toString(j) + ".Abfahrt ");
				for (int i = 0; i < getDimension(); i++) {
					if (j == 0) {
						table.addCell(new PdfPCell(new Phrase(getTimeTable().get(j)[i])));
					} else
						table.addCell(getTimeTable().get(j)[i]);
				}
			}
			document.add(table);
			document.close();

		} catch (Exception e) {
			return;
		}
	}

	/**
	 * 
	 * Statische Hilfsmethode um Absätze in einer Document-Instanz zu erzeugen.
	 * 
	 * @author Can
	 * 
	 * @param document
	 *            jeweilige Pdf Datei als Document-Objekt
	 * @param skips
	 *            Anzahl der Absätze
	 * 
	 */
	private static void lineSkip(Document document, int skips) {
		try {
			for (int i = 0; i < skips; i++)
				document.add(new Paragraph(" "));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}

}
