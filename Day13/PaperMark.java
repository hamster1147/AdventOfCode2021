public class PaperMark {
    private int m_x;
    private int m_y;

    public PaperMark(int x, int y) {
        m_x = x;
        m_y = y;
    }

    public PaperMark(String markString) {
        String[] input = markString.split(",");
        m_x = Integer.parseInt(input[0]);
        m_y = Integer.parseInt(input[1]);
    }

    public PaperMark(PaperMark otherPaperMark) {
        m_x = otherPaperMark.getX();
        m_x = otherPaperMark.getY();
    }

    public boolean equals(PaperMark otherMark) {
        return getX() == otherMark.getX() && getY() == otherMark.getY();
    }

    public void fold(PaperFold fold) {
        if (fold.getFoldType() == PaperFold.FoldType.VERTICAL) {
            foldAlongYAxis(fold.getCoordinate());
        } else {
            foldAlongXAxis(fold.getCoordinate());
        }
    }

    public void foldAlongXAxis(int y) {
        if (m_y > y) {
            m_y = y - (m_y - y);
        }
    }

    public void foldAlongYAxis(int x) {
        if (m_x > x) {
            m_x = x - (m_x - x);
        }
    }

    public int getX() {
        return m_x;
    }

    public int getY() {
        return m_y;
    }
}
