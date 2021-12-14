import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day10 {
    public static void main(String[] args) {
        ArrayList<CodeLine> lineList;

        if (args.length >= 2) {
            lineList = parseDataFile(args[1]);
        } else {
            lineList = parseDataFile("Day10/Day10_Input.csv");
        }

        System.out.println("Part 1 Answer: " + part1(lineList));
        System.out.println("Part 2 Answer: " + part2(lineList));
    }

    static public ArrayList<CodeLine> parseDataFile(String filePath) {
        ArrayList<CodeLine> lineList = new ArrayList<CodeLine>();

        try {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNextLine()) {
                lineList.add(new CodeLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return lineList;
    }

    /**
     * Part 1 of Day 10 Challenge.
     * 
     * @param lineList List of code line objects containing the parsed data from the
     *                 input file.
     * @return The answer for part 1.
     */
    static public int part1(ArrayList<CodeLine> lineList) {
        int total = 0;

        for (CodeLine line : lineList) {
            CodeLine.Bracket returnedResult = line.findCurroptedBracket();
            if (returnedResult != null) {
                total += returnedResult.getCorruptedScore();
            }
        }

        return total;
    }

    /**
     * Part 2 of Day 10 Challenge.
     * 
     * @param lineList List of code line objects containing the parsed data from the
     *                 input file.
     * @return The answer for part 2.
     */
    static public long part2(ArrayList<CodeLine> lineList) {
        ArrayList<Long> totals = new ArrayList<Long>();

        for (CodeLine line : lineList) {
            CodeLine.Bracket returnedResult = line.findCurroptedBracket();
            if (returnedResult == null) {
                ArrayList<CodeLine.Bracket> missingBrackets = line.findMissingBrackets();

                long total = 0;
                for (CodeLine.Bracket bracket : missingBrackets) {
                    total *= 5;
                    total += bracket.getIncompleteScore();
                }

                totals.add(Long.valueOf(total));
            }
        }

        Collections.sort(totals);

        int index = Math.floorDiv(totals.size(), 2);
        return totals.get(index);
    }
}
