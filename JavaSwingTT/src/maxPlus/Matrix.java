package maxPlus;

import java.util.ArrayList;
import java.util.Arrays;

public class Matrix {

	private int dimension;
	private MaxPlus[][] myMatrix;

	public Matrix(int dim) {
		this.dimension = dim;
		myMatrix = new MaxPlus[dimension][dimension];
	}

	public Matrix(Matrix m) {
		this.dimension = m.dimension;
		this.myMatrix = m.myMatrix;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public void setMyMatrix(MaxPlus[][] myMatrix) {
		this.myMatrix = myMatrix;
	}

	/**
	 * Ueberprüft die Elemente einer ArrayList in ein MaxPlus Objekt umgewandelt werden können.
	 * 
	 * @author Adel
	 * @param arrayList zweidimensionale String-ArrayList
	 * @return true falls obiges der Fall ist.
	 */
	public boolean arrayListToMatrix(ArrayList<ArrayList<String>> arrayList) {
		if (arrayList.isEmpty())
			return false;
		this.dimension = arrayList.size();
		myMatrix = new MaxPlus[dimension][dimension];
		for (int i = 0; i < this.dimension; i++) {
			for (int j = 0; j < this.dimension; j++) {
				String element = arrayList.get(i).get(j);
				MaxPlus maxPlus = new MaxPlus();
				if (maxPlus.isMaxPlus(element)) {
					myMatrix[i][j] = maxPlus;
				} else {
					return false;
				}

			}
		}
		return true;
	}

	/**
	 * Addition von zwei Matrizen mit MaxPlus-Objekten
	 *
	 * @author Philip
	 * @param b Matrix die addiert wird
	 *
	 * @return addierte Matrix
	 * 
	 */
	public Matrix add(Matrix b) {

		Matrix addMatrix = new Matrix(this.dimension);
		for (int j = 0; j < this.dimension; j++) {
			for (int i = 0; i < this.dimension; i++) {
				addMatrix.myMatrix[i][j] = this.myMatrix[i][j].add(b.myMatrix[i][j]);
			}
		}
		return addMatrix;
	}

	/**
	 * Multiplikation von zwei Matrizen mit MaxPlus-Objekten
	 *
	 * @author Philip
	 * @param b Matrix die multipliziert wird
	 *
	 * @return multiplizierte Matrix
	 * 
	 */
	public Matrix mult(Matrix b) {

		Matrix multMatrix = new Matrix(this.dimension);
		multMatrix = this;
		MaxPlus var = new MaxPlus(0);
		for (int j = 0; j < this.dimension; j++) {
			for (int i = 0; i < this.dimension; i++) {
				for (int k = 0; k < this.dimension; k++) {
					multMatrix.myMatrix[j][i] = multMatrix.myMatrix[j][i]
							.add(this.myMatrix[j][k].mult(b.myMatrix[k][i]));
				}
			}
		}
		return multMatrix;
	}

	public int getDimension() {
		return dimension;
	}

	public MaxPlus[][] getMyMatrix() {
		return myMatrix;
	}

	/**
	 * gebe die Matrix als zwei dimensionale ArrayList zurück
	 * 
	 * @author Adel
	 * @return zwei dimensionale ArrayList
	 */
	public ArrayList<ArrayList<String>> MatrixToArrayList() {
		ArrayList<ArrayList<String>> arrayList = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < this.dimension; i++) {
			ArrayList<String> row = new ArrayList<String>();
			for (int j = 0; j < this.dimension; j++) {

				row.add(this.myMatrix[i][j].getValue());
			}
			arrayList.add(row);
		}
		return arrayList;
	}

	/**
	 * setze ein MaxPlus element in einer bistimmten Position in der Matrix
	 * 
	 * @author Adel
	 * @param line Zeile 
	 * @param row Spalte
	 * @param maxPlus Element aus Rmax
	 */
	public void setElement(int line, int row, MaxPlus maxPlus) {
		if (maxPlus == null)
			return;
		this.myMatrix[line][row] = maxPlus;
	}

	/**
	 * Transponierte Matrix
	 * 
	 * @author Adel
	 * @return Die transponierte Matrix
	 */
	public Matrix transpose() {
		Matrix tmp = new Matrix(this.dimension);

		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				tmp.myMatrix[i][j] = this.myMatrix[j][i];
			}

		}
		return tmp;
	}

	/**
	 * 
	 * Erstellt eine Einheitsmatrix.
	 * 
	 * @param dimension Dimension der Matrix.
	 * @return gibt eine Einheitsmatrix zurueck
	 */

	public static Matrix identityMatrix(int dimension) {
		Matrix matrix = new Matrix(dimension);
		for (int i = 0; i < dimension; i++) {
			Vector tmp = new Vector(dimension, i + 1);
			for (int j = 0; j < dimension; j++) {
				matrix.myMatrix[i][j] = tmp.getMyVector()[j];
			}
		}
		return matrix;
	}

	/**
	 * fuehrt eine Skalarmultiplikation mit this und maxPlus durch.
	 * 
	 * @param maxPlus Skalar
	 * @return skalierte Matrix.
	 */
	public Matrix scalarMult(MaxPlus maxPlus) {
		Matrix matrix = new Matrix(this.dimension);
		for (int i = 0; i < dimension; i++) {
			for (int j = 0; j < dimension; j++) {
				matrix.myMatrix[i][j] = this.myMatrix[i][j].mult(maxPlus);

			}
		}
		return matrix;
	}

	/**
	 * berechnet das k-te Potenz von der Matrix
	 * 
	 * @param k Exponent
	 * @return exponentierte Matrix
	 */
	public Matrix exponentiation(int k) {
		Matrix matrix = new Matrix(this.dimension);
		matrix = this;
		for (int i = 0; i < k; i++) {
			matrix = matrix.mult(this);

		}
		return matrix;
	}

	/**
	 * überpruft ob die eingegebene Vektor EigenVektor bzg. der Eigenvalue
	 * 
	 * @param eigenvector Eigenvektor der geprüft werden soll.
	 * @param eigenvalue Zugehöriger Eigenwert.
	 * @return true falls obiges der Fall ist, sonst false.
	 */
	public boolean isEigenVector(Vector eigenvector, MaxPlus eigenvalue) {

		if (eigenvector == null | eigenvalue == null)
			return false;

		Vector output1 = new Vector(this.dimension);
		Vector output2 = new Vector(this.dimension);
		output1 = eigenvector.mult(this);
		output2 = eigenvector.scalar_mult(eigenvalue);

		return output1.equals(output2);
	}
	/**
	 * Gibt die i-te Spalte der Matrix aus.
	 * 
	 * @param index Spaltenindex der Matrix, dessen Spalte ausgegeben werden soll.
	 * @return Spaltenvektor.
	 */
	public Vector getColumn(int index) {

		if (index <= 0 | index > this.dimension)
			return null;
		index--;
		MaxPlus[] elements = new MaxPlus[dimension];
		Vector vector = new Vector(this.dimension);
		for (int i = 0; i < this.dimension; i++) {
			elements[i] = this.myMatrix[i][index];
		}
		vector.setMyVector(elements);
		return vector;
	}

	/**
	 * Der HashCode eines Objektes muss für zwei Objekte, deren Vergleichstest
	 * (equals) true ergibt, derselbe sein.
	 *
	 * @author Philip
	 *
	 * @return hashCode
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dimension;
		result = prime * result + Arrays.deepHashCode(myMatrix);
		return result;
	}

	/**
	 * Vergleichstest für zwei Matrizen in der MaxPlus-Algebra
	 *
	 * @author Philip
	 * @param obj Vergleichsobjekt
	 *
	 * @return true, falls Matrizen gleich sind, sonst false.
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
		Matrix other = (Matrix) obj;
		if (dimension != other.dimension)
			return false;
		if (!Arrays.deepEquals(myMatrix, other.myMatrix))
			return false;
		return true;
	}

}
