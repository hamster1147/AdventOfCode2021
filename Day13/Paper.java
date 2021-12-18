import java.util.ArrayList;
import java.util.Collections;

public class Paper {
    private ArrayList<PaperMark> m_markings;
    private ArrayList<PaperFold> m_folds;

    public Paper() {
        m_markings = new ArrayList<PaperMark>();
        m_folds = new ArrayList<PaperFold>();
    }

    public ArrayList<PaperMark> getMarkingList() {
        return m_markings;
    }

    public ArrayList<PaperFold> getFoldsList() {
        return m_folds;
    }

    public void carryOutFolds() {
        for (PaperFold fold : m_folds) {
            fold(fold);
        }
    }

    public void fold(PaperFold fold) {
        ArrayList<PaperMark> newMarkings = new ArrayList<PaperMark>();
        for (PaperMark mark : m_markings) {
            mark.fold(fold);

            boolean foundDupe = false;
            for (PaperMark newMark : newMarkings) {
                if (mark.equals(newMark)) {
                    foundDupe = true;
                    break;
                }
            }

            if (!foundDupe) {
                newMarkings.add(mark);
            }
        }
        m_markings = newMarkings;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();

        int maxX = 0;
        int maxY = 0;
        for (PaperMark mark : m_markings) {
            maxX = Math.max(maxX, mark.getX());
            maxY = Math.max(maxY, mark.getY());
        }

        for (int y = 0; y <= maxY; y++) {
            for (int x = 0; x <= maxX; x++) {
                boolean markFound = false;
                for (PaperMark mark : m_markings) {
                    if (x == mark.getX() && y == mark.getY()) {
                        string.append('*');
                        markFound = true;
                        break;
                    }
                }

                if (!markFound) {
                    string.append(' ');
                }
            }
            string.append('\n');
        }

        return string.toString();
    }
}