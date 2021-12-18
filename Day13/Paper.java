import java.util.ArrayList;

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
}