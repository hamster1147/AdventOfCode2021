import java.util.Map;
import java.util.TreeMap;

public class ElementTotals {
    TreeMap<Character, Integer> m_elements;

    ElementTotals() {
        m_elements = new TreeMap<Character, Integer>();
    }

    public void addElement(char element) {
        if (m_elements.containsKey(element)) {
            m_elements.replace(element, m_elements.get(element) + 1);
        } else {
            m_elements.put(element, 1);
        }
    }

    public int getMostCommonCount() {
        int mostCommon = Integer.MIN_VALUE;

        for (Map.Entry<Character, Integer> tally : m_elements.entrySet()) {
            mostCommon = Math.max(mostCommon, tally.getValue());
        }

        return mostCommon;
    }

    public int getLeastCommonCount() {
        int leastCommon = Integer.MAX_VALUE;

        for (Map.Entry<Character, Integer> tally : m_elements.entrySet()) {
            leastCommon = Math.min(leastCommon, tally.getValue());
        }

        return leastCommon;
    }
}
