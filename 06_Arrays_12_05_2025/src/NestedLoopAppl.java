public class NestedLoopAppl {
    public static void main(String[] args) {
        //pithagoras();
        figure(5);
    }

    private static void figure2(int size) {
        for (int i = 0; i < size; i++){
            for(int j = 0; j < i+1; j++){
                System.out.print("*\t");
            }
            System.out.println();
        }
    }

    private static void figure(int size) {
        for (int i = 1; i <= size; i++){
            for(int j = 1; j <= size; j++){
                if (i >= size - j + 1 && i >= j)
                    System.out.print("*\t");
                else
                    System.out.print(" \t");
            }
            System.out.println();
        }
    }

    private static void figure3(int size) {
        for (int i = size; i > 0; i--){
            for(int j = 0; j < i; j++){
                System.out.print("*\t");
            }
            System.out.println();
        }
    }

    private static void figure1(int size) {
        for(int i = 0; i < size; i++){
            for (int j = 0; j < i+1; j++){
                System.out.println(" ");
            }
            //for (int k = )

        }

//        for (int i = 1; i <= size; i++) {
//            for (int j = 1; j <= size - i; j++) {
//                System.out.print(" ");
//            }
//            for (int k = 1; k <= (2 * i - 1); k++) {
//                System.out.print("*");
//            }
//            System.out.println();
//        }
    }

    private static void pithagoras() {
        for(int i = 1; i <= 10; i++){
            for(int j = 1; j <= 10; j++){
                System.out.print(i * j + "\t");
            }
            System.out.println();
        }
    }
}
