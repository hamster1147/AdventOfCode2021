import java.util.ArrayList;

public class OctopusGrid {
    ArrayList<ArrayList<Octopus>> m_grid;

    public OctopusGrid() {
        m_grid = new ArrayList<ArrayList<Octopus>>();
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
                flashTotal += octopus.step();
            }
        }

        return flashTotal;
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
}
