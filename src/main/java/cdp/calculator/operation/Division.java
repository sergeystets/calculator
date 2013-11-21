package cdp.calculator.operation;

public class Division implements IOperation {

	public double execute(Double... args) {
		if (args.length != 2) {
			throw new IllegalArgumentException("Division operation takes only two arguments");
		}
		if (args[1] == 0.0) {
			throw new IllegalArgumentException("Second argument can not be 0.0");
		}
		return args[0] / args[1];
	}

	public String getName() {
		return "/";
	}

	public int getPriority() {
		return 2;
	}

	public int getNumOfOperands() {
		return 2;
	}
}
