package Day13;

public class PaperMark {
    private int m_x;
    private int m_y;

    public PaperMark(int x, int y) {
        m_x = x;
        m_y = y;
    }

    public PaperMark(PaperMark otherPaperMark) {
        m_x = otherPaperMark.getX();
        m_x = otherPaperMark.getY();
    }

    public void foldAlongXAxis(int y) {
        m_y = (m_y - y) - 1;
    }

    public void foldAlongYAxis(int x) {
        m_x = (m_x - x) - 1;
    }

    public int getX() {
        return m_x;
    }

    public int getY() {
        return m_y;
    }
}
