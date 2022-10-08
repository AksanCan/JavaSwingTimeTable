package MVC;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import maxPlus.Matrix;
import maxPlus.MaxPlus;
import maxPlus.Vector;
import resources.Resources;

public class GUI extends JFrame {

	// private members
	private JFrame mainFrame;
	private JPanel mainPanel, mainPanel2, buttonsPanel, matrixPanel;
	private JButton matrixInput, calculate, insert, close, save, history, edit, next, previous, importMatrix, create;
	private JComboBox dimensionInput, algorithmInput, timeTable;
	private JLabel backgroundLabel, historyBackgroundLabel;
	private JDialog inputDialog, historyDialog;
	private ArrayList<ArrayList<JTextField>> matrixElementsField = new ArrayList<ArrayList<JTextField>>();
	private ArrayList<Matrix> inputHistory = new ArrayList<Matrix>();
	private Strategy c;
	private String[] modelName;

	private Resources resources = new Resources();
	private TxtFile file = new TxtFile();

	private Matrix inputMatrix;
	private Double eigenValue;
	private Vector[] eigenVector;

	// default setting
	private String specificAlgorithmName;
	private FileType fileTyp = FileType.valueOf(resources.getTimeTableComboBox()[0]);;
	private int dimension = 2;
	private int matrixInputCounter = 0;
	private int EVCounter = 0; // EigenVectorCounter

	/**
	 * die graphische Benutzeroberfläche (Haupt-GUI)
	 * 
	 * @param c der Controller, über den die GUI die Methoden vom konkreten Model
	 *          ansteuern kann
	 * @author Adel
	 */
	public GUI(Strategy c) {
		this.c = c;
		this.modelName = c.getModelNames();

		specificAlgorithmName = modelName[0];
		mainFrame = new JFrame(resources.getWindowTitle());
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BorderLayout());
		mainFrame.setLocation(500, 0);

		mainFrame.setSize(1020, 1000);

		// mainPanel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.setBackground(new Color(100, 100, 100));

		// titel
		JLabel title1 = new JLabel(resources.getTitle1());
		title1.setFont(new Font("Courier New", Font.BOLD, 24));
		title1.setAlignmentX(CENTER_ALIGNMENT);
		title1.setAlignmentY(CENTER_ALIGNMENT);
		title1.setForeground(new Color(255, 0, 0));
		mainPanel.add(title1);

		JLabel title2 = new JLabel(resources.getTitle2());
		title2.setFont(new Font("Courier New", Font.BOLD, 24));
		title2.setAlignmentX(CENTER_ALIGNMENT);
		title2.setAlignmentY(CENTER_ALIGNMENT);
		title2.setForeground(new Color(255, 0, 0));
		mainPanel.add(title2);

		// matrix panel
		matrixPanel = new JPanel();
		matrixPanel.setLayout(null);

		ImageIcon background = new ImageIcon(resources.getBackgroundPath());
		backgroundLabel = new JLabel("", background, JLabel.CENTER);
		backgroundLabel.setBounds(0, -10, 1000, 1000);

		if (inputMatrix != null) {
			updateMatrixLabel(inputMatrix, backgroundLabel);
		}
		matrixPanel.add(backgroundLabel);
		mainPanel.add(matrixPanel);
		mainFrame.add(mainPanel);

		buttonsPanel = new JPanel();
		buttonsPanel.setBackground(Color.blue);

		// dropDown
		dimensionInput = new JComboBox(resources.getDiminsionComboBox());
		dimensionInput.addActionListener(new MatrixDInputListener());
		buttonsPanel.add(dimensionInput);

		algorithmInput = new JComboBox(modelName);
		algorithmInput.addActionListener(new AlgorithmInputListener());
		buttonsPanel.add(algorithmInput);

		timeTable = new JComboBox(resources.getTimeTableComboBox());
		timeTable.addActionListener(new FileTypListener());
		buttonsPanel.add(timeTable);

		// Buttons

		matrixInput = new JButton(resources.getMatrixInputButton());
		buttonsPanel.add(matrixInput);
		matrixInput.addActionListener(new MatrixInputListener());

		calculate = new JButton(resources.getCalculate());
		buttonsPanel.add(calculate);
		calculate.addActionListener(new CalculateIVListener());

		save = new JButton(resources.getSave());
		buttonsPanel.add(save);
		save.addActionListener(new SaveListener());

		create = new JButton(resources.getCreatePlan());
		buttonsPanel.add(create);
		create.addActionListener(new CreateListener());

		importMatrix = new JButton(resources.getImportMatrix());
		buttonsPanel.add(importMatrix);
		importMatrix.addActionListener(new ImportMatrixListener());

		history = new JButton(resources.getHistory());
		buttonsPanel.add(history);
		history.addActionListener(new HistoryListener());

		SetButtonsAbility(false);

		mainFrame.add(buttonsPanel, BorderLayout.PAGE_END);

		mainFrame.setVisible(true);

	}

	/**
	 * Der Dialog für die Eingabe der Matrix.
	 * 
	 * @author Adel
	 * @param matrix die Matrix, welche in die Felder übertragen wird
	 */
	public void MatrixInputGui(Matrix matrix) {
		// wenn der Matrix == null dann werden die Eingabefeldern leer sein, sonst
		// ausgefüllt von elementen der Matrix
		int dim;
		ArrayList<ArrayList<String>> matrixElements = new ArrayList<ArrayList<String>>();

		if (matrix == null) {
			dim = dimension;

		} else {
			dim = matrix.getDimension();
			matrixElements = matrix.MatrixToArrayList();
		}

		// Frame erstellen
		inputDialog = new JDialog();
		inputDialog.setLocation(500, 200);
		inputDialog.setSize(150 + dim * 30, 150 + dim * 30);

		// Titel hinzufügen
		JLabel title1 = new JLabel(resources.getWindow2Text());
		title1.setFont(new Font("Courier New", Font.BOLD, dim + 10));
		title1.setForeground(new Color(0, 0, 0));
		title1.setBounds(5, 5, 150 + dim * 20, 50);

		mainPanel2 = new JPanel();
		mainPanel2.setBackground(new Color(0, 255, 213));
		mainPanel2.add(title1);
		mainPanel2.setLayout(null);

		// Eingabefelder für die Matrix
		for (int i = 0; i < dim; i++) {
			ArrayList<JTextField> rowElements = new ArrayList<JTextField>();
			JTextField matrixElement;
			for (int j = 0; j < dim; j++) {
				if (matrix == null) {
					matrixElement = new JTextField();
				} else {
					matrixElement = new JTextField(matrixElements.get(i).get(j));
				}

				matrixElement.setBounds(50 + (j * 30), 50 + (i * 30), 20, 20);
				rowElements.add(matrixElement);
				mainPanel2.add(matrixElement);
			}

			matrixElementsField.add(rowElements);

			// inputFrame.add(matrixRow, BorderLayout.CENTER);
		}
		inputDialog.add(mainPanel2);

		// Buttons Panel
		buttonsPanel = new JPanel();
		buttonsPanel.setBackground(Color.blue);

		insert = new JButton(resources.getInsert());
		buttonsPanel.add(insert);
		insert.addActionListener(new InsertListener());

		close = new JButton(resources.getClose());
		buttonsPanel.add(close);
		close.addActionListener(new CloseListener());

		inputDialog.add(buttonsPanel, BorderLayout.PAGE_END);

		inputDialog.setVisible(true);

	}

	/**
	 * Verlauf-Dialog, in welchem alle seit Start der Anwendung eingegeben Matrizen
	 * angezeigt werden
	 * 
	 * @author Adel
	 * @param matrix die Matrix, die angezeigt wird
	 */
	private void historyGui(Matrix matrix) {

		if (matrix == null)
			return;
		// Frame erstellen
		historyDialog = new JDialog();
		historyDialog.setLocation(500, 200);
		historyDialog.setSize(1000, 700);

		JPanel panel = new JPanel();

		ImageIcon background = new ImageIcon(resources.getBackgroundPath());
		historyBackgroundLabel = new JLabel("", background, JLabel.CENTER);
		historyBackgroundLabel.setBounds(0, -10, 1000, 1000);

		updateMatrixLabel(matrix, historyBackgroundLabel);
		panel.add(historyBackgroundLabel);
		historyDialog.add(panel);

		// Buttons Panel
		buttonsPanel = new JPanel();
		buttonsPanel.setBackground(Color.blue);

		ImageIcon previousIcon = new ImageIcon(resources.getPreviousIconPath());
		previous = new JButton();
		previous.setIcon(previousIcon);
		buttonsPanel.add(previous);
		previous.addActionListener(new PreviousListener());

		insert = new JButton(resources.getInsert());
		buttonsPanel.add(insert);
		insert.addActionListener(new InsertFromHistoryListener());

		edit = new JButton(resources.getEdit());
		buttonsPanel.add(edit);
		edit.addActionListener(new EditListener());

		close = new JButton(resources.getClose());
		buttonsPanel.add(close);
		close.addActionListener(new CloseListener());

		ImageIcon nextIcon = new ImageIcon(resources.getNextIconPath());
		next = new JButton();
		next.setIcon(nextIcon);
		buttonsPanel.add(next);
		next.addActionListener(new NextListener());
		// next.setEnabled(false);

		historyDialog.add(buttonsPanel, BorderLayout.PAGE_END);

		historyDialog.setVisible(true);

	}

	/**
	 * updatet die eingegebene Matrix in der einegegenen Label
	 * 
	 * @author Adel
	 * @param matrix die angezeigte Matrix in label
	 * @param label  der Ort, wo die Matrix angezeigt wird
	 */
	private void updateMatrixLabel(Matrix matrix, JLabel label) {
		if (matrix == null)
			return;

		label.removeAll();
		label.repaint();
		ArrayList<ArrayList<String>> matrixElements = new ArrayList<ArrayList<String>>();
		int dim = matrix.getDimension();

		int locationX = 280 - (20 * dim);
		int biggstSize = 0;

		// Matrix title
		JLabel matrixTitle = new JLabel("Matrix");
		matrixTitle.setFont(new Font(null, Font.LAYOUT_LEFT_TO_RIGHT, 30 - dim));
		matrixTitle.setForeground(new Color(0, 0, 0));
		matrixTitle.setBounds(200 + (10 * dim), 25, 100, 100);
		label.add(matrixTitle);

		// Klammer der Matrix
		Font klammerFont = new Font(null, Font.LAYOUT_LEFT_TO_RIGHT, (dim * 55));
		JLabel bracket1 = new JLabel("(");

		bracket1.setFont(klammerFont);
		bracket1.setForeground(new Color(0, 0, 0));
		bracket1.setBounds(260 - (35 * dim), 90 - (10 * dim), (dim * 20), 25 + (dim * 60));
		label.add(bracket1);

		// generating the matrix elements

		Matrix m = new Matrix(dim);
		m = matrix.transpose();
		matrixElements = m.MatrixToArrayList();

		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {

				JLabel element = new JLabel(matrixElements.get(i).get(j));

				element.setFont(new Font(null, Font.ROMAN_BASELINE, 50 - 3 * dim));
				element.setForeground(new Color(0, 0, 0));
				element.setSize(element.getPreferredSize());
				if (element.getSize().width > biggstSize) {
					biggstSize = element.getSize().width;
				}
				element.setLocation(locationX, 100 + (50 * j));
				label.add(element);
			}
			locationX = locationX + biggstSize + 20;
		}

		JLabel bracket2 = new JLabel(")");
		bracket2.setFont(klammerFont);
		bracket2.setForeground(new Color(0, 0, 0));
		bracket2.setBounds(locationX, 90 - (10 * dim), (dim * 20), 25 + (dim * 60));
		label.add(bracket2);

		label.repaint();
	}

	/**
	 * Updatet das Anzeigen von Eigenwert und Eigenvektor im MainWindow
	 * 
	 * 
	 * @author Adel
	 */
	private void updateResult() {
		matrixPanel.removeAll();
		mainFrame.repaint();
		JLabel result;
		if (eigenValue != null) { // eigenValue >= 0

			// das Anzeigen von dem Eigenwert
			String eV = Double.toString(eigenValue);
			eV = resources.getEigenvalueResult() + eV;
			result = new JLabel(eV);

			result.setFont(new Font(null, Font.ROMAN_BASELINE, 50 - 3 * dimension));
			result.setForeground(new Color(0, 0, 0));
			result.setLocation(50, 750);
			result.setSize(result.getPreferredSize());
			backgroundLabel.add(result);
		}

		// das Anzeigen von dem Eigenvektor
		Vector defaultEV = new Vector(2);
		if (eigenVector == null) {
			matrixPanel.add(backgroundLabel);

			mainPanel.add(matrixPanel);
			mainFrame.add(mainPanel);
			mainFrame.repaint();
			return;
		}
		result = new JLabel(resources.getEigenvectorResult());
		result.setFont(new Font(null, Font.ROMAN_BASELINE, 50 - 3 * dimension));
		result.setLocation(470, 750);
		result.setSize(result.getPreferredSize());
		backgroundLabel.add(result);
		if (eigenVector.length > 1) {

			next = new JButton();
			next.setIcon(new ImageIcon(resources.getNextIconPath()));
			next.setLocation(670, 800);
			next.setSize(next.getPreferredSize());
			next.addActionListener(new NextEVListener());
			backgroundLabel.add(next);

			previous = new JButton();
			previous.setIcon(new ImageIcon(resources.getPreviousIconPath()));
			previous.setLocation(600, 800);
			previous.setSize(next.getPreferredSize());
			previous.addActionListener(new PrevEVListener());
			backgroundLabel.add(previous);

		} else {
			EVCounter = 0;
		}

		for (int i = 0; i < eigenVector[EVCounter].getDimension(); i++) {

			result = new JLabel(
					eigenVector[EVCounter].getMyVector()[eigenVector[EVCounter].getDimension() - 1 - i].getValue());
			result.setFont(new Font(null, Font.ROMAN_BASELINE, 50 - 3 * dimension));
			result.setLocation(850, 750 - (i * 50));
			result.setSize(result.getPreferredSize());
			backgroundLabel.add(result);
			backgroundLabel.add(result);
		}

		matrixPanel.add(backgroundLabel);
		mainPanel.add(matrixPanel);
		mainFrame.add(mainPanel);
		mainFrame.repaint();
	}

	/**
	 * Diese Methode wird von der View aufgerufen sobald diese von ihrem Subjekt
	 * benachrichtigt wird. Setzt die gerechneten Ergebnisse ein.
	 * 
	 * @author Can
	 * @param eigenvalue  der berechnete Eigenwert von inputMatrix
	 * @param eigenvector der berechnete Eigenvektor von inputMatrix
	 */
	public void setEigenvalues(Double eigenvalue, Vector[] eigenvector) {

		if (eigenvector != null) {
			for (Vector v : eigenvector)
				v.roundVector(3);
		}
		if (eigenvalue != null)
			this.eigenValue = Math.round(eigenvalue * 1000.0) / 1000.0;
		this.eigenVector = eigenvector;
	}

	/**
	 * stellt die Nutzbarkeit von den verschiedenen Tasten ein
	 * 
	 * @author Adel
	 * @param flage fuer true werden die Tasten aktiviert, ansonsten deaktiviert
	 */
	private void SetButtonsAbility(Boolean flage) {
		history.setEnabled(flage);
		calculate.setEnabled(flage);
		save.setEnabled(flage);
		create.setEnabled(flage);
	}

	/**
	 * Das Ereignis fuer die Taste "Matrix erstellen" oeffnet das Fensterchen zur
	 * Matrixeingabe
	 * 
	 * @author Adel
	 *
	 */
	class MatrixInputListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			matrixElementsField.clear();
			MatrixInputGui(null);
		}
	}

	/**
	 * 
	 * oeffnet das Dropdown-Menue zur Auswahl der Matrixgroesse
	 * 
	 * @author Adel
	 *
	 */
	class MatrixDInputListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dimension = dimensionInput.getSelectedIndex() + 2;
		}
	}

	/**
	 * Das Ereignis für die Taste "Berechnen" fuehr die Methode calculate(Matrix
	 * matrix, Swing swingname) von der Strategy der GUI aus (Controller)
	 * 
	 * @author Adel
	 *
	 */
	class CalculateIVListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			c.calculate(inputMatrix, specificAlgorithmName);

			updateMatrixLabel(inputMatrix, backgroundLabel);
			updateResult();
		}
	}

	/**
	 * Das Ereignis für die Taste "Eingeben" erstellt eine Matrix aus den
	 * eingegebenen Werten und zeigt sie in der Haupt-Benutzeroberflaeche an
	 * 
	 * @author Adel
	 *
	 */
	class InsertListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			boolean validInput = true;
			inputMatrix = new Matrix(dimension);

			for (int i = 0; i < dimension; i++) {

				for (int j = 0; j < dimension; j++) {

					MaxPlus maxPlus = new MaxPlus();
					if (maxPlus.isMaxPlus(matrixElementsField.get(i).get(j).getText())) {

						inputMatrix.getMyMatrix()[i][j] = maxPlus;
					} else {
						matrixElementsField.get(i).get(j).setBackground(new Color(255, 82, 50));
						validInput = false;
					}
				}
			}

			if (validInput) {
				updateMatrixLabel(inputMatrix, backgroundLabel);
				SetButtonsAbility(true);
				inputDialog.dispose();
				inputHistory.add(inputMatrix);

			} else {
				inputMatrix = null;
			}

		}
	}

	/**
	 * Das Ereignis für die Taste "Eingeben" uebertraegt die angezeigte Matrix vom
	 * Verlauf in die Haupt-Benutzeroberflaeche
	 * 
	 * @author Adel
	 *
	 */
	class InsertFromHistoryListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			inputMatrix = inputHistory.get(matrixInputCounter);
			updateMatrixLabel(inputMatrix, backgroundLabel);
			historyDialog.dispose();

		}
	}

	/**
	 * Das Ereignis für die Taste "beenden" schliesst das Window
	 * 
	 * @author Adel
	 *
	 */
	class CloseListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			matrixElementsField.clear();
			if (inputDialog != null)
				inputDialog.dispose();
			if (historyDialog != null)
				historyDialog.dispose();
		}
	}

	/**
	 * 
	 * oeffnet das Dropdown-Menue zum Waehlen des Algorithmus
	 * 
	 * @author Adel
	 *
	 */
	class AlgorithmInputListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			specificAlgorithmName = modelName[algorithmInput.getSelectedIndex()];
		}
	}

	/**
	 * speichert die eingegebene Matrix und ihre eigenValues in TxtDatei
	 * 
	 * @author Adel
	 *
	 */
	class SaveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			try {

				file.writeToFile(inputMatrix);

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	/**
	 * Speichert den Fahrplan in .xls Datei
	 * 
	 * @author Adel
	 *
	 */
	class CreateListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			c.generateFile(fileTyp);
		}
	}

	/**
	 * oeffnet den Dialog "Verlauf"
	 * 
	 * @author Adel
	 *
	 */
	class HistoryListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			matrixInputCounter = inputHistory.size() - 1;
			historyGui(inputHistory.get(matrixInputCounter));

		}
	}

	/**
	 * Das Ereignis für die Taste "next" zeigt die naechste Matrix im Dialog
	 * "Verlauf"
	 * 
	 * @author Adel
	 *
	 */
	class NextListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (matrixInputCounter + 1 < inputHistory.size()) {
				matrixInputCounter = matrixInputCounter + 1;
				updateMatrixLabel(inputHistory.get(matrixInputCounter), historyBackgroundLabel);
			}
		}
	}

	/**
	 * Das Ereignis für die Taste "previous" zeigt die vorherige Matrix im Dialog
	 * "Verlauf"
	 * 
	 * @author Adel
	 *
	 */
	class PreviousListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (matrixInputCounter - 1 >= 0) {
				matrixInputCounter = matrixInputCounter - 1;
				updateMatrixLabel(inputHistory.get(matrixInputCounter), historyBackgroundLabel);
			}

		}
	}

	/**
	 * Das Ereignis fuer die Taste "bearbeiten" uebertraegt die Werte der
	 * angezeigten Matrix in MatrixInput-Dialog
	 * 
	 * @author Adel
	 *
	 */
	class EditListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			matrixElementsField.clear();
			MatrixInputGui(inputHistory.get(matrixInputCounter));
			dimension = inputHistory.get(matrixInputCounter).getDimension();
			historyDialog.dispose();

		}
	}

	/**
	 * Das Ereignis fuer die Taste "import" oeffnet einen Windows-Dialog, in dem man
	 * eine txt-Datei zum Import waehlen kann
	 * 
	 * @author Adel
	 *
	 */
	class ImportMatrixListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {

				if (file.readFile() != null) {
					inputMatrix = file.readFile();
					updateMatrixLabel(inputMatrix, backgroundLabel);
					inputHistory.add(inputMatrix);
					SetButtonsAbility(true);
				}

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

	/**
	 * Das Ereignis fuer das Dropdown-Menue "FileTyp"
	 * 
	 * 
	 * @author Adel
	 *
	 */
	class FileTypListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			fileTyp = FileType.valueOf(resources.getTimeTableComboBox()[timeTable.getSelectedIndex()]);
		}
	}

	/**
	 * Das Ereignis fuer die Taste "NextEigenVector" zeigt den naechsten Eigenvektor
	 * in der Haupt-Benutzeroberflaeche an
	 * 
	 * @author Adel
	 *
	 */
	class NextEVListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (EVCounter + 1 < eigenVector.length) {
				EVCounter = EVCounter + 1;
				updateMatrixLabel(inputMatrix, backgroundLabel);
				updateResult();
			}

		}
	}

	/**
	 * Das Ereignis fuer die Taste "PreviousEigenVector" zeigt den vorherigen
	 * Eigenvektor in der Haupt-Benutzeroberflaeche
	 * 
	 * 
	 * @author Adel
	 *
	 */
	class PrevEVListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (EVCounter - 1 >= 0) {
				EVCounter = EVCounter - 1;
				updateMatrixLabel(inputMatrix, backgroundLabel);
				updateResult();
			}
		}
	}
	// smile
}
