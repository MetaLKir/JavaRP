import java.util.Arrays;

public class GroupSumStreamParallel extends GroupSum{

    public GroupSumStreamParallel(int[][] numbers) {
        super(numbers);
    }

    @Override
    public long computeSum() {
        return Arrays.stream(numbers).
                parallel().
                flatMapToLong(e -> Arrays.stream(e).asLongStream()).
                sum();
    }
}
