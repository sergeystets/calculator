package cdp.calculator.operation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

public class OperationProvider {

    private final Map<String, IOperation> operations = new HashMap<String, IOperation>();

    public OperationProvider() {
        init();
    }

    private void init() {
        IOperation division = new Division();
        IOperation sum = new Sum();
        IOperation sub = new Subtraction();
        IOperation multiplication = new Multiplication();

        operations.put(division.getName(), division);
        operations.put(sum.getName(), sum);
        operations.put(sub.getName(), sub);
        operations.put(multiplication.getName(), multiplication);
    }


    public boolean isOperation(String operationName) {
        Assert.hasText(operationName, "operationName can not be null or empty");
        return operations.containsKey(operationName);
    }

    public IOperation getOperation(String operationName) {
        Assert.hasText(operationName, "operationName can not be null or empty");
        return operations.get(operationName);
    }

    public String getOperationNames() {
        StringBuilder result = new StringBuilder();
        for (String operationName : operations.keySet()) {
            result.append(operationName);
        }
        return result.toString();
    }
}
