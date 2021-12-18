import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;

public class Day14 {
    public static void main(String[] args) {
        Polymerizer polymerizer;

        if (args.length >= 2) {
            polymerizer = parseDataFile(args[1]);
        } else {
            polymerizer = parseDataFile("Day14/Day14_Input.csv");
        }

        System.out.println("Part 1 Answer: " + part1(polymerizer, 10));

        if (args.length >= 2) {
            polymerizer = parseDataFile(args[1]);
        } else {
            polymerizer = parseDataFile("Day14/Day14_Input.csv");
        }

        System.out.println("Part 2 Answer: " + part2(polymerizer, 40));
    }

    static public Polymerizer parseDataFile(String filePath) {
        Polymerizer polymerizer = new Polymerizer();

        try {
            Scanner scanner = new Scanner(new File(filePath));

            if (scanner.hasNextLine()) {
                String polyerTemplateString = scanner.nextLine();
                for (char element : polyerTemplateString.toCharArray()) {
                    polymerizer.getElementsList().add(Character.valueOf(element));
                }
            }

            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                PairInsertionRule rule = new PairInsertionRule(scanner.nextLine());
                polymerizer.m_rules.add(rule);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return polymerizer;
    }

    static public long part1(Polymerizer polymerizer, int count) {
        for (int i = 0; i < count; i++) {
            polymerizer.polymerize();
        }

        return polymerizer.getPart1Solution();
    }

    static public long part2(Polymerizer polymerizer, int count) {
        ElementTotals totals = polymerizer.polymerize(count);

        return totals.getMostCommonCount() - totals.getLeastCommonCount();
    }
}
