public enum Segment {
    A(0, 0.11428571428571428),
    B(1, 0.08571428571428572),
    C(2, 0.11428571428571428),
    D(3, 0.1),
    E(4, 0.05714285714285714),
    F(5, 0.12857142857142856),
    G(6, 0.1);

    private int m_value;
    private double m_probability;

    private Segment(int value, double probability) {
        m_value = value;
        m_probability = probability;
    }

    public int getValue() {
        return m_value;
    }

    public double getProbability() {
        return m_probability;
    }
}
