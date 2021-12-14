import java.io.File;
import java.io.FileNotFoundException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map.Entry;

public class Day5 {
    public static void main(String[] args) {
        ArrayList<Line> lineArray;

        if (args.length >= 2) {
            lineArray = parseDataFile(args[1]);
        } else {
            lineArray = parseDataFile("Day5/Day5_Input.csv");
        }

        System.out.println("Part 1 Answer: " + part1(lineArray, false));
        System.out.println("Part 2 Answer: " + part1(lineArray, true));
    }

    static public ArrayList<Line> parseDataFile(String filePath) {
        ArrayList<Line> lineArray = new ArrayList<Line>();

        try {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNext()) {
                lineArray.add(new Line(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return lineArray;
    }

    /**
     * Part 1 of Day 4 Challenge. Also part 2 if part2 is set to true.
     * 
     * @param lineArray Object filled with bingo numbers and bingo boards.
     * @return The answer for part 1, or part 2 is part2 is true.
     */
    static public int part1(ArrayList<Line> lineArray, boolean part2) {
        ArrayList<Entry<Coordinate, Integer>> intersectionTally = new ArrayList<Entry<Coordinate, Integer>>();

        int i = 0;
        for (Line lineA : lineArray) {
            List<Line> subList = lineArray.subList(i, lineArray.size());
            for (Line lineB : subList) {
                if (lineA != lineB) {
                    ArrayList<Coordinate> intersections = Line.getIntersectingCoordinates(lineA, lineB, part2);

                    if (intersections.size() > 0) {
                        for (Coordinate newIntersection : intersections) {
                            boolean foundMatch = false;
                            for (Entry<Coordinate, Integer> tally : intersectionTally) {
                                if (tally.getKey().equals(newIntersection)) {
                                    tally.setValue(Integer.valueOf(tally.getValue() + 1));
                                    foundMatch = true;
                                    break;
                                }
                            }

                            if (!foundMatch) {
                                Entry<Coordinate, Integer> entry = new AbstractMap.SimpleEntry<Coordinate, Integer>(
                                        newIntersection, 1);
                                intersectionTally.add(entry);
                            }
                        }
                    }
                }
            }
            i++;
        }

        int result = 0;
        for (Entry<Coordinate, Integer> entry : intersectionTally) {
            if (entry.getValue() >= 1) {
                result++;
            }
        }

        return result;
    }
}
