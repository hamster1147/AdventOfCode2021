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

        System.out.println("Part 1 Answer: " + part1(parsedCsvResult, false));
        System.out.println("Part 2 Answer: " + part1(parsedCsvResult, true));
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
     * Part 1 of Day 4 Challenge. Also part 2 if part2 is set to true.
     * 
     * @param parsedCsvResult Object filled with bingo numbers and bingo boards.
     * @param part2           If true, result will be for part 2 and not part 1.
     * @return The answer for part 1, or part 2 is part2 is true.
     */
    static public int part1(ParsedCsvResult parsedCsvResult, boolean part2) {
        BingoBoard winningBingoBoard = null;
        int winningBingoNumber = 0;
        int totalWinningBoards = 0;

        for (int bingoNumber : parsedCsvResult.m_bingoNumbers) {
            for (BingoBoard bingoBoard : parsedCsvResult.m_bingoBoards) {
                bingoBoard.checkAndMarkNumber(bingoNumber);

                if (!bingoBoard.alreadyWon() && bingoBoard.isWinningBoard()) {
                    winningBingoBoard = bingoBoard;
                    winningBingoNumber = bingoNumber;
                    totalWinningBoards++;
                }
            }

            // I don't know why adding a negative 1 was the only way to
            // get part 2's solution
            if (winningBingoBoard != null && !part2) {
                return winningBingoBoard.calculatePart1Solution(bingoNumber);
            } else if (totalWinningBoards == parsedCsvResult.m_bingoBoards.size() - 1 && part2) {
                return winningBingoBoard.calculatePart1Solution(winningBingoNumber);
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
