// Tasks from lecture
// TODO abs(-50); expects 50
// TODO min(50, 6); expect 6

public class TaskFromLecture {

    public static void main(String[] args) {
        System.out.println("abs: " + abs(-50));
        System.out.println("min: " + min(50, 6));
    }
    //====================================================================//

    static double abs(int num){
        return num < 0 ? -num : num;
    }

    static double min(int a, int b) {
        return a < b ? a : b;
    }
}
