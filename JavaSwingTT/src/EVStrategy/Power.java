package EVStrategy;

import java.util.Hashtable;
import java.util.Map;

import maxPlus.Matrix;
import maxPlus.MaxPlus;
import maxPlus.Vector;

public class Power implements EigenValueMethod, EigenVectorMethod {

	private boolean bool = false;
	private int n_1, n_2, dimension;
	private double r;
	private Double eigenvalue;
	private Map<Integer, Vector> vectorMap = new Hashtable<Integer, Vector>();
	private static final Double DEFAULT_EIGENVALUE = null;
	private static final int LIMIT = 100;

	/**
	 * Methode bestimmt den Eigenwert einer Matrix mit dem Power-Algorithmus. Falls
	 * der Algorithmus nicht konvergiert wird hier -1 als Defaultwert zurück
	 * gegeben.
	 * 
	 * @author Can
	 *
	 */
	@Override
	public Double specificAlgorithm(Matrix matrix) {

		this.dimension = matrix.getDimension();
		Vector x_0 = new Vector(matrix.getDimension(), 1);
		iteration(matrix, x_0, 0);
		if (this.bool)
			this.eigenvalue = this.r / (this.n_1 - this.n_2);
		this.bool = false;
		return this.eigenvalue;
	}

	/**
	 * Methode berechnet den zugehörigen Eigenvektor einer Matrix mit dem
	 * Power-Algorthmus. Falls der Eigenwert zuvor mit der selben Klasseninstanz
	 * über die Methode specificAlgorithm(...) berechnet wurde, rechnet diese
	 * Methode den Eigenvektor direkt über die entsprechende Formel aus (Vgl.
	 * Bachelor-Thesis von Frau Akin S. 58).
	 * 
	 * @author Can
	 *
	 */
	@Override
	public Vector[] eigenvector(Matrix matrix, double... eigenvalue) {
		// TODO Auto-generated method stub
		if (this.vectorMap.isEmpty())
			this.eigenvalue = specificAlgorithm(matrix);
		else if (this.eigenvalue == DEFAULT_EIGENVALUE)
			return null;

		Vector temp[] = new Vector[1];
		temp[0] = new Vector(this.dimension);
		int limit = this.n_1 - this.n_2;
		for (int i = 1; i <= limit; i++) {
			if (temp[0].getMyVector()[0] == null)
				temp[0] = this.vectorMap.get(this.n_2 - 1 + i).scalar_mult(new MaxPlus(this.eigenvalue * (limit - i)));
			temp[0] = temp[0]
					.add(this.vectorMap.get(this.n_2 - 1 + i).scalar_mult(new MaxPlus(this.eigenvalue * (limit - i))));
		}
		this.vectorMap = new Hashtable<Integer, Vector>();
		return temp;
	}

	/**
	 * Methode fuehrt rekursiv den Power-Algorithmus durch und wird von der Methode
	 * specificAlgorithm(...) aufgerufen. Abbruchbedingung ist, dass die
	 * Klassenvariable bool (vom Typ boolean) true ist, welches true gesetzt wird,
	 * wenn zwei iterierte Vektoren ein Vielfaches voneinander sind. Falls zudem die
	 * Iterationsgrenze von 4000 Schritten erreicht wird oder ein nicht zulässiger
	 * Iterationsvektor iteriert wird, wird eine Nachricht ausgegeben und die
	 * Iteration wird abgebrochen.
	 *
	 * @author Can
	 * @param matrix
	 *            Matrix mit der iteriert wird
	 * @param vector
	 *            Iterationsvektor
	 * @param counter
	 *            Zaehler, der die Iterationsschritte zählt
	 *
	 */
	private void iteration(Matrix matrix, Vector vector, int counter) {

		if (this.bool) {
			return;
		}
		if (counter == LIMIT | !islegit(vector)) {
			this.eigenvalue = DEFAULT_EIGENVALUE;
			return;
		}

		Vector x_k = vector.mult(matrix);
		x_k.setIndex(counter + 1);
		this.vectorMap.put(x_k.getIndex(), x_k);
		initialize();
		counter++;
		iteration(matrix, x_k, counter);
	}

	/**
	 * Methode sucht sequentiell, ob sich bei den bisher iterierten Vektoren zwei
	 * befinden, die ein vielfaches voneinander sind. Falls zwei gefunden werden,
	 * initialisiert diese Methode bestimmte Klassenattribute, die für die
	 * Berechnung vom Eigenvektor relevant sind und bricht die Suche ab.
	 * 
	 * @author Can
	 *
	 */
	private void initialize() {

		if (this.vectorMap.isEmpty())
			return;

		Double temp0, temp = 0.0;
		int J, counter = 0, a = this.vectorMap.size();

		for (int k = this.vectorMap.size() - 1; k > 0; k--) {
			J = this.vectorMap.get(a).compare(this.vectorMap.get(k));
			if (J < 0)
				continue;
			temp = this.vectorMap.get(a).getDoubleValue(J) - this.vectorMap.get(k).getDoubleValue(J);
			for (int j = 0; j < this.dimension; j++) {
				if (this.vectorMap.get(a).getMyVector()[j].compare1(this.vectorMap.get(k).getMyVector()[j])) {
					counter++;
					continue;
				} else if (this.vectorMap.get(a).getMyVector()[j].compare2(this.vectorMap.get(k).getMyVector()[j])) {
					break;
				}
				temp0 = this.vectorMap.get(a).getDoubleValue(j) - this.vectorMap.get(k).getDoubleValue(j);
				if (temp.equals(temp0)) {
					counter++;
				}
			}
			if (counter == this.dimension) {
				this.n_1 = this.vectorMap.get(a).getIndex();
				this.n_2 = this.vectorMap.get(k).getIndex();
				this.r = temp;
				this.bool = true;
				return;
			}
			counter = 0;
		}
	}

	/**
	 * Hilfsmethode
	 * 
	 * @author Can
	 * @param vector
	 *            Vektor der geprüft wird
	 * @return Gibt true zurück falls vector als Komponenten nur -Inf enthält.
	 *         Ansonsten false. Dieser Vector ist als Iterationsvektor beim
	 *         Power-Algorithmus nämlich nicht zulässig.
	 *
	 */
	private static boolean islegit(Vector vector) {
		int counter = 0;
		for (int i = 0; i < vector.getDimension(); i++) {
			if (vector.getMyVector()[i].isIstE())
				counter++;
		}
		if (counter == vector.getDimension())
			return false;
		else
			return true;
	}

}