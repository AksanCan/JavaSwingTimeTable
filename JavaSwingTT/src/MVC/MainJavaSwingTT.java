package MVC;

/**
 * In der Klasse Main werden drei unterschiedliche Instanzen vom Typ Model,
 * sowie eine Controller Instanz erzeugt, die anschließend als Parameter diese
 * drei Model-Objekte übergeben bekommt.
 *
 * @author Can
 *
 */
public class MainJavaSwingTT {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		Model m1 = new PowerAlgorithm();
		Model m2 = new KarpAlgorithm();
		Model m3 = new ALambdaAlgorithm();

		Controller c = new Controller(m1,m2,m3);
	}
}
