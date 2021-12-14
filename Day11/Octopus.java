import java.util.ArrayList;

public class Octopus {
    public static int k_minEnergy = 0;
    public static int k_maxEnergy = 9;
    public static int k_fakeOctopusEnergy = Integer.MIN_VALUE;

    private int m_x;
    private int m_y;
    private int m_energy;
    private Octopus m_topLeft;
    private Octopus m_top;
    private Octopus m_topRight;
    private Octopus m_right;
    private Octopus m_bottomRight;
    private Octopus m_bottom;
    private Octopus m_bottomLeft;
    private Octopus m_left;

    public Octopus() {
        m_x = -1;
        m_y = -1;
        m_energy = k_fakeOctopusEnergy;
        m_topLeft = null;
        m_top = null;
        m_topRight = null;
        m_right = null;
        m_bottomRight = null;
        m_bottom = null;
        m_bottomLeft = null;
        m_left = null;
    }

    public Octopus(int x, int y, int energy) {
        m_x = x;
        m_y = y;
        m_energy = energy;
        m_topLeft = null;
        m_top = null;
        m_topRight = null;
        m_right = null;
        m_bottomRight = null;
        m_bottom = null;
        m_bottomLeft = null;
        m_left = null;
    }

    public Octopus(int x, int y, int energy, Octopus topLeft, Octopus top, Octopus topRight, Octopus right,
            Octopus bottomRight, Octopus bottom, Octopus bottomLeft, Octopus left) {
        m_x = x;
        m_y = y;
        m_energy = energy;
        m_topLeft = topLeft;
        m_top = top;
        m_topRight = topRight;
        m_right = right;
        m_bottomRight = bottomRight;
        m_bottom = bottom;
        m_bottomLeft = bottomLeft;
        m_left = left;
    }

    public int getX() {
        return m_x;
    }

    public int getY() {
        return m_y;
    }

    public int getEnergy() {
        return m_energy;
    }

    public Octopus getTopLeft() {
        return m_topLeft;
    }

    public Octopus getTop() {
        return m_top;
    }

    public Octopus getTopRight() {
        return m_topRight;
    }

    public Octopus getRight() {
        return m_right;
    }

    public Octopus getBottomRight() {
        return m_bottomRight;
    }

    public Octopus getBottom() {
        return m_bottom;
    }

    public Octopus getBottomLeft() {
        return m_bottomLeft;
    }

    public Octopus getLeft() {
        return m_left;
    }

    public void setTopLeft(Octopus topLeft) {
        m_topLeft = topLeft;
    }

    public void setTop(Octopus top) {
        m_top = top;
    }

    public void setTopRight(Octopus topRight) {
        m_topRight = topRight;
    }

    public void setRight(Octopus right) {
        m_right = right;
    }

    public void setBottomRight(Octopus bottomRight) {
        m_bottomRight = bottomRight;
    }

    public void setBottom(Octopus bottom) {
        m_bottom = bottom;
    }

    public void setBottomLeft(Octopus bottomLeft) {
        m_bottomLeft = bottomLeft;
    }

    public void setLeft(Octopus left) {
        m_left = left;
    }
}
