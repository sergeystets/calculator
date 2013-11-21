package cdp.calculator.operation;
public interface IOperation {

	double execute(Double... args);

	String getName();

	int getPriority();

	int getNumOfOperands();
}
