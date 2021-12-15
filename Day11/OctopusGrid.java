import java.util.ArrayList;

public class OctopusGrid {
    private ArrayList<ArrayList<Octopus>> m_grid;
    private boolean m_allFlashed;

    public OctopusGrid() {
        m_grid = new ArrayList<ArrayList<Octopus>>();
        m_allFlashed = false;
    }

    public void addRowString(String rowString) {
        ArrayList<Octopus> octopusRow = new ArrayList<Octopus>();
        int x = 0;
        int y = m_grid.size();

        for (char energyChar : rowString.toCharArray()) {
            octopusRow.add(new Octopus(x++, y, Integer.parseInt(String.valueOf(energyChar))));
        }

        m_grid.add(octopusRow);
    }

    public int getMaxX() {
        return m_grid.get(0).size() - 1;
    }

    public int getMaxY() {
        return m_grid.size() - 1;
    }

    public Octopus getOctopus(int x, int y) {
        if (x >= 0 && x <= getMaxX() && y >= 0 && y <= getMaxY()) {
            return m_grid.get(y).get(x);
        } else {
            return new Octopus();
        }
    }

    public int step() {
        int flashTotal = 0;
        for (ArrayList<Octopus> list : m_grid) {
            for (Octopus octopus : list) {
                octopus.step();
            }
        }

        boolean flashDetected = false;
        do {
            flashDetected = false;
            for (ArrayList<Octopus> list : m_grid) {
                for (Octopus octopus : list) {
                    if (octopus.flash()) {
                        flashTotal++;
                        flashDetected = true;
                    }
                }
            }
        } while (flashDetected);

        m_allFlashed = true;
        for (ArrayList<Octopus> list : m_grid) {
            for (Octopus octopus : list) {
                octopus.finishStep();
                if (octopus.getEnergy() != 0) {
                    m_allFlashed = false;
                }
            }
        }

        if (m_allFlashed) {
            return 0;
        }

        return flashTotal;
    }

    public boolean getAllFlashed() {
        return m_allFlashed;
    }

    public void populateOctopusReferences() {
        for (ArrayList<Octopus> cavePointRow : m_grid) {
            for (Octopus cavePoint : cavePointRow) {
                cavePoint.setTopLeft(getOctopus(cavePoint.getX() - 1, cavePoint.getY() - 1));
                cavePoint.setTop(getOctopus(cavePoint.getX(), cavePoint.getY() - 1));
                cavePoint.setTopRight(getOctopus(cavePoint.getX() + 1, cavePoint.getY() - 1));
                cavePoint.setRight(getOctopus(cavePoint.getX() + 1, cavePoint.getY()));
                cavePoint.setBottomRight(getOctopus(cavePoint.getX() + 1, cavePoint.getY() + 1));
                cavePoint.setBottom(getOctopus(cavePoint.getX(), cavePoint.getY() + 1));
                cavePoint.setBottomLeft(getOctopus(cavePoint.getX() - 1, cavePoint.getY() + 1));
                cavePoint.setLeft(getOctopus(cavePoint.getX() - 1, cavePoint.getY()));
            }
        }
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        for (ArrayList<Octopus> list : m_grid) {
            for (Octopus octopus : list) {
                result.append(octopus.getEnergy());
            }
            result.append('\n');
        }

        return result.toString();
    }
}
