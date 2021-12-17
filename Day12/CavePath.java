import java.util.ArrayList;

public class CavePath {
    private ArrayList<CaveSite> m_path;

    public CavePath() {
        m_path = new ArrayList<CaveSite>();
    }

    public ArrayList<CaveSite> getList() {
        return m_path;
    }

    public String toString() {
        StringBuilder string = new StringBuilder();
        for (CaveSite site : m_path) {
            string.append(site.getName());
            if (!site.isEnd()) {
                string.append(',');
            }
        }
        return string.toString();
    }
}
