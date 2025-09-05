import view.InputOutput;
import view.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;

public class CalculatorItem implements Item {
    InputOutput inOut;
    static Map<String, BinaryOperator<Integer>> mapOperations;
    {
        mapOperations = new HashMap<>();
        mapOperations.put("+", (x,y ) -> x + y);
        mapOperations.put("*", (x,y ) -> x * y);
        mapOperations.put("-", (x,y ) -> x - y);
        mapOperations.put("/", (x,y ) -> {
            if(y == 0) throw new ArithmeticException("Division by zero");
            return x / y;
        });
    }

    public CalculatorItem(InputOutput inOut) {
        this.inOut = inOut;
    }

    @Override
    public String displayedName() {
        return "Integer calculator";
    }

    @Override
    public void perform() {
        Integer operand1 = inOut.inputInteger("Enter first number");
        if(operand1 == null) return;
        Integer operand2 = inOut.inputInteger("Enter second number");
        if(operand2 == null) return;
        String operator = inOut.inputString("Enter operator from ", new ArrayList<>(mapOperations.keySet()));
        inOut.outputLine(mapOperations.get(operator).apply(operand1, operand2));
    }
}
