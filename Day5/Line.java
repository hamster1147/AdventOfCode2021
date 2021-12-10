import java.util.ArrayList;

public class Line {
    public enum LineType {
        HORIZONTAL,
        VERTICAL,
        DIAGONAL_RISING,
        DIAGONAL_FALLING
    }

    private Coordinate m_start;
    private Coordinate m_end;
    private ArrayList<Coordinate> m_coordinates;
    private LineType m_type;

    public Line(int x1, int y1, int x2, int y2) {
        this(new Coordinate(x1, y1), new Coordinate(x2, y2));
    }

    public Line(Coordinate start, Coordinate end) {
        m_start = start;
        m_end = end;
        m_type = calculateLineType(m_start, m_end);
        fixCoordinateOrder();
        m_coordinates = generateCoordinates();
    }

    // 561,579 -> 965,175
    public Line(String csvLine) {
        String coordinates[] = csvLine.split(",|(\\s->\\s)");

        m_start = new Coordinate(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
        m_end = new Coordinate(Integer.parseInt(coordinates[2]), Integer.parseInt(coordinates[3]));
        m_type = calculateLineType(m_start, m_end);
        fixCoordinateOrder();
        m_coordinates = generateCoordinates();
    }

    private void fixCoordinateOrder() {
        if (m_start.getX() > m_end.getX() || m_start.getY() > m_end.getY()) {
            Coordinate temp = m_start;
            m_start = m_end;
            m_end = temp;
        }
    }

    private ArrayList<Coordinate> generateCoordinates() {
        ArrayList<Coordinate> list = new ArrayList<Coordinate>();
        int deltaX = m_end.getX() - m_start.getX();
        int deltaY = m_end.getY() - m_start.getY();
        int totalPoints = Math.max(Math.abs(deltaX), Math.abs(deltaY));

        for (int i = 0; i <= totalPoints; i++) {
            int x = m_start.getX() + (i * (int) Math.signum(deltaX));
            int y = m_start.getY() + (i * (int) Math.signum(deltaY));
            list.add(new Coordinate(x, y));
        }

        return list;
    }

    public Coordinate getStart() {
        return m_start;
    }

    public Coordinate getEnd() {
        return m_end;
    }

    public LineType getLineType() {
        return m_type;
    }

    public ArrayList<Coordinate> getCoordinates() {
        return m_coordinates;
    }

    private static LineType calculateLineType(Coordinate start, Coordinate end) {
        if (start.getX() == end.getX()) {
            return LineType.VERTICAL;
        } else if (start.getY() == end.getY()) {
            return LineType.HORIZONTAL;
        } else {
            int deltaX = start.getX() - end.getX();
            int deltaY = start.getY() - end.getY();

            if (Integer.signum(deltaX) == Integer.signum(deltaY)) {
                return LineType.DIAGONAL_RISING;
            } else {
                return LineType.DIAGONAL_FALLING;
            }
        }
    }

    public static ArrayList<Coordinate> getIntersectingCoordinates(Line lineA, Line lineB, boolean part2) {
        ArrayList<Coordinate> result = new ArrayList<Coordinate>();

        if ((lineA.getLineType() == LineType.DIAGONAL_RISING || lineA.getLineType() == LineType.DIAGONAL_FALLING ||
                lineB.getLineType() == LineType.DIAGONAL_RISING || lineB.getLineType() == LineType.DIAGONAL_FALLING)
                && !part2) {
            return result;
        }

        for (Coordinate coordA : lineA.getCoordinates()) {
            for (Coordinate coordB : lineB.getCoordinates()) {
                if (coordA.equals(coordB)) {
                    result.add(coordA);
                }
            }
        }

        return result;
    }
}
