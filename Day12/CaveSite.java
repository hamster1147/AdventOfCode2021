import java.util.ArrayList;

public class CaveSite {
    private ArrayList<CaveSite> m_connectingCaves;
    private boolean m_smallCave;
    private String m_name;
    private boolean m_isStart;
    private boolean m_isEnd;

    public CaveSite(String name, boolean isSmall, boolean isStart, boolean isEnd) {
        m_name = name;
        m_smallCave = isSmall;
        m_isStart = isStart;
        m_isEnd = isEnd;
        m_connectingCaves = new ArrayList<CaveSite>();
    }

    public void addConnection(CaveSite cave) {
        m_connectingCaves.add(cave);
    }

    public String getName() {
        return m_name;
    }

    public boolean isSmall() {
        return m_smallCave;
    }

    public boolean isStart() {
        return m_isStart;
    }

    public boolean isEnd() {
        return m_isEnd;
    }
}
