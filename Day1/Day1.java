import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Day1 {
    public static void main(String[] args) {
        ArrayList<Integer> data;

        if (args.length >= 2) {
            data = parseDataFile(args[1]);
        } else {
            data = parseDataFile("Day1/Day1_Input.csv");
        }

        System.out.println("Part 1 Answer: " + part1(data));
        System.out.println("Part 2 Answer: " + part2(data));
    }

    static public ArrayList<Integer> parseDataFile(String filePath) {
        ArrayList<Integer> data = new ArrayList<Integer>();

        try {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNextInt()) {
                data.add(Integer.parseInt(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return data;
    }

    /**
     * Part 1 of Day 1 Challenge.
     * 
     * @param data List of int values parsed from input file.
     * @return The answer for part 1.
     */
    static public int part1(ArrayList<Integer> data) {
        boolean started = false;
        int prevValue = 0;
        int total = 0;

        for (Integer value : data) {
            if (started && (value.intValue() > prevValue)) {
                total++;
            }

            prevValue = value.intValue();
            started = true;
        }

        return total;
    }

    /**
     * Part 2 of Day 1 Challenge.
     * 
     * @param data List of int values parsed from input file.
     * @return The answer for part 1.
     */
    static public int part2(ArrayList<Integer> data) {
        int total = 0;
        ArrayList<Integer> history = new ArrayList<Integer>();

        for (Integer value : data) {
            history.add(value);

            // Check if the list has too many items
            if (history.size() > 4) {
                history.remove(0);
            }

            if (history.size() == 4) {
                int commonSum = history.get(1) + history.get(2);
                // New values are appended, so older values are near the beginning
                int prevSum = commonSum + history.get(0);
                int currSum = commonSum + history.get(3);

                if (currSum > prevSum) {
                    total++;
                }
            }
        }

        return total;
    }
}