package resources;

import javax.swing.JOptionPane;

import MVC.FileType;

public class Resources {

	/**
	 * Hier werden all Strings für die Benutzeroberfläsche gespeichert
	 */
	/**
	 * @author AA
	 *
	 */
	private static final String WindowTitle = "Max-Plus-Algebra";
	private static final String Title1 = "Berechnung der Eigenwerte von irreduziblen Matrizen";
	private static final String Title2 = "in der Max-Plus-Algebra";

	private static final String Window2Title = "Eingabe der Matrix";
	private static final String Window2Text = "Geben Sie die Matrix ein:";

	// Strings für alle Buttons
	private static final String Calculate = "Berechnen";
	private static final String Insert = "eingeben";
	private static final String Close = "beenden";
	private static final String Save = "in .txt-Datei speichern";
	private static final String MatrixInputButton = "Matrix erstellen";
	private static final String History = "Verlauf";
	private static final String Edit = "bearbeiten";
	private static final String ImportMatrix = "Import";
	private static final String CreatePlan = "Fahrplan erstellen";

	// ComboBox
	private static final String[] DiminsionInput = { "2X2", "3X3", "4X4", "5X5", "6X6", "7X7", "8X8" };
	//private String[] timeTableComboBox = { "EXCEL", "PDF", "TXTFILE"};

	// TxtFile
	private static final String FileName = "result.txt";
	private static final String BackgroundPath = "src\\resources\\Background.jpg";
	private static final String NextIconPath = "src\\resources\\nextIcon.png";
	private static final String PreviousIconPath = "src\\resources\\previousIcon.png";
	// results
	private static final String EigenvalueResult = "der Eigenwert: ";
	private static final String EigenvectorResult = "der Eigenvektor: ";

	public String getWindowTitle() {
		return WindowTitle;
	}

	public String getTitle1() {
		return Title1;
	}

	public String getTitle2() {
		return Title2;
	}

	public String getMatrixInputButton() {
		return MatrixInputButton;
	}

	public String getWindow2Title() {
		return Window2Title;
	}

	public String getWindow2Text() {
		return Window2Text;
	}

	public String[] getDiminsionComboBox() {
		return DiminsionInput;
	}

	public String[] getTimeTableComboBox() {
		return FileType.getAllFileTypes();
	}

	public String getCalculate() {
		return Calculate;
	}

	public String getInsert() {
		return Insert;
	}

	public String getClose() {
		return Close;
	}

	public String getSave() {
		return Save;
	}

	public String getFileName() {
		return FileName;
	}

	public String getHistory() {
		return History;
	}

	public String getEdit() {
		return Edit;
	}

	public String getBackgroundPath() {
		return BackgroundPath;
	}

	public String getNextIconPath() {
		return NextIconPath;
	}

	public String getPreviousIconPath() {
		return PreviousIconPath;
	}

	public String getEigenvalueResult() {
		return EigenvalueResult;
	}

	public String getEigenvectorResult() {
		return EigenvectorResult;
	}

	public String getImportMatrix() {
		return ImportMatrix;
	}

	public String getCreatePlan() {
		return CreatePlan;
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
	public static void pop_up(String message, String caption) {
		JOptionPane.showMessageDialog(null, message, caption, JOptionPane.WARNING_MESSAGE);
	}
}
