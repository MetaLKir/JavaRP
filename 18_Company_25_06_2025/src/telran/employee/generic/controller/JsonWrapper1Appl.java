package telran.employee.generic.controller;

import telran.employee.generic.model.JsonWrapper1;

public class JsonWrapper1Appl {
    public static void main(String[] args) {
        JsonWrapper1 wrapper1 = new JsonWrapper1(42);
        System.out.println(wrapper1);

        if (wrapper1.getValue() instanceof String){
            String str = (String) wrapper1.getValue();
            System.out.println(str + 5);
        }
        if (wrapper1.getValue() instanceof Integer){
            Integer num = (Integer) wrapper1.getValue();
            System.out.println(num + 5);
        }

    }
}
