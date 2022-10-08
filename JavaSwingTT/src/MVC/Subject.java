package MVC;

public interface Subject {

	/**
	 * Stellt eine neue Beziehung zwischen sich und einem Observer her.
	 * 
	 * @param observer
	 *            : Observer
	 * @author Can
	 *
	 */
	public void addObserver(Observer observer);

	/**
	 * Entfernt eine vorhandene Beziehung zwischen sich und einem Observer.
	 * 
	 * @param observer
	 *            der jeweilige Beobachter
	 * @author Can
	 *
	 */
	public void deleteObserver(Observer observer);

	/**
	 * Methode aktuallisiert den Eigenwert und die Eigenvektoren ihrer Beobachter
	 * durch die Methode update(...).
	 * 
	 * @author Can
	 *
	 */
	public void notifyObservers();

}
