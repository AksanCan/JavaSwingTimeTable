package maxPlus;

public class MaxPlus {

	/**
	 * Hier wird die MaxPlus-Algebra definiert
	 * 
	 * @author Jäger
	 */

	private double value;
	private boolean istE = false;

	/**
	 * Konstruktoren
	 * 
	 * @author Jäger
	 * @param a MaxPlus-Element
	 */
	public MaxPlus(MaxPlus a) {
		this.value = a.value;
		this.istE = a.istE;
	}

	public MaxPlus() {

	}

	public MaxPlus(double value) {
		this.value = value;
	}

	public MaxPlus(String s) {

		if (s.equals("E") || s.equals("e"))
			istE = true;

	}

	/**
	 * converting String to MaxPlus
	 * 
	 * @author adel
	 * @param s String, welcher auf Konvertierbarkeit zu MaxPlus ueberprueft wird
	 * @return ture if s valid
	 */
	public boolean isMaxPlus(String s) {

		try {
			double v = Double.parseDouble(s);
			this.value = v;
		} catch (NumberFormatException e) {
			if (s.equals("E") || s.equals("e")) {
				this.istE = true;
				// return true;
			} else {
				return false;
			}
			return true;
		}
		return true;
	}

	/**
	 * MaxPlus-Addition mit 3 Fallunterscheidungen zur Behandlung des neutralen
	 * Elements
	 * 
	 * @author Jäger
	 * @param b MaxPlus-Element, welches addiert zu this maxplus-addiert wird
	 * @return Ergebnis der maxplus-Addition
	 */
	public MaxPlus add(MaxPlus b) {

		// wenn beide Werte eine Zahl sind:
		if (this.istE == false & b.istE == false) {
			if (this.value >= b.value) { // der größere Wert wird ausgegeben
				return new MaxPlus(this);
			} else {
				return new MaxPlus(b);
			}
		}
		// wenn exakt einer der beiden Werte E ist:
		else if (this.istE == true ^ b.istE == true) {
			if (this.istE == true) // der Wert, der nicht E ist wird ausgegeben
				return new MaxPlus(b); // also der Zahlenwert
			else
				return new MaxPlus(this);
		}
		// wenn beide E sind:
		else {
			return new MaxPlus(this);
		}
	}

	/**
	 * MaxPlus-Multiplikation
	 * 
	 * @author Jäger
	 * @param b MaxPlus-Element, welches mit this maxplus-multipliziert wird
	 * @return Ergebnis der maxplus-Multiplikation
	 */
	public MaxPlus mult(MaxPlus b) {

		// wenn beide Werte eine Zahl sind:
		if (this.istE == false & b.istE == false) {
			return new MaxPlus(this.value + b.value);
		}

		// wenn exakt einer der beiden Werte E ist:
		else if (this.istE == true ^ b.istE == true) {
			if (this.istE == true) // der Wert, der E ist wird ausgegeben
				return new MaxPlus(this);
			else
				return new MaxPlus(b);
		}

		// wenn beide E sind:
		else {
			return new MaxPlus(this);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (istE ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaxPlus other = (MaxPlus) obj;
		if (istE != other.istE)
			return false;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}

	public String getValue() {
		if (this.istE == true) {
			return "e";
		} else {
			return Double.toString(value);
		}
	}

	public void setValue(String value) {
		this.value = Double.parseDouble(value);
	}

	public void setIstE(boolean istE) {
		this.istE = istE;
	}

	public boolean isIstE() {
		return istE;
	}

	/**
	 * prüft ob zwei MaxPlus Objekte gleichzeitig als Value den Wert E haben.
	 * 
	 * @author Can
	 * @param b MaxPlus-Element, das mit einem anderen MaxPlus-Element verglichen
	 *          wird (ob beide E sind)
	 * @return true, wenn beide E sind, false sonst
	 */
	public boolean compare1(MaxPlus b) {
		if (this.isIstE() && b.isIstE())
			return true;
		else
			return false;
	}

	/**
	 * prüft ob zwischen zwei MaxPlus Objekten mindestens einer als Value den Wert E
	 * hat.
	 * 
	 * @author Can
	 * @param b MaxPlus-Element, das mit einem anderen MaxPlus-Element verglichen
	 *          wird (ob einer von den beiden E ist)
	 * @return true, wenn einer E ist, false sonst
	 */
	public boolean compare2(MaxPlus b) {
		if (this.isIstE() | b.isIstE())
			return true;
		else
			return false;
	}

	/***
	 * wechselt das Vorzeichnen von dem Value des MaxPlusElements
	 * 
	 * @return gibt das negierte MaxPlus-Element zurueck
	 */
	public MaxPlus calculateNegativ() {
		MaxPlus maxPlus = new MaxPlus();
		if (this.istE)
			return null;

		maxPlus.value = -1 * this.value;

		return maxPlus;
	}

}
