import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day4 {
    public static void main(String[] args) {
        ParsedCsvResult parsedCsvResult;

        if (args.length >= 2) {
            parsedCsvResult = parseDataFile(args[1]);
        } else {
            parsedCsvResult = parseDataFile("Day4/Day4_Input.csv");
        }

        System.out.println("Part 1 Answer: " + part1(parsedCsvResult));
    }

    static public ParsedCsvResult parseDataFile(String filePath) {
        ParsedCsvResult parsedCsvResult = new ParsedCsvResult();

        try {
            Scanner scanner = new Scanner(new File(filePath));

            // Parse bingo numbers
            for (String bingoNumber : scanner.nextLine().split(",")) {
                parsedCsvResult.m_bingoNumbers.add(Integer.parseInt(bingoNumber));
            }

            // Parse bingo boards
            while (scanner.hasNext()) {
                String emptyLine = scanner.nextLine();
                BingoBoard board = new BingoBoard();
                parsedCsvResult.m_bingoBoards.add(board);

                for (int y = 0; y < BingoBoard.k_boardSize; y++) {
                    String boardLocationRow[] = scanner.nextLine().trim().split("\\s+");
                    for (int x = 0; x < BingoBoard.k_boardSize; x++) {
                        board.setBoardLocation(x, y, Integer.parseInt(boardLocationRow[x]));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return parsedCsvResult;
    }

    /**
     * Part 1 of Day 1 Challenge.
     * 
     * @param data List of int values parsed from input file.
     * @return The answer for part 1.
     */
    static public int part1(ParsedCsvResult parsedCsvResult) {
        BingoBoard winningBingoBoard = null;

        for (int bingoNumber : parsedCsvResult.m_bingoNumbers) {
            for (BingoBoard bingoBoard : parsedCsvResult.m_bingoBoards) {
                bingoBoard.checkAndMarkNumber(bingoNumber);

                if (bingoBoard.isWinningBoard()) {
                    winningBingoBoard = bingoBoard;
                }
            }

            if (winningBingoBoard != null) {
                return winningBingoBoard.calculatePart1Solution(bingoNumber);
            }
        }

        return -1;
    }

    static class ParsedCsvResult {
        public ArrayList<Integer> m_bingoNumbers = new ArrayList<Integer>();
        public ArrayList<BingoBoard> m_bingoBoards = new ArrayList<BingoBoard>();

        public ParsedCsvResult() {

        }
    }
}
