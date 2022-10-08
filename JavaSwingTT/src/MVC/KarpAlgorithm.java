package MVC;

import EVStrategy.InabilityToPerform;
import EVStrategy.Karp;

public class KarpAlgorithm extends EVAlgorithm {

	public KarpAlgorithm() {
		super("Algorithmus von Karp");
		setEigenValueAlg(new Karp());
		setEigenVectorAlg(new InabilityToPerform());
	}

}
