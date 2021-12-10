import java.io.File;
import java.io.FileNotFoundException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;

class Day7 {
    public static void main(String[] args) {
        ArrayList<Entry<Integer, Integer>> data;

        if (args.length >= 2) {
            data = parseDataFile(args[1]);
        } else {
            data = parseDataFile("Day7/Day7_Input.csv");
        }

        System.out.println("Part 1 Answer: " + part1(data));
        System.out.println("Part 2 Answer: " + part2(data));
    }

    static public ArrayList<Entry<Integer, Integer>> parseDataFile(String filePath) {
        ArrayList<Entry<Integer, Integer>> result = new ArrayList<Entry<Integer, Integer>>();

        try {
            ArrayList<Integer> list = new ArrayList<Integer>();
            Scanner scanner = new Scanner(new File(filePath));

            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (String numStr : line.split(",")) {
                    int num = Integer.valueOf(numStr);
                    max = Math.max(max, num);
                    min = Math.min(min, num);
                    list.add(num);
                }
            }

            for (int i = 0; i < max + min + 1; i++) {
                result.add(new AbstractMap.SimpleEntry<Integer, Integer>(i, 0));
            }

            for (Integer num : list) {
                int newTally = result.get(num + min).getValue() + 1;
                Entry<Integer, Integer> entry = new AbstractMap.SimpleEntry<Integer, Integer>(num, newTally);
                result.set(num + min, entry);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Part 1 of Day 7 Challenge.
     * 
     * @param data List of values parsed from input file.
     * @return The answer for part 1.
     */
    static public int part1(ArrayList<Entry<Integer, Integer>> data) {
        int minFuelNeeded = Integer.MAX_VALUE;

        for (int i = 1; i < data.size() - 1; i++) {
            List<Entry<Integer, Integer>> leftList = data.subList(0, i);
            List<Entry<Integer, Integer>> rightList = data.subList(i + 1, data.size());
            int leftListTotal = 0;
            int rightListTotal = 0;

            for (Entry<Integer, Integer> entry : leftList) {
                int x = Math.abs(i - entry.getKey());
                leftListTotal += entry.getValue() * x;
            }

            for (Entry<Integer, Integer> entry : rightList) {
                int x = Math.abs(i - entry.getKey());
                rightListTotal += entry.getValue() * x;
            }

            int fuelNeeded = leftListTotal + rightListTotal;
            if (fuelNeeded < minFuelNeeded) {
                minFuelNeeded = fuelNeeded;
            }
        }

        return minFuelNeeded;
    }

    /**
     * Part 2 of Day 7 Challenge.
     * 
     * @param data List of values parsed from input file.
     * @return The answer for part 2.
     */
    static public int part2(ArrayList<Entry<Integer, Integer>> data) {
        int minFuelNeeded = Integer.MAX_VALUE;

        for (int i = 1; i < data.size() - 1; i++) {
            List<Entry<Integer, Integer>> leftList = data.subList(0, i);
            List<Entry<Integer, Integer>> rightList = data.subList(i + 1, data.size());
            int leftListTotal = 0;
            int rightListTotal = 0;

            for (Entry<Integer, Integer> entry : leftList) {
                int x = Math.abs(i - entry.getKey());
                leftListTotal += entry.getValue() * (x * (x + 1) / 2);
            }

            for (Entry<Integer, Integer> entry : rightList) {
                int x = Math.abs(i - entry.getKey());
                rightListTotal += entry.getValue() * (x * (x + 1) / 2);
            }

            int fuelNeeded = leftListTotal + rightListTotal;
            if (fuelNeeded < minFuelNeeded) {
                minFuelNeeded = fuelNeeded;
            }
        }

        return minFuelNeeded;
    }
}