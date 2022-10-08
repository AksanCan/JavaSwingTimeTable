package MVC;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import maxPlus.Matrix;
import maxPlus.MaxPlus;

public class TxtFile {

	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	/**
	 * schreibt die die engegebene Matrix in .txt Datei in dem eingegebenen Pfad
	 * 
	 * @author Adel
	 * @param matrix Die Matrix, die als Txt-Datei ausgeschrieben wird.
	 * @param fileNameAndPath Der Pfad, wo die Datei gespeichert wird.
	 * @throws FileNotFoundException wirft eine Exception, falls Eingabe ungueltig war.
	 */
	public void writeToFile(Matrix matrix, String fileNameAndPath) throws FileNotFoundException {
		
		ArrayList<ArrayList<String>> matrixElements = new ArrayList<ArrayList<String>>();
		matrixElements = matrix.MatrixToArrayList();
		
		PrintWriter output = new PrintWriter(fileNameAndPath);

		// Writing the information to the file
		int dimension = matrix.getDimension();
		output.println("Dimension: " + dimension);
		output.println("Die eingegebene Matrix: ");

		// Matrix elemente
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				output.print(matrixElements.get(i).get(j) + "  ");
			}
			output.println(" ");
		}

		Calendar cal = Calendar.getInstance();
		output.println("Erstellungszeit:");
		output.println(sdf.format(cal.getTime()));
		output.close();
	}

	/**
	 * öffnet eine Dilaog und schreibt die die engegebene Matrix in .txt Datei in
	 * dem gewälten Pfad
	 * 
	 * @param matrix Die Matrix, die als Txt-Datei ausgeschrieben wird
	 * @throws FileNotFoundException wirft eine Exception, falls Eingabe ungueltig war.
	 */
	public void writeToFile(Matrix matrix) throws FileNotFoundException {

		// dialog öfnnen, Datei pfad und name festlegen
		FileDialog dialog = new FileDialog((Frame) null, "save File");
		dialog.setMode(FileDialog.SAVE);
		dialog.setVisible(true);
		String fileNameAndPath = dialog.getDirectory() + "\\" + dialog.getFile();

		

		if (dialog.getFile() == null)
			return;
		if (!fileNameAndPath.contains(".txt")) {
			fileNameAndPath = fileNameAndPath + ".txt";
		}
		
		writeToFile(matrix, fileNameAndPath);

	}

	/**
	 * öffnet ein Dialog um .Txt Datei zu importieren, liest Matrix aus .txt Datei
	 * 
	 * @author Adel
	 * @return Matrix aus MaxPlus
	 * @throws IOException wirft eine IO-Exception.
	 */
	public Matrix readFile() throws IOException {
		FileDialog dialog = new FileDialog((Frame) null, "save File");
		dialog.setMode(FileDialog.LOAD);
		dialog.setVisible(true);

		String fileNameAndPath = dialog.getDirectory() + "\\" + dialog.getFile();
		if (dialog.getFile() == null)
			return null;

		return readFile(fileNameAndPath);

	}

	/**
	 * liest Matrix aus .txt Datei von dem eingegebenen Pfad
	 * 
	 * @author Adel
	 * @param path der Pfad in der die Datei gespeichert wird.
	 * @return die eingelesene Matrix 
	 * @throws IOException wirft eine IOException.
	 */
	public Matrix readFile(String path) throws IOException {

		File inputFile = new File(path);
		try {
			int matrixDimension = 0;
			Matrix matrix = null;
			int counter = 0;

			BufferedReader br = new BufferedReader(new FileReader(inputFile));

			String st;
			while ((st = br.readLine()) != null) {
				// reading the dimension of the matrix
				if (st.contains("Dimension")) {
					char[] line = st.toCharArray();
					String dimension = "";
					for (int i = 11; i < line.length; i++) {
						dimension = dimension + line[i];
					}
					matrixDimension = Integer.parseInt(dimension);
					matrix = new Matrix(matrixDimension);
				}

				// reading Matrix lines
				if (counter >= 2 && counter < matrixDimension + 2) {
					int rowIndex = 0;
					char[] line = st.toCharArray();
					String maxPlus = "";
					for (int i = 0; i < line.length; i++) {
						if (line[i] != ' ') {
							maxPlus = maxPlus + line[i];

						} else {
							MaxPlus mp = new MaxPlus();
							if (mp.isMaxPlus(maxPlus)) {
								matrix.setElement(counter - 2, rowIndex, mp);
								rowIndex++;
							} else if (maxPlus != "") {
								return null;
							}
							maxPlus = "";
						}
					}
				}
				counter++;
			}
			return matrix;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
