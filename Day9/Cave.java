import java.util.ArrayList;

public class Cave {
    ArrayList<ArrayList<CavePoint>> m_map;

    public Cave() {
        m_map = new ArrayList<ArrayList<CavePoint>>();
    }

    public void addRowString(String rowString) {
        ArrayList<CavePoint> cavePointsRow = new ArrayList<CavePoint>();
        int x = 0;
        int y = m_map.size();

        for (char pointChar : rowString.toCharArray()) {
            cavePointsRow.add(new CavePoint(x++, y, Integer.parseInt(String.valueOf(pointChar))));
        }

        m_map.add(cavePointsRow);
    }

    public int getMaxX() {
        return m_map.get(0).size() - 1;
    }

    public int getMaxY() {
        return m_map.size() - 1;
    }

    public CavePoint getCavePoint(int x, int y) {
        if (x >= 0 && x <= getMaxX() && y >= 0 && y <= getMaxY()) {
            return m_map.get(y).get(x);
        } else {
            return new CavePoint();
        }
    }

    public void populateCavePointReferences() {
        for (ArrayList<CavePoint> cavePointRow : m_map) {
            for (CavePoint cavePoint : cavePointRow) {
                cavePoint.setUp(getCavePoint(cavePoint.getX(), cavePoint.getY() - 1));
                cavePoint.setDown(getCavePoint(cavePoint.getX(), cavePoint.getY() + 1));
                cavePoint.setLeft(getCavePoint(cavePoint.getX() - 1, cavePoint.getY()));
                cavePoint.setRight(getCavePoint(cavePoint.getX() + 1, cavePoint.getY()));
            }
        }
    }

    public ArrayList<CavePoint> getListOfLowestPoints() {
        ArrayList<CavePoint> list = new ArrayList<CavePoint>();

        for (ArrayList<CavePoint> cavePointRow : m_map) {
            for (CavePoint cavePoint : cavePointRow) {
                if (cavePoint.isLowestPoint()) {
                    list.add(cavePoint);
                }
            }
        }

        return list;
    }
}
