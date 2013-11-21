package cdp.calculator.operation;

public class Sum implements IOperation {

	public double execute(Double... args) {
		if (args.length != 2) {
			throw new IllegalArgumentException("Sum operation takes only two arguments");
		}
		return args[0] + args[1];
	}

	public String getName() {
		return "+";
	}

	public int getPriority() {
		return 1;
	}

	public int getNumOfOperands() {
		return 2;
	}
}
