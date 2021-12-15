import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day11 {
    public static void main(String[] args) {
        OctopusGrid grid;

        if (args.length >= 2) {
            grid = parseDataFile(args[1]);
        } else {
            grid = parseDataFile("Day11/Day11_Input.csv");
        }

        System.out.println("Part 1 Answer: " + part1(grid, 100));

        if (args.length >= 2) {
            grid = parseDataFile(args[1]);
        } else {
            grid = parseDataFile("Day11/Day11_Input.csv");
        }

        System.out.println("Part 2 Answer: " + part2(grid));
    }

    static public OctopusGrid parseDataFile(String filePath) {
        OctopusGrid grid = new OctopusGrid();

        try {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNextLine()) {
                grid.addRowString(scanner.nextLine());
            }

            grid.populateOctopusReferences();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return grid;
    }

    /**
     * Part 1 of Day 11 Challenge.
     * 
     * @param grid       Object containing the parsed data from the input file.
     * @param totalSteps Total steps to step the grid of octopuses by.
     * @return The answer for part 1.
     */
    static public int part1(OctopusGrid grid, int totalSteps) {
        int total = 0;

        for (int i = 0; i < totalSteps; i++) {
            total += grid.step();
        }

        return total;
    }

    /**
     * Part 2 of Day 11 Challenge.
     * 
     * @param cave Object containing the parsed data from the input file.
     * @return The answer for part 2.
     */
    static public int part2(OctopusGrid grid) {
        int step = 0;

        while (!grid.getAllFlashed()) {
            grid.step();
            step++;
        }

        return step;
    }
}
