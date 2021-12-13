import java.util.ArrayList;

public class SevenSegment {
    // 0 ABC-EFG 6
    // 1 --C--F- 2
    // 2 A-CDE-G 6
    // 3 A-CD-FG 5
    // 4 -BCD-F- 4
    // 5 AB-D-FG 5
    // 6 AB-DEFG 6
    // 7 A-C--F- 3
    // 8 AVCDEFG 7
    // 9 ABCD-FG 6

    // Required Segments Based on Total Segments
    // * means some digits had segment
    // 2 --C--F-
    // 3 A-C--F-
    // 4 -BCD-F-
    // 5 A**D*FG
    // 6 A*****G
    // 7 ABCDEFG

    /*
     * Probability of each segment in a core group of digits
     * A: 0.11428571428571428
     * B: 0.08571428571428572
     * C: 0.11428571428571428
     * D: 0.1
     * E: 0.05714285714285714
     * F: 0.12857142857142856
     * G: 0.1
     */

    // Thought process on how to figure out which digit is which.

    // Since A and C are the same chance, and F is unique, we can figure out which
    // is C knowing that a 1 digit is C and F.
    // The F segment would be known, so the remaining segment has to be the C
    // segment. A would be known after figuring this out.

    // Now that we know C, and since B and F have unique probability, we can figure
    // out which is D versus G by looking at digit 4.

    private boolean m_segments[] = new boolean[7];

    public SevenSegment(int num) {
        switch (num) {
            case 0:
                m_segments[Segment.A.getValue()] = true;
                m_segments[Segment.B.getValue()] = true;
                m_segments[Segment.C.getValue()] = true;
                m_segments[Segment.D.getValue()] = false;
                m_segments[Segment.E.getValue()] = true;
                m_segments[Segment.F.getValue()] = true;
                m_segments[Segment.G.getValue()] = true;
                break;
            case 1:
                m_segments[Segment.A.getValue()] = false;
                m_segments[Segment.B.getValue()] = false;
                m_segments[Segment.C.getValue()] = true;
                m_segments[Segment.D.getValue()] = false;
                m_segments[Segment.E.getValue()] = false;
                m_segments[Segment.F.getValue()] = true;
                m_segments[Segment.G.getValue()] = false;
                break;
            case 2:
                m_segments[Segment.A.getValue()] = true;
                m_segments[Segment.B.getValue()] = false;
                m_segments[Segment.C.getValue()] = true;
                m_segments[Segment.D.getValue()] = true;
                m_segments[Segment.E.getValue()] = true;
                m_segments[Segment.F.getValue()] = false;
                m_segments[Segment.G.getValue()] = true;
                break;
            case 3:
                m_segments[Segment.A.getValue()] = true;
                m_segments[Segment.B.getValue()] = false;
                m_segments[Segment.C.getValue()] = true;
                m_segments[Segment.D.getValue()] = true;
                m_segments[Segment.E.getValue()] = false;
                m_segments[Segment.F.getValue()] = true;
                m_segments[Segment.G.getValue()] = true;
                break;
            case 4:
                m_segments[Segment.A.getValue()] = false;
                m_segments[Segment.B.getValue()] = true;
                m_segments[Segment.C.getValue()] = true;
                m_segments[Segment.D.getValue()] = true;
                m_segments[Segment.E.getValue()] = false;
                m_segments[Segment.F.getValue()] = true;
                m_segments[Segment.G.getValue()] = false;
                break;
            case 5:
                m_segments[Segment.A.getValue()] = true;
                m_segments[Segment.B.getValue()] = true;
                m_segments[Segment.C.getValue()] = false;
                m_segments[Segment.D.getValue()] = true;
                m_segments[Segment.E.getValue()] = false;
                m_segments[Segment.F.getValue()] = true;
                m_segments[Segment.G.getValue()] = true;
                break;
            case 6:
                m_segments[Segment.A.getValue()] = true;
                m_segments[Segment.B.getValue()] = true;
                m_segments[Segment.C.getValue()] = false;
                m_segments[Segment.D.getValue()] = true;
                m_segments[Segment.E.getValue()] = true;
                m_segments[Segment.F.getValue()] = true;
                m_segments[Segment.G.getValue()] = true;
                break;
            case 7:
                m_segments[Segment.A.getValue()] = true;
                m_segments[Segment.B.getValue()] = false;
                m_segments[Segment.C.getValue()] = true;
                m_segments[Segment.D.getValue()] = false;
                m_segments[Segment.E.getValue()] = false;
                m_segments[Segment.F.getValue()] = true;
                m_segments[Segment.G.getValue()] = false;
                break;
            case 8:
                m_segments[Segment.A.getValue()] = true;
                m_segments[Segment.B.getValue()] = true;
                m_segments[Segment.C.getValue()] = true;
                m_segments[Segment.D.getValue()] = true;
                m_segments[Segment.E.getValue()] = true;
                m_segments[Segment.F.getValue()] = true;
                m_segments[Segment.G.getValue()] = true;
                break;
            case 9:
                m_segments[Segment.A.getValue()] = true;
                m_segments[Segment.B.getValue()] = true;
                m_segments[Segment.C.getValue()] = true;
                m_segments[Segment.D.getValue()] = true;
                m_segments[Segment.E.getValue()] = false;
                m_segments[Segment.F.getValue()] = true;
                m_segments[Segment.G.getValue()] = true;
                break;
        }
    }

    public SevenSegment() {
        m_segments[Segment.A.getValue()] = false;
        m_segments[Segment.B.getValue()] = false;
        m_segments[Segment.C.getValue()] = false;
        m_segments[Segment.D.getValue()] = false;
        m_segments[Segment.E.getValue()] = false;
        m_segments[Segment.F.getValue()] = false;
        m_segments[Segment.G.getValue()] = false;
    }

    public SevenSegment(boolean a, boolean b, boolean c, boolean d, boolean e, boolean f, boolean g) {
        m_segments[Segment.A.getValue()] = a;
        m_segments[Segment.B.getValue()] = b;
        m_segments[Segment.C.getValue()] = c;
        m_segments[Segment.D.getValue()] = d;
        m_segments[Segment.E.getValue()] = e;
        m_segments[Segment.F.getValue()] = f;
        m_segments[Segment.G.getValue()] = g;
    }

    public SevenSegment(String sevenSegmentString) {
        // Set all to false first
        for (Segment segment : Segment.values()) {
            m_segments[segment.getValue()] = false;
        }

        for (char segmentChar : sevenSegmentString.toCharArray()) {
            Segment segment = segmentFromLetter(segmentChar);

            if (segment == null) {
                System.out.println("Null returned from " + segmentChar);
            } else {
                m_segments[segmentFromLetter(segmentChar).getValue()] = true;
            }
        }
    }

    public boolean equals(SevenSegment other) {
        return this.getSegment(Segment.A) == other.getSegment(Segment.A) &&
                this.getSegment(Segment.B) == other.getSegment(Segment.B) &&
                this.getSegment(Segment.C) == other.getSegment(Segment.C) &&
                this.getSegment(Segment.D) == other.getSegment(Segment.D) &&
                this.getSegment(Segment.E) == other.getSegment(Segment.E) &&
                this.getSegment(Segment.F) == other.getSegment(Segment.F) &&
                this.getSegment(Segment.G) == other.getSegment(Segment.G);
    }

    public boolean isValidDigit() {
        return this.equals(new SevenSegment(0)) ||
                this.equals(new SevenSegment(1)) ||
                this.equals(new SevenSegment(2)) ||
                this.equals(new SevenSegment(3)) ||
                this.equals(new SevenSegment(4)) ||
                this.equals(new SevenSegment(5)) ||
                this.equals(new SevenSegment(6)) ||
                this.equals(new SevenSegment(7)) ||
                this.equals(new SevenSegment(8)) ||
                this.equals(new SevenSegment(9));
    }

    public int getDigitValue() {
        if (this.equals(new SevenSegment(0))) {
            return 0;
        } else if (this.equals(new SevenSegment(1))) {
            return 1;
        } else if (this.equals(new SevenSegment(2))) {
            return 2;
        } else if (this.equals(new SevenSegment(3))) {
            return 3;
        } else if (this.equals(new SevenSegment(4))) {
            return 4;
        } else if (this.equals(new SevenSegment(5))) {
            return 5;
        } else if (this.equals(new SevenSegment(6))) {
            return 6;
        } else if (this.equals(new SevenSegment(7))) {
            return 7;
        } else if (this.equals(new SevenSegment(8))) {
            return 8;
        } else if (this.equals(new SevenSegment(9))) {
            return 9;
        } else {
            return -1;
        }
    }

    public boolean getSegment(Segment segment) {
        return m_segments[segment.getValue()];
    }

    public void setSegment(Segment segment, boolean state) {
        m_segments[segment.getValue()] = state;
    }

    // public boolean getSegment(int index) {
    // return m_segments[index];
    // }

    public int totalSegmentsOn() {
        int total = 0;

        for (Segment segment : Segment.values()) {
            if (getSegment(segment)) {
                total++;
            }
        }

        return total;
    }

    public ArrayList<Segment> getListOfOnSegments() {
        ArrayList<Segment> list = new ArrayList<Segment>();

        for (Segment segment : Segment.values()) {
            if (getSegment(segment)) {
                list.add(segment);
            }
        }

        return list;
    }

    public static Segment segmentFromLetter(char segmentLetter) {
        if (segmentLetter == 'A' || segmentLetter == 'a') {
            return Segment.A;
        } else if (segmentLetter == 'B' || segmentLetter == 'b') {
            return Segment.B;
        } else if (segmentLetter == 'C' || segmentLetter == 'c') {
            return Segment.C;
        } else if (segmentLetter == 'D' || segmentLetter == 'd') {
            return Segment.D;
        } else if (segmentLetter == 'E' || segmentLetter == 'e') {
            return Segment.E;
        } else if (segmentLetter == 'F' || segmentLetter == 'f') {
            return Segment.F;
        } else if (segmentLetter == 'G' || segmentLetter == 'g') {
            return Segment.G;
        } else {
            return null;
        }
    }
}
