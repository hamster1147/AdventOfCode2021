import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map.Entry;

public class Polymerizer {
    ArrayList<Character> m_elements;
    ArrayList<PairInsertionRules> m_rules;

    public Polymerizer() {
        m_elements = new ArrayList<Character>();
        m_rules = new ArrayList<PairInsertionRules>();
    }

    public ArrayList<Character> getElementsList() {
        return m_elements;
    }

    public ArrayList<PairInsertionRules> getRulesList() {
        return m_rules;
    }

    public void polymerize() {
        ArrayList<Character> newElementsList = new ArrayList<Character>();

        for (int i = 1; i < m_elements.size(); i++) {
            Character newElement = null;
            for (PairInsertionRules rule : m_rules) {
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

    public int getSolution() {
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
