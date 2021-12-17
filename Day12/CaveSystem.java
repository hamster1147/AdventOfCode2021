import java.util.ArrayList;

public class CaveSystem {
    private static String k_startString = "start";
    private static String k_endString = "end";

    ArrayList<CaveSite> m_cavePoints = new ArrayList<CaveSite>();

    public CaveSystem() {
        m_cavePoints = new ArrayList<CaveSite>();
    }

    public void addConnection(String connectionString) {
        String[] cavePointStrings = connectionString.split("-");
        String leftString = cavePointStrings[0];
        String rightString = cavePointStrings[1];

        CaveSite leftPoint = null;
        CaveSite rightPoint = null;
        for (CaveSite cave : m_cavePoints) {
            if (cave.getName().equals(leftString)) {
                leftPoint = cave;
            }

            if (cave.getName().equals(rightString)) {
                rightPoint = cave;
            }
        }

        if (leftPoint == null) {
            leftPoint = new CaveSite(leftString, leftString.equals(leftString.toLowerCase()),
                    leftString.equals(k_startString), leftString.equals(k_endString));
            m_cavePoints.add(leftPoint);
        }

        if (rightPoint == null) {
            rightPoint = new CaveSite(rightString, rightString.equals(rightString.toLowerCase()),
                    rightString.equals(k_startString), rightString.equals(k_endString));
            m_cavePoints.add(rightPoint);
        }

        leftPoint.addConnection(rightPoint);
        rightPoint.addConnection(leftPoint);
    }

    public ArrayList<CavePath> spelunk() {
        ArrayList<CavePath> paths = null;

        for (CaveSite cave : m_cavePoints) {
            if (cave.getName().equals(k_startString)) {
                // Spelunk function needs a CavePath to build its current path off of
                CavePath currentPath = new CavePath();
                paths = cave.spelunk(currentPath);
                break;
            }
        }

        return paths;
    }
}
