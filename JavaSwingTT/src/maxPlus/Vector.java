package maxPlus;

import java.util.Arrays;

/**
 * Klasse zur Erzeugung von Max-Plus algebraischen Vektorinstanzen
 * 
 * @author Can
 *
 */
public class Vector {

	private int dimension;
	private MaxPlus[] myVector;
	private int index;

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public MaxPlus[] getMyVector() {
		return myVector;
	}

	public void setMyVector(MaxPlus[] myVector) {
		this.myVector = myVector;
	}

	/**
	 * Konstruktor
	 * 
	 * @author Can
	 * @param dim Dimension vom Vektor.
	 */
	public Vector(int dim) {
		this.dimension = dim;
		this.myVector = new MaxPlus[dimension];
	}

	/**
	 * Konstruktor, welches das Attribut myVektor von sich mit der i-ten Spalte der
	 * Identitiaetsmatrix der Max-Plus-Algebra initialsisiert. MyVektor ist ein
	 * Array vom Typ MaxPlus.
	 * 
	 * @author Can
	 * 
	 * @param dim Dimension vom Vektor
	 * @param i   Spaltenzahl
	 *
	 */
	public Vector(int dim, int i) {
		this.dimension = dim;
		this.myVector = column_of_identity(i, dim);
	}

	/**
	 * Vector Addition.
	 * 
	 * @author Can
	 * @param b Vector der addiert werden soll
	 * @return neues Objekt vom typ Vector
	 */
	public Vector add(Vector b) {
		Vector addVector = new Vector(this.dimension);
		for (int j = 0; j < this.dimension; j++) {
			addVector.myVector[j] = this.myVector[j].add(b.myVector[j]);
		}
		return addVector;
	}

	/**
	 * Matrix-Vector-Multiplikation.
	 * 
	 * @author Can
	 * @param b Matrix, die multipliziert werden soll
	 * @return Objekt vom Typ Vector
	 */
	public Vector mult(Matrix b) {
		if (this.dimension != b.getDimension()) {
			return null;
		}
		Vector multVector = new Vector(this.dimension);
		for (int j = 0; j < this.dimension; j++) {
			for (int i = 0; i < this.dimension; i++) {
				if (multVector.myVector[j] == null) {
					multVector.myVector[j] = this.myVector[i].mult(b.getMyMatrix()[j][i]);
					i++;
				}
				multVector.myVector[j] = multVector.myVector[j].add(this.myVector[i].mult(b.getMyMatrix()[j][i]));
			}
		}
		return multVector;
	}

	/**
	 * Skalare-Multiplikation eines Vektor mit einem Element aus Rmax.
	 * 
	 * @author Can
	 * @param b das Skalar
	 * @return Objekt vom Typ Vector
	 */
	public Vector scalar_mult(MaxPlus b) {
		Vector streched_vector = new Vector(this.dimension);
		for (int j = 0; j < this.dimension; j++) {
			streched_vector.myVector[j] = this.myVector[j].mult(b);
		}
		return streched_vector;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * Konvertiert das Attribut myVector eines Vector-Objektes (vom Typ MaxPlus[])
	 * zu Double[].
	 * 
	 * @author Can
	 * @return myVector als Double-Array
	 *
	 */
	public Double[] getDoubleValues() {
		Double[] vector = new Double[this.dimension];
		for (int j = 0; j < this.dimension; j++) {
			try {
				if (Double.valueOf(this.myVector[j].getValue()) instanceof Double)
					vector[j] = Double.valueOf(this.myVector[j].getValue());
			} catch (Exception e) {
				return null;
			}
		}
		return vector;
	}

	/**
	 * Konvertiert das i-te Element vom Attribut myVector eines Vector-Objektes (vom
	 * Typ MaxPlus[]) zu Double.
	 * 
	 * @author Can
	 * @param i Index des Eintrags
	 * @return i-tes Element von myVector als Double-Wert
	 *
	 */
	public Double getDoubleValue(int i) {
		Double dbl = new Double(0);
		try {
			if (Double.valueOf(this.myVector[i].getValue()) instanceof Double)
				dbl = Double.valueOf(this.myVector[i].getValue());
		} catch (Exception e) {
			return null;
		}
		return dbl;
	}

	/**
	 * Hilfsmethode, die von einem Konstruktoren dieser Klasse aufgerufen wird.
	 * 
	 * @author Can
	 * @param i         Spaltennummer der Identitätsmatrix
	 * @param dimension Dimension des Arrays
	 * @return i-te Spalte der Identitätsmatrix als MaxPlus-Array
	 *
	 */
	private static MaxPlus[] column_of_identity(int i, int dimension) {
		MaxPlus[] column_of_identity = new MaxPlus[dimension];
		if (i >= 1) {
			i--;
			for (int k = 0; k < dimension; k++)
				if (k != i)
					column_of_identity[k] = new MaxPlus("E");
				else {
					column_of_identity[k] = new MaxPlus(0);
				}
			return column_of_identity;
		} else
			return null;
	}

	/**
	 * Hilfsmethode, die zwei Vector-Objekte vergleicht und den kleinsten Index i
	 * zurück gibt, so dass this.myVector[i]!=b.myVector[i].
	 * 
	 * @author Can
	 * @param b Vector, der verglichen werden soll
	 * @return Index vom Typ int.
	 *
	 */
	public int compare(Vector b) {
		int temp = -1;
		int i = 0;
		boolean bool_temp = true;
		
		while (bool_temp && i < this.getDimension()) {
			if (!this.getMyVector()[i].compare2(b.getMyVector()[i])) {
				temp = i;
				bool_temp = false;
			}
			i++;
		}
		return temp;
	}

	/**
	 * hashCode-Methode
	 *
	 */
	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + dimension;
		result = prime * result + index;
		result = prime * result + Arrays.hashCode(myVector);
		return result;
	}

	/**
	 * equals-Methode
	 *
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (dimension != other.dimension)
			return false;
		if (index != other.index)
			return false;
		if (!Arrays.equals(myVector, other.myVector))
			return false;
		return true;
	}

	/**
	 * Hilfsmethode rundet Werte aus myVector von einem Vector Objekt auf n
	 * Nachkommastellen
	 * 
	 * @param n Anzahl der Nachkommastellen
	 * 
	 * @return Vector Objekt mit this.
	 * 
	 * @author Can
	 */
	public Vector roundVector(int n) {

		double m = Math.pow(10.0, n);

		for (int i = 0; i < this.dimension; i++) {
			try {
				this.myVector[i] = new MaxPlus(Math.round(this.getDoubleValue(i) * m) / m);
			} catch (Exception e) {
				continue;
			}
		}
		return this;
	}

	/**
	 * Erstellt einen Vektor der als Einträge nur -inf hat.
	 * 
	 * @param dimension Dimension vom Vektor
	 * 
	 * @return Vector Objekt, der nur -Inf als Werte hat .
	 * 
	 * @author Can
	 */
	public static Vector identity(int dimension) {
		Vector vector = new Vector(dimension);
		MaxPlus max[] = new MaxPlus[dimension];
		for (int i = 0; i < dimension; i++) {
			max[i] = new MaxPlus("E");
		}
		vector.setMyVector(max);
		return vector;
	}

}
