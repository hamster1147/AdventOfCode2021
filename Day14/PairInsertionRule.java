import java.util.ArrayList;

public class PairInsertionRule {
    private char m_firstElement;
    private char m_secondElement;
    private char m_insertionElement;
    private PairInsertionRule m_leftRule;
    private PairInsertionRule m_rightRule;

    public PairInsertionRule(String pairRuleString) {
        String[] split = pairRuleString.split(" -> ");
        m_firstElement = split[0].charAt(0);
        m_secondElement = split[0].charAt(1);
        m_insertionElement = split[1].charAt(0);
        m_leftRule = null;
        m_rightRule = null;
    }

    public void setupReferences(ArrayList<PairInsertionRule> rules) {
        for (PairInsertionRule rule : rules) {
            if (rule.getFirstElement() == m_firstElement && rule.getSecondElement() == m_insertionElement) {
                m_leftRule = rule;
            }

            if (rule.getFirstElement() == m_insertionElement && rule.getSecondElement() == m_secondElement) {
                m_rightRule = rule;
            }
        }
    }

    public ElementTotals polymerize(ElementTotals totals, int count) {
        ElementTotals newTotals = totals;

        if (count == 1) {
            newTotals.addElement(m_firstElement);
            newTotals.addElement(m_insertionElement);
            return newTotals;
        }

        if ((count - 10) > 0) {
            count -= 10;
            newTotals = polymerizeTenTimes(newTotals);
        } else {
            count--;
            if (m_leftRule != null) {
                newTotals = m_leftRule.polymerize(newTotals, count);
            }

            if (m_leftRule != null) {
                newTotals = m_rightRule.polymerize(newTotals, count);
            }
        }

        if (count == 30) {
            System.out.println("Finish 30");
        }

        if (count == 35) {
            System.out.println("Finish 35");
        }

        return newTotals;
    }

    public ElementTotals polymerizeTenTimes(ElementTotals totals) {
        ElementTotals newTotals = totals;

        newTotals = polymerize(newTotals, 10);

        return newTotals;
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

    public boolean match(String elements) {
        if (elements.length() != 2) {
            return false;
        }

        return (elements.charAt(0) == m_firstElement && elements.charAt(1) == m_secondElement);
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
}
