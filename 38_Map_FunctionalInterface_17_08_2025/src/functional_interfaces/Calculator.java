package functional_interfaces;

@FunctionalInterface
public interface Calculator {
    int calculate(int a , int b);
//    float cal(float a, float b);

    static Calculator add(){
        return (a, b) -> a + b;
    }

    default Calculator thenAdd(int k){
        return (a,b) -> calculate(a, b) + k;
    }
}
