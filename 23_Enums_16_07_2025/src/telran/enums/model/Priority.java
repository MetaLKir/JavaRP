package telran.enums.model;

public enum Priority {
    LOW(1), SOFT(1, 1), MEDIUM(5), HIGH(10);

    private final int level;
    private int num;

    Priority(int level) {
        this.level = level;
    }

    Priority(int level, int num) {
        this.level = level;
        this.num = num;
    }

    public int getLevel() {
        return level;
    }
}
