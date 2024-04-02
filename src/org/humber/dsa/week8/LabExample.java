package org.humber.dsa.week8;

import java.util.*;

public class LabExample {

    private static final Map<Character, Character> closingBracketOf = new HashMap<>() {
        {
            put('{', '}');
            put('(', ')');
            put('[', ']');
        }
    };

    /**
     * Complete the 'isBalanced' method below.
     * <p>
     * @param input String is provided as method argument
     * @return Method expected to return TRUE or FALSE
     */
    public static boolean isBalanced(String input) {
        // Write your code here
        //HINT: use stacks
        Stack<Character> stack = new Stack<>();
        for (int index = 0; index < input.length(); index++) {
            char ch = input.charAt(index);
            if (isOpeningBracket(ch)) {
                stack.push(ch);
                continue;
            }
            if (stack.isEmpty() || isNotMatching(stack.pop(), ch)) {
                return false;
            }
        }
        return stack.isEmpty();
    }

    private static boolean isNotMatching(char openingBracket, char closingBracket) {
        return closingBracketOf.get(openingBracket) != closingBracket;
    }

    private static boolean isOpeningBracket(char ch) {
        return closingBracketOf.containsKey(ch);
    }

    public static void main(String[] args) {
        List<String> inputs = Arrays.asList(
                "())",
                "()(",
                "(",
                ")",
                "()",
                "[()]",
                "{[()]}",
                "{[(])}",
                "{{[[(())]]}}",
                "[{[({({[()]})}({[()]}){[()]})[({[()]})]})]",
                "({[([{({[]})}([{}])])]}[({[]})])"
        );
        for (String input : inputs) {
            System.out.printf("Is %s Balanced?: %s\n", input, isBalanced(input) ? "YES" : "NO");
        }
    }
}
