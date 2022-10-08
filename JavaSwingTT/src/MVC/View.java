package MVC;

import maxPlus.Vector;

/**
 * Die Klasse View implementiert die Klasse Observer und hat eine GUI, die es
 * mit den aktuallisierte Eigenwerten und Eigenvektoren beliefert, sobald durch
 * die GUI ein Algorithmus durchgeführt wurde.
 * 
 * @author Can
 *
 */
public class View implements Observer {

	private GUI gui;

	/**
	 * Beim Aufruf des Konstruktors wird eine GUI-Instanz erzeugt, der als Parameter
	 * eine Strategy übergeben wird. Die View registriert sich zudem bei den
	 * Klassen, die es beobachtet.
	 * 
	 * @author Can
	 * @param strategy
	 *            Strategy, die der GUI übergeben wird
	 * @param subject
	 *            Instanzen die beobachtet werden sollen
	 */
	public View(Strategy strategy, Subject... subject) {
		for (Subject s : subject)
			s.addObserver(this);
		this.gui = new GUI(strategy);
	}

	@Override
	public void update(Double eigenvalue, Vector... eigenvector) {
		this.gui.setEigenvalues(eigenvalue, eigenvector);
	}

}
