package MVC;

import java.util.ArrayList;

import EVStrategy.EigenValueMethod;
import EVStrategy.EigenVectorMethod;
import maxPlus.Matrix;
import maxPlus.Vector;
import resources.Resources;

/**
 * Diese Abstrakte Klasse beinhaltet alle Schnittstellen der konkreten
 * Algorithmen, die zur Berechnung der Eigenwerte und Eigenvektoren verwendet
 * werden. Diese Klasse implementiert zudem auch das Interface Model und damit
 * auch Subject.
 * 
 * @author Can
 *
 */
public abstract class EVAlgorithm implements Model {

	private ArrayList<Observer> observer;
	private String name;
	private Double eigenvalue;
	private Vector[] eigenvector;
	private EigenValueMethod eigenValueAlg;
	private EigenVectorMethod eigenVectorAlg;
	private static final String MESSAGE = "Der Algorithmus konvergiert nicht. Bitte geben Sie eine andere Matrix ein";
	private static final String CAPTION = "ACHTUNG!!!!!";

	public EVAlgorithm(String name) {
		this.name = name;
		this.observer = new ArrayList<Observer>();
	}

	public void setEigenValueAlg(EigenValueMethod eigenValueAlg) {
		this.eigenValueAlg = eigenValueAlg;
	}

	public void setEigenVectorAlg(EigenVectorMethod eigenVectorAlg) {
		this.eigenVectorAlg = eigenVectorAlg;
	}

	@Override
	public void addObserver(Observer observer) {
		// TODO Auto-generated method stub
		this.observer.add(observer);
	}

	@Override
	public void deleteObserver(Observer observer) {
		// TODO Auto-generated method stub
		this.observer.remove(observer);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for (Observer observer : this.observer) {
			observer.update(this.eigenvalue, this.eigenvector);
		}
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	/**
	 * Führt die entsprechenden Algorithmen seiner konkreten Strategy aus und führt
	 * anschließend die Methode notifyObservers(...) aus.
	 * 
	 * @author Can
	 * @param matrix Matrix der zur Eigenwert bzw. Eigenvektorberechnung benötigt
	 *               wird
	 *
	 */
	@Override
	public void calculate(Matrix matrix) {
		this.eigenvalue = this.eigenValueAlg.specificAlgorithm(matrix);
		if (eigenvalue != null)
			this.eigenvector = this.eigenVectorAlg.eigenvector(matrix, this.eigenvalue);

		if (this.eigenvalue == null)
			Resources.pop_up(MESSAGE, CAPTION);

		notifyObservers();
	}

}
