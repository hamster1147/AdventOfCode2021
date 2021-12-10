public class Coordinate {
    private int m_x;
    private int m_y;

    public Coordinate(int x, int y) {
        m_x = x;
        m_y = y;
    }

    public boolean equals(Coordinate otherCoordinate) {
        return (getX() == otherCoordinate.getX() && getY() == otherCoordinate.getY());
    }

    public int getX() {
        return m_x;
    }

    public int getY() {
        return m_y;
    }
}
