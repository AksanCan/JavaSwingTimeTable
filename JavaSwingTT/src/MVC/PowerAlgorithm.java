package MVC;

import EVStrategy.Power;

/**
 * Unterklasse von EVAlgorithm.
 * 
 * @author Can
 *
 */
public class PowerAlgorithm extends EVAlgorithm {

	public PowerAlgorithm() {
		super("Power-Algorithmus");
		Power pow = new Power();
		setEigenValueAlg(pow);
		setEigenVectorAlg(pow);
	}

}
