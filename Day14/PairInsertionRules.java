public class PairInsertionRules {
    private char m_firstElement;
    private char m_secondElement;
    private char m_insertionElement;

    public PairInsertionRules(String pairRuleString) {
        String[] split = pairRuleString.split(" -> ");
        m_firstElement = split[0].charAt(0);
        m_secondElement = split[0].charAt(1);
        m_insertionElement = split[1].charAt(0);
    }

    public char getFirstElement() {
        return m_firstElement;
    }

    public char getSecondElement() {
        return m_secondElement;
    }

    public char getInsertionElement() {
        return m_insertionElement;
    }

    public Character getInsertion(String elements) {
        if (elements.length() != 2) {
            return null;
        }

        if (elements.charAt(0) == m_firstElement && elements.charAt(1) == m_secondElement) {
            return Character.valueOf(m_insertionElement);
        } else {
            return null;
        }
    }
}
