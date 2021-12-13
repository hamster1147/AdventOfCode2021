import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day8 {

    private static class ParseResult {
        public SevenSegmentGroup m_definition;
        public SevenSegmentGroup m_solution;

        public ParseResult(SevenSegmentGroup definition, SevenSegmentGroup solution) {
            m_definition = definition;
            m_solution = solution;
        }
    }

    public static void main(String[] args) {
        ArrayList<ParseResult> parsedResult;

        if (args.length >= 2) {
            parsedResult = parseDataFile(args[1]);
        } else {
            parsedResult = parseDataFile("Day8/Day8_Input.csv");
        }

        System.out.println("Part 1 Answer: " + part1(parsedResult));
        System.out.println("Part 2 Answer: " + part2(parsedResult));
    }

    static public ArrayList<ParseResult> parseDataFile(String filePath) {
        ArrayList<ParseResult> parsedData = new ArrayList<ParseResult>();

        try {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNextLine()) {
                SevenSegmentGroup definition = new SevenSegmentGroup();
                SevenSegmentGroup solution = new SevenSegmentGroup();

                String definitionSolutionSplit[] = scanner.nextLine().split(" \\| ");
                for (String segmentString : definitionSolutionSplit[0].split(" ")) {
                    SevenSegment newSevenSegment = new SevenSegment(segmentString);
                    definition.getList().add(newSevenSegment);
                }

                for (String segmentString : definitionSolutionSplit[1].split(" ")) {
                    SevenSegment newSevenSegment = new SevenSegment(segmentString);
                    solution.getList().add(newSevenSegment);
                }

                parsedData.add(new ParseResult(definition, solution));
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return parsedData;
    }

    /**
     * Part 1 of Day 8 Challenge.
     * 
     * @param parsedResult Object containing the parsed data from the input file.
     * @return The answer for part 1.
     */
    static public int part1(ArrayList<ParseResult> parsedResultList) {
        int total = 0;

        for (ParseResult parsedResult : parsedResultList) {
            ArrayList<SegmentTranslate> segmentTranslateTable = parsedResult.m_definition.solveForTranslates();
            SevenSegmentGroup translatedResult = parsedResult.m_solution.translateResult(segmentTranslateTable);

            for (SevenSegment sevenSegmentResult : translatedResult.getList()) {
                // System.out.println(sevenSegmentResult.isValidDigit() + " - " +
                // sevenSegmentResult.getDigitValue());
                int digit = sevenSegmentResult.getDigitValue();
                if (digit == 1 || digit == 4 || digit == 7 || digit == 8) {
                    total++;
                }
            }
        }

        return total;
    }

    /**
     * Part 2 of Day 8 Challenge.
     * 
     * @param parsedResult Object containing the parsed data from the input file.
     * @return The answer for part 2.
     */
    static public int part2(ArrayList<ParseResult> parsedResultList) {
        int total = 0;

        for (ParseResult parsedResult : parsedResultList) {
            ArrayList<SegmentTranslate> segmentTranslateTable = parsedResult.m_definition.solveForTranslates();
            SevenSegmentGroup translatedResult = parsedResult.m_solution.translateResult(segmentTranslateTable);

            total += translatedResult.getList().get(0).getDigitValue() * 1000;
            total += translatedResult.getList().get(1).getDigitValue() * 100;
            total += translatedResult.getList().get(2).getDigitValue() * 10;
            total += translatedResult.getList().get(3).getDigitValue() * 1;
        }

        return total;
    }
}
