package MVC;

/**
 * Enumeration FileType wird i.W. von der Klasse TimeTable verwendet, sowie der
 * Klasse GUI um die Algorithmen zum Erstellen eines Fahrplans auszutauschen.
 * 
 * @author Can
 *
 */
public enum FileType {

	EXCEL("EXCEL"), PDF("PDF"), TXTFILE("TEXTFILE");

	private String filename;

	private FileType(String filename) {
		this.filename = filename;
	}

	/**
	 * Static-Methode, die alle Enums als String-Array zurück gibt.
	 *
	 * @author Can
	 * @return String[], der alle Enums als String enthält.
	 *
	 */
	public static String[] getAllFileTypes() {
		String[] temp = new String[FileType.values().length];
		for (int i = 0; i < temp.length; i++)
			temp[i] = FileType.values()[i].toString();
		return temp;
	}

	@Override
	public String toString() {
		return this.filename;
	}

}
