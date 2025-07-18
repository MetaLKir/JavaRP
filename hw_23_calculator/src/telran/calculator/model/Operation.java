package telran.calculator.model;

public enum Operation {
    AND {
        @Override
        public int calculate(int a, int b) {
            return a & b;
        }
    },
    OR {
        @Override
        public int calculate(int a, int b) {
            return a | b;
        }
    },
    XOR {
        @Override
        public int calculate(int a, int b) {
            return a ^ b;
        }
    },
    NOT {
        @Override
        public int calculate(int a, int b) {
            return ~a;
        }
    },
    SHIFT_LEFT {
        @Override
        public int calculate(int a, int b) {
            return a << b;
        }
    },
    SHIFT_RIGHT {
        @Override
        public int calculate(int a, int b) {
            return a >> b;
        }
    },
    UNSIGNED_SHIFT_RIGHT {
        @Override
        public int calculate(int a, int b) {
            return a >>> b;
        }
    };

    public abstract int calculate(int a, int b);
}
