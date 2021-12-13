import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Day9 {
    public static void main(String[] args) {
        Cave cave;

        if (args.length >= 2) {
            cave = parseDataFile(args[1]);
        } else {
            cave = parseDataFile("Day9/Day9_Input.csv");
        }

        System.out.println("Part 1 Answer: " + part1(cave));
        System.out.println("Part 2 Answer: " + part2(cave));
    }

    static public Cave parseDataFile(String filePath) {
        Cave cave = new Cave();

        try {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNextLine()) {
                cave.addRowString(scanner.nextLine());
            }

            cave.populateCavePointReferences();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return cave;
    }

    /**
     * Part 1 of Day 9 Challenge.
     * 
     * @param cave Object containing the parsed data from the input file.
     * @return The answer for part 1.
     */
    static public int part1(Cave cave) {
        int total = 0;

        for (CavePoint cavePoint : cave.getListOfLowestPoints()) {
            total += cavePoint.getHeight() + 1;
        }

        return total;
    }

    /**
     * Part 2 of Day 9 Challenge.
     * 
     * @param cave Object containing the parsed data from the input file.
     * @return The answer for part 2.
     */
    static public int part2(Cave cave) {
        ArrayList<Integer> sizeList = new ArrayList<Integer>();

        for (ArrayList<CavePoint> basin : cave.getListOfBasins()) {
            sizeList.add(basin.size());
        }

        Collections.sort(sizeList);
        int size = sizeList.size();

        return sizeList.get(size - 1) * sizeList.get(size - 2) * sizeList.get(size - 3);
    }
}
