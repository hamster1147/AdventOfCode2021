public class SegmentTranslate {
    private Segment m_from;
    private Segment m_to;

    public SegmentTranslate(Segment from, Segment to) {
        m_from = from;
        m_to = to;
    }

    public SegmentTranslate(Segment segment) {
        m_from = m_to = segment;
    }

    public Segment getFrom() {
        return m_from;
    }

    public void setFrom(Segment from) {
        m_from = from;
    }

    public Segment getTo() {
        return m_to;
    }

    public void setTo(Segment to) {
        m_to = to;
    }
}
