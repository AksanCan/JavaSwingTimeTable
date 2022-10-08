package MVC;

import EVStrategy.ALambda;
import EVStrategy.Karp;

public class ALambdaAlgorithm extends EVAlgorithm {
	public ALambdaAlgorithm() {
		super("A-Lambda-Algorithmus");
		setEigenValueAlg(new Karp());
		setEigenVectorAlg(new ALambda());
	}
}
