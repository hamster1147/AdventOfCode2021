import java.util.ArrayList;

public class CavePath {
    private ArrayList<CaveSite> m_path;
    private CaveSite m_plannedSmallCaveToVisitTwice;
    // private boolean m_smallCaveVisitedTwice;

    public CavePath() {
        m_path = new ArrayList<CaveSite>();
        m_plannedSmallCaveToVisitTwice = null;
        // m_smallCaveVisitedTwice = false;
    }

    public CavePath(CavePath otherPath) {
        m_path = new ArrayList<CaveSite>();
        for (CaveSite cave : otherPath.getList()) {
            m_path.add(cave);
        }

        m_plannedSmallCaveToVisitTwice = otherPath.getPlannedToVisitTwiceSmallCave();
        // m_smallCaveVisitedTwice = false;
    }

    public ArrayList<CaveSite> getList() {
        return m_path;
    }

    // public void visitedPlannedSmallCaveTwice() {
    // m_smallCaveVisitedTwice = true;
    // }

    // public boolean planToVisitSmallCaveTwice() {
    // return m_smallCaveVisitedTwice;
    // }

    public void setPlannedToVisitTwiceSmallCave(CaveSite smallCave) {
        m_plannedSmallCaveToVisitTwice = smallCave;
    }

    public CaveSite getPlannedToVisitTwiceSmallCave() {
        return m_plannedSmallCaveToVisitTwice;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        for (CaveSite site : m_path) {
            string.append(site.getName());
            if (!site.isEnd()) {
                string.append(',');
            }
        }

        if (m_plannedSmallCaveToVisitTwice != null) {
            string.append(" - " + m_plannedSmallCaveToVisitTwice.getName());
        }

        return string.toString();
    }
}
