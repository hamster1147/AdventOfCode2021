import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Day6 {
    public static void main(String[] args) {
        ArrayList<Lanternfish> fish;

        if (args.length >= 2) {
            fish = parseDataFile(args[1]);
        } else {
            fish = parseDataFile("Day6/Day6_Input.csv");
        }

        System.out.println("Part 1 Answer: " + part1(fish, 80));

        // Need to refresh the fish. I don't feel like setting up deep copies.
        fish.clear();

        if (args.length >= 2) {
            fish = parseDataFile(args[1]);
        } else {
            fish = parseDataFile("Day6/Day6_Input.csv");
        }

        System.out.println("Part 1b Answer: " + part2(fish, 256));
    }

    static public ArrayList<Lanternfish> parseDataFile(String filePath) {
        ArrayList<Lanternfish> fish = new ArrayList<Lanternfish>();

        try {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNext()) {
                String stringArray[] = scanner.nextLine().split(",");
                for (String str : stringArray) {
                    fish.add(new Lanternfish(Integer.parseInt(str)));
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return fish;
    }

    /**
     * Part 1 of Day 6 Challenge.
     * 
     * @param fish List of fish parsed from input file.
     * @return The answer for part 1.
     */
    static public int part1(ArrayList<Lanternfish> fish, int days) {
        for (int day = 1; day <= days; day++) {
            for (Lanternfish fishy : fish) {
                fishy.tick();
            }
        }

        int totalCount = 0;
        for (Lanternfish fishy : fish) {
            totalCount += fishy.sizeOfFamily();
        }

        return totalCount;
    }

    /**
     * Part 2 of Day 6 Challenge. Different way to approach counting fish kiddies.
     * 
     * @param fish List of fish parsed from input file.
     * @return The answer for part 1.
     */
    static public long part2(ArrayList<Lanternfish> fish, int days) {
        long totalCount = 0;
        long count[] = new long[5];

        // Generate the total counts for various ages
        for (int i = 0; i < 5; i++) {
            Lanternfish fishy = new Lanternfish(i + 1);

            count[i] = fishy.familySizeAfterDays(days);
            System.out.println(i + " - " + count[i]);
        }

        // count[0] = 1911853737;
        // count[1] = 1322121852;
        // count[2] = 922255946;
        // count[3] = 431133578;
        // count[4] = 73264713;

        for (Lanternfish fishy : fish) {
            totalCount += count[fishy.getAge() - 1];
        }

        return totalCount;
    }
}
