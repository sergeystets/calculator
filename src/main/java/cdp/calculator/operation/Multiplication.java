package cdp.calculator.operation;

public class Multiplication implements IOperation {

	public double execute(Double... args) {
		if (args.length != 2) {
			throw new IllegalArgumentException("Multiplication operation takes only two arguments");
		}
		return args[0] * args[1];
	}

	public String getName() {
		return "*";
	}

	public int getPriority() {
		return 2;
	}

	public int getNumOfOperands() {
		return 2;
	}
}
