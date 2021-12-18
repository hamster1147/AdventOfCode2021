public class PaperFold {
    public enum FoldType {
        HORIZONTAL,
        VERTICAL;
    }

    private FoldType m_foldType;
    private int m_coordinate;

    public PaperFold(FoldType foldType, int coordinate) {
        m_foldType = foldType;
        m_coordinate = coordinate;
    }

    public PaperFold(String foldString) {
        String[] settings = foldString.split("fold along ")[1].split("=");
        m_coordinate = Integer.valueOf(settings[1]);

        if (settings[0].equals("y")) {
            m_foldType = FoldType.HORIZONTAL;
        } else {
            m_foldType = FoldType.VERTICAL;
        }
    }

    public FoldType getFoldType() {
        return m_foldType;
    }

    public int getCoordinate() {
        return m_coordinate;
    }
}
