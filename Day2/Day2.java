import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Position {
    public int m_x;
    public int m_depth;

    public Position(int x, int depth) {
        m_x = x;
        m_depth = depth;
    }

    public Position() {
        this(0, 0);
    }

    public void add(Position other) {
        m_x += other.m_x;
        m_depth += other.m_depth;
    }
}

class Day2 {
    static private String kForward = "forward";
    static private String kDown = "down";
    static private String kUp = "up";

    public static void main(String[] args) {
        ArrayList<Position> data;

        if (args.length >= 2) {
            data = parseDataFile(args[1]);
        } else {
            data = parseDataFile("Day2/Day2_Input.csv");
        }

        System.out.println("Part 1 Answer: " + part1(data));
    }

    static public ArrayList<Position> parseDataFile(String filePath) {
        ArrayList<Position> data = new ArrayList<Position>();
        String line;

        try {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNext()) {
                line = scanner.nextLine();

                String[] textItems = line.split(" ");

                if (textItems[0].equalsIgnoreCase(kForward)) {
                    data.add(new Position(Integer.parseInt(textItems[1]), 0));
                } else if (textItems[0].equalsIgnoreCase(kDown)) {
                    data.add(new Position(0, Integer.parseInt(textItems[1])));
                } else if (textItems[0].equalsIgnoreCase(kUp)) {
                    data.add(new Position(0, -Integer.parseInt(textItems[1])));
                } else {
                    data.add(new Position());
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return data;
    }

    /**
     * Part 1 of Day 2 Challenge.
     * 
     * @param data List of int values parsed from input file.
     * @return The answer for part 1.
     */
    static public int part1(ArrayList<Position> data) {
        Position pos = new Position();

        for (Position value : data) {
            pos.add(value);
        }

        return pos.m_x * pos.m_depth;
    }
}