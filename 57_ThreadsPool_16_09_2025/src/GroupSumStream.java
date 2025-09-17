import java.util.Arrays;

public class GroupSumStream extends GroupSum{

    public GroupSumStream(int[][] numbers) {
        super(numbers);
    }

    @Override
    public long computeSum() {
        return Arrays.stream(numbers).
                flatMapToLong(e -> Arrays.stream(e).asLongStream()).
                sum();
    }
}
