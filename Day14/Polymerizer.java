import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map.Entry;

public class Polymerizer {
    ArrayList<Character> m_elements;
    ArrayList<PairInsertionRule> m_rules;

    public Polymerizer() {
        m_elements = new ArrayList<Character>();
        m_rules = new ArrayList<PairInsertionRule>();
    }

    public ArrayList<Character> getElementsList() {
        return m_elements;
    }

    public ArrayList<PairInsertionRule> getRulesList() {
        return m_rules;
    }

    private void setupRuleReferences() {
        for (PairInsertionRule rule : m_rules) {
            rule.setupReferences(m_rules);
        }
    }

    public ElementTotals polymerize(int count) {
        ElementTotals totals = new ElementTotals();

        setupRuleReferences();

        for (int i = 1; i < m_elements.size(); i++) {
            for (PairInsertionRule rule : m_rules) {
                if (rule.match(String.valueOf(m_elements.get(i - 1)) + m_elements.get(i))) {
                    totals = rule.polymerize(totals, count);
                }
            }
            System.out.println(i + " out of " + m_elements.size());
        }

        totals.addElement(m_elements.get(m_elements.size() - 1));

        return totals;
    }

    public void polymerize() {
        ArrayList<Character> newElementsList = new ArrayList<Character>();

        for (int i = 1; i < m_elements.size(); i++) {
            Character newElement = null;
            for (PairInsertionRule rule : m_rules) {
                newElement = rule.getInsertion(String.valueOf(m_elements.get(i - 1)) + m_elements.get(i));
                if (newElement != null) {
                    break;
                }
            }

            newElementsList.add(m_elements.get(i - 1));
            if (newElement != null) {
                newElementsList.add(newElement);
            }
        }

        newElementsList.add(m_elements.get(m_elements.size() - 1));

        m_elements = newElementsList;
    }

    public int getPart1Solution() {
        ArrayList<Entry<Character, Integer>> elementTally = new ArrayList<Entry<Character, Integer>>();

        for (Character element : m_elements) {
            boolean found = false;
            for (Entry<Character, Integer> tally : elementTally) {
                if (tally.getKey() == element) {
                    tally.setValue(tally.getValue() + 1);
                    found = true;
                    break;
                }
            }

            if (!found) {
                elementTally.add(new AbstractMap.SimpleEntry<Character, Integer>(element, 1));
            }
        }

        int mostCommon = Integer.MIN_VALUE;
        int leastCommon = Integer.MAX_VALUE;

        for (Entry<Character, Integer> tally : elementTally) {
            mostCommon = Math.max(mostCommon, tally.getValue());
            leastCommon = Math.min(leastCommon, tally.getValue());
        }

        return mostCommon - leastCommon;
    }
}
