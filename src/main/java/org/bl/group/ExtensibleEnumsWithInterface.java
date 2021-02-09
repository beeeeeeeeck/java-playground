package org.bl.group;

/**
 * @author beckl
 */
public class ExtensibleEnumsWithInterface {
    // Emulated extensible enum using an interface
    public interface Operation {
        double apply(double x, double y);
    }

    public enum BasicOperation implements Operation {
        PLUS("+") {
            public double apply(double x, double y) {
                return x + y;
            }
        },
        MINUS("-") {
            public double apply(double x, double y) {
                return x - y;
            }
        },
        TIMES("*") {
            public double apply(double x, double y) {
                return x * y;
            }
        },
        DIVIDE("/") {
            public double apply(double x, double y) {
                return x / y;
            }
        };

        private final String symbol;

        BasicOperation(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return symbol;
        }
    }

    // Emulated extension enum
    public enum ExtendedOperation implements Operation {
        EXP("^") {
            public double apply(double x, double y) {
                return Math.pow(x, y);
            }
        },
        REMAINDER("%") {
            public double apply(double x, double y) {
                return x % y;
            }
        };

        private final String symbol;

        ExtendedOperation(String symbol) {
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return symbol;
        }
    }

    public static void main(String[] args) {
        Operation op = BasicOperation.DIVIDE;
        System.out.println(op.apply(15, 3));
        op = ExtendedOperation.EXP;
        System.out.println(op.apply(2, 5));
    }
}
