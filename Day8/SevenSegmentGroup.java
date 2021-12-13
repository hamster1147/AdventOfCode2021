import java.util.ArrayList;

public class SevenSegmentGroup {
    private ArrayList<SevenSegment> m_list;

    public SevenSegmentGroup() {
        m_list = new ArrayList<SevenSegment>();
    }

    public ArrayList<SevenSegment> getList() {
        return m_list;
    }

    public double percentSegment(Segment segment) {
        int count = 0;

        for (SevenSegment sevenSegment : m_list) {
            if (sevenSegment.getSegment(segment)) {
                count++;
            }
        }

        return (double) count / (7.0 * m_list.size());
    }

    public ArrayList<SegmentTranslate> solveForTranslates() {
        ArrayList<SegmentTranslate> result = new ArrayList<SegmentTranslate>();
        boolean found = false;

        // Find B since it is easy
        SegmentTranslate translateB = new SegmentTranslate(Segment.B);
        for (Segment segment : Segment.values()) {
            if (Math.abs(percentSegment(segment) - Segment.B.getProbability()) < 1.0e-9) {
                if (found) {
                    System.out.println("B found more then once.");
                }

                translateB.setFrom(segment);
                found = true;
            }
        }

        // Find E since it is easy
        found = false;
        SegmentTranslate translateE = new SegmentTranslate(Segment.E);
        for (Segment segment : Segment.values()) {
            if (Math.abs(percentSegment(segment) - Segment.E.getProbability()) < 1.0e-9) {
                if (found) {
                    System.out.println("E found more then once.");
                }

                translateE.setFrom(segment);
                found = true;
            }
        }

        // Find F since it is easy
        found = false;
        SegmentTranslate translateF = new SegmentTranslate(Segment.F);
        for (Segment segment : Segment.values()) {
            if (Math.abs(percentSegment(segment) - Segment.F.getProbability()) < 1.0e-9) {
                if (found) {
                    System.out.println("F found more then once.");
                }

                translateF.setFrom(segment);
                found = true;
            }
        }

        // Figure out what C is from finding the 1 digit and knowing what F is already
        SegmentTranslate translateC = new SegmentTranslate(Segment.C);
        for (SevenSegment sevenSegment : m_list) {
            // Find the 1 digit. It only has two segments on
            if (sevenSegment.totalSegmentsOn() == 2) {
                ArrayList<Segment> onSegments = sevenSegment.getListOfOnSegments();

                for (Segment segment : onSegments) {
                    if (segment != translateF.getFrom()) {
                        translateC.setFrom(segment);
                    }
                }
            }
        }

        // Figure out what A is since we now know what C was
        found = false;
        SegmentTranslate translateA = new SegmentTranslate(Segment.A);
        for (Segment segment : Segment.values()) {
            if (Math.abs(percentSegment(segment) - Segment.A.getProbability()) < 1.0e-9
                    && translateC.getFrom() != segment) {
                if (found) {
                    System.out.println("A found more then once.");
                }

                translateA.setFrom(segment);
                found = true;
            }
        }

        // Now that we know B, C and F, we can figure out D from the 4 digit
        SegmentTranslate translateD = new SegmentTranslate(Segment.D);
        for (SevenSegment sevenSegment : m_list) {
            // Find the 1 digit. It only has two segments on
            if (sevenSegment.totalSegmentsOn() == 4) {
                ArrayList<Segment> onSegments = sevenSegment.getListOfOnSegments();

                for (Segment segment : onSegments) {
                    if (segment != translateB.getFrom() &&
                            segment != translateC.getFrom() &&
                            segment != translateF.getFrom()) {
                        translateD.setFrom(segment);
                    }
                }
            }
        }

        // Figure out what G is since we now know what D was
        found = false;
        SegmentTranslate translateG = new SegmentTranslate(Segment.G);
        for (Segment segment : Segment.values()) {
            if (Math.abs(percentSegment(segment) - Segment.G.getProbability()) < 1.0e-9
                    && translateD.getFrom() != segment) {
                if (found) {
                    System.out.println("G found more then once.");
                }

                translateG.setFrom(segment);
                found = true;
            }
        }

        result.add(translateA);
        result.add(translateB);
        result.add(translateC);
        result.add(translateD);
        result.add(translateE);
        result.add(translateF);
        result.add(translateG);

        return result;
    }

    public SevenSegmentGroup translateResult(ArrayList<SegmentTranslate> segmentTranlateTable) {
        SevenSegmentGroup result = new SevenSegmentGroup();

        for (SevenSegment sevenSegment : m_list) {
            SevenSegment translatedSevenSegment = new SevenSegment();

            for (SegmentTranslate translate : segmentTranlateTable) {
                translatedSevenSegment.setSegment(translate.getTo(), sevenSegment.getSegment(translate.getFrom()));
            }

            result.m_list.add(translatedSevenSegment);
        }

        return result;
    }

    public static SevenSegmentGroup coreDigits() {
        SevenSegmentGroup group = new SevenSegmentGroup();

        group.getList().add(new SevenSegment(0));
        group.getList().add(new SevenSegment(1));
        group.getList().add(new SevenSegment(2));
        group.getList().add(new SevenSegment(3));
        group.getList().add(new SevenSegment(4));
        group.getList().add(new SevenSegment(5));
        group.getList().add(new SevenSegment(6));
        group.getList().add(new SevenSegment(7));
        group.getList().add(new SevenSegment(8));
        group.getList().add(new SevenSegment(9));

        return group;
    }

    public static void printCorePercent() {
        SevenSegmentGroup group = coreDigits();

        System.out.println("A: " + group.percentSegment(Segment.A));
        System.out.println("B: " + group.percentSegment(Segment.B));
        System.out.println("C: " + group.percentSegment(Segment.C));
        System.out.println("D: " + group.percentSegment(Segment.D));
        System.out.println("E: " + group.percentSegment(Segment.E));
        System.out.println("F: " + group.percentSegment(Segment.F));
        System.out.println("G: " + group.percentSegment(Segment.G));
    }
}
