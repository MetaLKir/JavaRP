package telran.homework;

public class PrimitivesMinMaxAppl {

    private static final String[] PRIMITIVES = {"byte", "short", "int", "long", "float", "double", "boolean", "char"};

    public static void main(String[] args) {
        if (args.length == 0) {
            for (String primitive : PRIMITIVES)
                printPrimitiveMinMaxValues(primitive);
        }
        else {
            for (String arg : args)
                printPrimitiveMinMaxValues(arg);
        }
    }

    public static void printPrimitiveMinMaxValues(String primitive) {
        String maxValue;
        String minValue;
        switch (primitive.toLowerCase()) {
            case "byte":
                minValue = Byte.toString(Byte.MIN_VALUE);
                maxValue = Byte.toString(Byte.MAX_VALUE);
                break;
            case "short":
                minValue = Short.toString(Short.MIN_VALUE);
                maxValue = Short.toString(Short.MAX_VALUE);
                break;
            case "int", "integer":
                minValue = Integer.toString(Integer.MIN_VALUE);
                maxValue = Integer.toString(Integer.MAX_VALUE);
                break;
            case "long":
                minValue = Long.toString(Long.MIN_VALUE);
                maxValue = Long.toString(Long.MAX_VALUE);
                break;
            case "float":
                minValue = Float.toString(Float.MIN_VALUE);
                maxValue = Float.toString(Float.MAX_VALUE);
                break;
            case "double":
                minValue = Double.toString(Float.MIN_VALUE);
                maxValue = Double.toString(Float.MAX_VALUE);
                break;
            case "boolean", "bool":
                minValue = Boolean.toString(Boolean.FALSE);
                maxValue = Boolean.toString(Boolean.TRUE);
                break;
            case "char", "character":
                minValue = Integer.toString(Character.MIN_VALUE);
                maxValue = Integer.toString(Character.MAX_VALUE);
                break;
            default:
                System.out.println(primitive + " WRONG TYPE!!!");
                return;
        }
        System.out.println(primitive.toLowerCase() + " : min value = " + minValue + " | max value = " + maxValue);
    }
}
