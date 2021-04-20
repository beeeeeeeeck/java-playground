package org.bl.leetcode.medium;

import java.util.EnumMap;
import java.util.Map;

public class StringToIntegerByStateMachine {
    public static int myAtoi(String s) {
        Convertor convertor = new Convertor();
        int length = s.length();
        for (int i = 0; i < length; ++i) {
            boolean shouldContinue = convertor.convert(s.charAt(i));
            if (!shouldContinue) break;
        }
        return (int) (convertor.sign * convertor.result);
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("42"));
        System.out.println(myAtoi("    -42"));
        System.out.println(myAtoi("4193 with words"));
        System.out.println(myAtoi("words and 987"));
        System.out.println(myAtoi("-91283472332"));
    }

    enum State {
        START,
        SIGN,
        NUMBER,
        END
    }

    static class Convertor {
        int sign = 1;
        long result = 0;
        State state = State.START;
        Map<State, State[]> states = new EnumMap<>(State.class) {{
            // illegal char, sign, number, end
            put(State.START, new State[]{State.START, State.SIGN, State.NUMBER, State.END});
            put(State.SIGN, new State[]{State.END, State.END, State.NUMBER, State.END});
            put(State.NUMBER, new State[]{State.END, State.END, State.NUMBER, State.END});
            put(State.END, new State[]{State.END, State.END, State.END, State.END});
        }};

        private boolean convert(char c) {
            state = states.get(state)[nextState(c)];
            if (state == State.END) {
                return false;
            }

            if (state == State.NUMBER) {
                result = result * 10 + c - '0';
                result = sign == 1 ? Math.min(result, Integer.MAX_VALUE) : Math.min(result, -(long) Integer.MIN_VALUE);
            } else if (state == State.SIGN) {
                sign = c == '+' ? 1 : -1;
            }

            return true;
        }

        private int nextState(char c) {
            if (c == ' ') {
                return 0;
            }

            if (c == '+' || c == '-') {
                return 1;
            }

            if (Character.isDigit(c)) {
                return 2;
            }

            return 3;
        }
    }
}
