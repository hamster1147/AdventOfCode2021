import java.util.ArrayList;

public class CavePath {
    private ArrayList<CaveSite> m_path;
    private CaveSite m_plannedSmallCaveToVisitTwice;
    private ArrayList<CaveSite> m_avoidSmallCaveTwice;

    public CavePath() {
        m_path = new ArrayList<CaveSite>();
        m_plannedSmallCaveToVisitTwice = null;
        m_avoidSmallCaveTwice = new ArrayList<CaveSite>();
        // m_smallCaveVisitedTwice = false;
    }

    public CavePath(CavePath otherPath) {
        m_path = new ArrayList<CaveSite>();
        for (CaveSite cave : otherPath.getList()) {
            m_path.add(cave);
        }

        for (CaveSite cave : otherPath.getAvoidSmallCaveList()) {

        }
        m_plannedSmallCaveToVisitTwice = otherPath.getPlannedToVisitTwiceSmallCave();

        m_avoidSmallCaveTwice = new ArrayList<CaveSite>();
        for (CaveSite cave : otherPath.getAvoidSmallCaveList()) {
            m_avoidSmallCaveTwice.add(cave);
        }
    }

    public ArrayList<CaveSite> getList() {
        return m_path;
    }

    public ArrayList<CaveSite> getAvoidSmallCaveList() {
        return m_avoidSmallCaveTwice;
    }

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
