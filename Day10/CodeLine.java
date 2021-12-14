import java.util.ArrayList;
import java.util.Stack;

public class CodeLine {
    String m_lineString;
    ArrayList<Bracket> m_bracketList;

    public static enum Bracket {
        CURVED_OPEN('(', 0, 3, 1),
        CURVED_CLOSE(')', 0, 3, 1),
        SQUARE_OPEN('[', 1, 57, 2),
        SQUARE_CLOSE(']', 1, 57, 2),
        CURLY_OPEN('{', 2, 1197, 3),
        CURLY_CLOSE('}', 2, 1197, 3),
        ANGLED_OPEN('<', 3, 25137, 4),
        ANGLED_CLOSE('>', 3, 25137, 4);

        private char m_bracket;
        private int m_id;
        private int m_corruptedScore;
        private int m_incompleteScore;

        private Bracket(char bracket, int id, int corruptedScore, int incompleteScore) {
            m_bracket = bracket;
            m_id = id;
            m_corruptedScore = corruptedScore;
            m_incompleteScore = incompleteScore;
        }

        public char getBracket() {
            return m_bracket;
        }

        public int getId() {
            return m_id;
        }

        public int getCorruptedScore() {
            return m_corruptedScore;
        }

        public int getIncompleteScore() {
            return m_incompleteScore;
        }
    }

    public CodeLine(String line) {
        m_lineString = line;
        m_bracketList = getBracketList(line);
    }

    public Bracket findCurroptedBracket() {
        Stack<Bracket> bracketStack = new Stack<Bracket>();

        for (Bracket bracket : m_bracketList) {
            if (isOpenBracket(bracket)) {
                bracketStack.add(bracket);
            } else {
                if (isMatchingBracket(bracket, bracketStack.peek())) {
                    bracketStack.pop();
                } else {
                    return bracket;
                }
            }
        }

        return null;
    }

    public ArrayList<Bracket> findMissingBrackets() {
        Stack<Bracket> bracketStack = new Stack<Bracket>();

        for (Bracket bracket : m_bracketList) {
            if (isOpenBracket(bracket)) {
                bracketStack.add(bracket);
            } else {
                if (isMatchingBracket(bracket, bracketStack.peek())) {
                    bracketStack.pop();
                }
            }
        }

        ArrayList<Bracket> closingBrackets = new ArrayList<Bracket>();
        while (!bracketStack.empty()) {
            closingBrackets.add(getMatchingBracket(bracketStack.pop()));
        }

        return closingBrackets;
    }

    public static boolean isOpenBracket(Bracket bracket) {
        return (bracket == Bracket.CURVED_OPEN ||
                bracket == Bracket.SQUARE_OPEN ||
                bracket == Bracket.CURLY_OPEN ||
                bracket == Bracket.ANGLED_OPEN);
    }

    public static boolean isCloseBracket(Bracket bracket) {
        return (bracket == Bracket.CURVED_CLOSE ||
                bracket == Bracket.SQUARE_CLOSE ||
                bracket == Bracket.CURLY_CLOSE ||
                bracket == Bracket.ANGLED_CLOSE);
    }

    public static boolean isMatchingBracket(Bracket bracket, Bracket otherBracket) {
        return (bracket.getId() == otherBracket.getId());
    }

    public static Bracket getMatchingBracket(Bracket bracket) {
        if (bracket == Bracket.ANGLED_OPEN) {
            return Bracket.ANGLED_CLOSE;
        } else if (bracket == Bracket.ANGLED_CLOSE) {
            return Bracket.ANGLED_OPEN;
        } else if (bracket == Bracket.CURVED_OPEN) {
            return Bracket.CURVED_CLOSE;
        } else if (bracket == Bracket.CURVED_CLOSE) {
            return Bracket.CURVED_OPEN;
        } else if (bracket == Bracket.CURLY_OPEN) {
            return Bracket.CURLY_CLOSE;
        } else if (bracket == Bracket.CURLY_CLOSE) {
            return Bracket.CURLY_OPEN;
        } else if (bracket == Bracket.SQUARE_OPEN) {
            return Bracket.SQUARE_CLOSE;
        } else {
            return Bracket.SQUARE_OPEN;
        }
    }

    public static ArrayList<Bracket> getBracketList(String line) {
        ArrayList<Bracket> brackets = new ArrayList<Bracket>();

        for (char bracketChar : line.toCharArray()) {
            boolean found = false;

            for (Bracket bracket : Bracket.values()) {
                if (bracketChar == bracket.getBracket()) {
                    brackets.add(bracket);
                    found = true;
                }
            }

            if (!found) {
                System.out.println("Could not kind Bracket enum to match char: " + bracketChar);
            }
        }

        return brackets;
    }
}
