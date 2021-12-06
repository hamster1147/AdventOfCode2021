public class BingoBoard {
    public static int k_boardSize = 5;
    /** Represents the locations on the bingo board */
    private BingoBoardLocation m_boardNumbers[][] = new BingoBoardLocation[k_boardSize][k_boardSize];

    public BingoBoard() {

    }

    public void setBoardLocation(int x, int y, int value) {
        if (x >= 0 && x < k_boardSize && y >= 0 && y < k_boardSize) {
            m_boardNumbers[x][y] = new BingoBoardLocation(x, y, value);
        }
    }

    public void checkAndMarkNumber(int value) {
        for (int x = 0; x < k_boardSize; x++) {
            for (int y = 0; y < k_boardSize; y++) {
                m_boardNumbers[x][y].markLocation(value);
            }
        }
    }

    public boolean isWinningBoard() {
        // Check all rows
        for (int x = 0; x < k_boardSize; x++) {
            // Start with true, and check if any are not marked
            boolean rowResult = true;
            for (int y = 0; y < k_boardSize; y++) {
                if (!m_boardNumbers[x][y].m_marked) {
                    rowResult = false;
                }
            }

            if (rowResult) {
                return true;
            }
        }

        // Check all columns
        for (int y = 0; y < k_boardSize; y++) {
            // Start with true, and check if any are not marked
            boolean columnResult = true;
            for (int x = 0; x < k_boardSize; x++) {
                if (!m_boardNumbers[x][y].m_marked) {
                    columnResult = false;
                }
            }

            if (columnResult) {
                return true;
            }
        }

        return false;
    }

    public int calculatePart1Solution(int numberJustCalled) {
        int unmarkedSum = 0;

        for (int x = 0; x < k_boardSize; x++) {
            for (int y = 0; y < k_boardSize; y++) {
                if (!m_boardNumbers[x][y].getIfMarked()) {
                    unmarkedSum += m_boardNumbers[x][y].getValue();
                }
            }
        }

        return numberJustCalled * unmarkedSum;
    }

    class BingoBoardLocation {
        private int m_x;
        private int m_y;
        private int m_value;
        private boolean m_marked;

        public BingoBoardLocation(int x, int y, int value) {
            m_x = x;
            m_y = y;
            m_value = value;
            m_marked = false;
        }

        public void markLocation(int value) {
            if (value == m_value) {
                m_marked = true;
            }
        }

        public void markLocation() {
            m_marked = true;
        }

        public int getX() {
            return m_x;
        }

        public int getY() {
            return m_y;
        }

        public int getValue() {
            return m_value;
        }

        public boolean getIfMarked() {
            return m_marked;
        }
    }
}
