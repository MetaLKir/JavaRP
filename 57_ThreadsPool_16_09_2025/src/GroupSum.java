public abstract class GroupSum {
    protected int[][] numbers;

    public abstract long computeSum();

    public GroupSum(int[][] numbers) {
        this.numbers = numbers;
    }

    public int[][] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[][] numbers) {
        this.numbers = numbers;
    }
}
