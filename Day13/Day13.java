import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day13 {
    public static void main(String[] args) {
        Paper paper;

        if (args.length >= 2) {
            paper = parseDataFile(args[1]);
        } else {
            paper = parseDataFile("Day13/Day13_Input.csv");
        }

        System.out.println("Part 1 Answer: " + part1(paper));
    }

    static public Paper parseDataFile(String filePath) {
        Paper paper = new Paper();

        try {
            Scanner scanner = new Scanner(new File(filePath));

            boolean foundFolds = false;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (foundFolds == false) {
                    if (line.length() == 0) {
                        foundFolds = true;
                    } else {
                        PaperMark mark = new PaperMark(line);
                        paper.getMarkingList().add(mark);
                    }
                } else {
                    PaperFold fold = new PaperFold(line);
                    paper.getFoldsList().add(fold);
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return paper;
    }

    static public int part1(Paper paper) {
        paper.fold(paper.getFoldsList().get(0));
        return paper.getMarkingList().size();
    }
}
