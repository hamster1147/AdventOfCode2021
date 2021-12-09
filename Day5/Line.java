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
    private LineType m_type;

    public Line(int x1, int y1, int x2, int y2) {
        this(new Coordinate(x1, y1), new Coordinate(x2, y2));
    }

    public Line(Coordinate start, Coordinate end) {
        m_start = start;
        m_end = end;
        m_type = calculateLineType(m_start, m_end);
        fixCoordinateOrder();
    }

    // 561,579 -> 965,175
    public Line(String csvLine) {
        String coordinates[] = csvLine.split(",|(\\s->\\s)");

        m_start = new Coordinate(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]));
        m_end = new Coordinate(Integer.parseInt(coordinates[2]), Integer.parseInt(coordinates[3]));
        m_type = calculateLineType(m_start, m_end);
        fixCoordinateOrder();
    }

    private void fixCoordinateOrder() {
        if (m_start.getX() > m_end.getX() || m_start.getY() > m_end.getY()) {
            Coordinate temp = m_start;
            m_start = m_end;
            m_end = temp;
        }
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

    public static ArrayList<Coordinate> getIntersectingCoordinates(Line lineA, Line lineB) {
        ArrayList<Coordinate> result = new ArrayList<Coordinate>();

        // Currently does not support diagonals
        if (lineA.getLineType() == LineType.DIAGONAL_RISING ||
                lineA.getLineType() == LineType.DIAGONAL_FALLING ||
                lineB.getLineType() == LineType.DIAGONAL_RISING ||
                lineB.getLineType() == LineType.DIAGONAL_FALLING) {
            return result;
        }

        if (lineA.getLineType() == lineB.getLineType()) {
            if (lineA.getLineType() == LineType.HORIZONTAL) {
                if (lineA.getStart().getY() == lineB.getStart().getY()) {
                    int start = 0;
                    int end = 0;
                    if (lineA.getStart().getX() >= lineB.getStart().getX()
                            && lineA.getStart().getX() <= lineB.getEnd().getX()) {
                        start = lineA.getStart().getX();
                        end = lineB.getEnd().getX();
                    } else if (lineB.getStart().getX() >= lineA.getStart().getX()
                            && lineB.getStart().getX() <= lineA.getEnd().getX()) {
                        start = lineB.getStart().getX();
                        end = lineA.getEnd().getX();
                    }

                    for (int x = start; x <= end; x++) {
                        result.add(new Coordinate(x, lineA.getStart().getY()));
                    }
                }
            } else if (lineA.getLineType() == LineType.VERTICAL) {
                if (lineA.getStart().getX() == lineB.getStart().getX()) {
                    int start = 0;
                    int end = 0;
                    if (lineA.getStart().getY() >= lineB.getStart().getY()
                            && lineA.getStart().getY() <= lineB.getEnd().getY()) {
                        start = lineA.getStart().getY();
                        end = lineB.getEnd().getY();
                    } else if (lineB.getStart().getY() >= lineA.getStart().getY()
                            && lineB.getStart().getY() <= lineA.getEnd().getY()) {
                        start = lineB.getStart().getY();
                        end = lineA.getEnd().getY();
                    }

                    for (int y = start; y <= end; y++) {
                        result.add(new Coordinate(lineA.getStart().getX(), y));
                    }
                }
            }
        } else {
            if (lineA.getStart().getX() >= lineB.getStart().getX() &&
                    lineA.getStart().getX() >= lineB.getEnd().getX() ||
                    lineB.getStart().getX() >= lineA.getStart().getX() &&
                            lineB.getStart().getX() >= lineA.getEnd().getX()) {
                if (lineA.getLineType() == LineType.VERTICAL) {
                    result.add(new Coordinate(lineA.getStart().getX(), lineB.getStart().getY()));
                } else {
                    result.add(new Coordinate(lineB.getStart().getX(), lineA.getStart().getY()));
                }
            }
        }

        return result;
    }
}
