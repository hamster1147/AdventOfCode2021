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

    public ArrayList<CavePath> spelunk(CavePath currentPath, boolean part2) {
        ArrayList<CavePath> paths = new ArrayList<CavePath>();

        // Make unique CavePath
        CavePath newPath = new CavePath(currentPath);
        newPath.getList().add(this);

        // We are at the end, add our current unique path to the list and return it.
        if (isEnd()) {
            paths.add(newPath);
            return paths;
        }

        for (CaveSite cave : m_connectingCaves) {
            if (cave.isStart()) {
                continue;
            }

            // Check how many times we've been to the next cave
            int passCount = 0;
            for (CaveSite previousCave : newPath.getList()) {
                if (cave == previousCave) {
                    passCount++;
                }
            }

            if (!cave.isSmall()) {
                ArrayList<CavePath> returnedPaths = cave.spelunk(newPath, part2);
                // Add returned paths to our list of paths to return
                for (CavePath path : returnedPaths) {
                    paths.add(path);
                }
            } else {
                // We can't go through a cave more then once if its small, unless its part 2.
                boolean plannedSmallCave = (cave == newPath.getPlannedToVisitTwiceSmallCave());
                if (passCount < 1 || !cave.isSmall() || (passCount < 2 && plannedSmallCave)) {
                    CavePath newNewPath = new CavePath(newPath);
                    newNewPath.getAvoidSmallCaveList().add(cave);
                    ArrayList<CavePath> returnedPaths = cave.spelunk(newNewPath, part2);
                    // Add returned paths to our list of paths to return
                    for (CavePath path : returnedPaths) {
                        paths.add(path);
                    }

                    boolean avoidSmallCave = false;
                    for (CaveSite avoidCave : currentPath.getAvoidSmallCaveList()) {
                        if (cave == avoidCave) {
                            avoidSmallCave = true;
                        }
                    }

                    // For part 2, we are allowed to visit a single small cave twice
                    if (part2 && cave.isSmall() && !avoidSmallCave && !cave.isEnd()) {
                        newPath.setPlannedToVisitTwiceSmallCave(cave);
                        returnedPaths = cave.spelunk(newPath, part2);
                        // Add returned paths to our list of paths to return
                        for (CavePath path : returnedPaths) {
                            paths.add(path);
                        }
                    }
                }
            }
        }

        return paths;
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
