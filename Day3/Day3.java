import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Day3 {
    public static void main(String[] args) {
        ArrayList<ArrayList<Boolean>> data;

        if (args.length >= 2) {
            data = parseDataFile(args[1]);
        } else {
            data = parseDataFile("Day3/Day3_Input.csv");
        }

        System.out.println("Part 1 Answer: " + part1(data));
        System.out.println("Part 2 Answer: " + part2(data));
    }

    static public ArrayList<ArrayList<Boolean>> parseDataFile(String filePath) {
        ArrayList<ArrayList<Boolean>> data = new ArrayList<ArrayList<Boolean>>();

        try {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                ArrayList<Boolean> bitList = new ArrayList<Boolean>();

                for (char bit : line.toCharArray()) {
                    bitList.add(bit == '1'); // This assumes the only two options are '0' or '1'
                }
                data.add(bitList);
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return data;
    }

    /**
     * Part 1 of Day 3 Challenge.
     * 
     * @param data List of boolean "bit" arrays parsed from input file.
     * @return The answer for part 1.
     */
    static public int part1(ArrayList<ArrayList<Boolean>> data) {
        int total = data.size();
        ArrayList<Integer> onesTally = new ArrayList<Integer>();

        for (ArrayList<Boolean> bitList : data) {
            int i = 0;
            for (Boolean bit : bitList) {
                if (onesTally.size() <= i) {
                    onesTally.add(0); // Add new entry
                }

                if (bit) {
                    int incremented = onesTally.get(i).intValue() + 1;
                    onesTally.set(i, Integer.valueOf(incremented));
                }
                i++;
            }
        }

        ArrayList<Boolean> gammaBitArray = new ArrayList<Boolean>();

        for (Integer ones : onesTally) {
            boolean onesMajority = ones.intValue() >= (total - ones.intValue());
            gammaBitArray.add(Boolean.valueOf(onesMajority));
        }

        int gamma = 0;
        int epsilon = 0;

        for (Boolean gammaBit : gammaBitArray) {
            gamma = (gamma << 1) | (gammaBit ? 1 : 0);
            epsilon = (epsilon << 1) | (gammaBit ? 0 : 1);
        }

        return epsilon * gamma;
    }

    /**
     * Part 2 of Day 3 Challenge.
     * 
     * @param data List of boolean "bit" arrays parsed from input file.
     * @return The answer for part 1.
     */
    static public int part2(ArrayList<ArrayList<Boolean>> data) {
        // Find Oxygen
        ArrayList<Boolean> oxygenBitArray = new ArrayList<Boolean>();
        ArrayList<ArrayList<Boolean>> oxygenList = data;
        for (int i = 0; i < data.get(0).size(); i++) {
            int tally = 0;
            for (ArrayList<Boolean> bits : oxygenList) {
                if (bits.get(i)) {
                    tally++;
                }
            }

            boolean oneMajority = false;
            if (tally == (oxygenList.size() - tally)) {
                oneMajority = true;
            } else {
                oneMajority = (tally > (oxygenList.size() - tally));
            }
            ArrayList<ArrayList<Boolean>> newOxygenList = new ArrayList<ArrayList<Boolean>>();

            for (ArrayList<Boolean> bits : oxygenList) {
                if (bits.get(i) == oneMajority) {
                    newOxygenList.add(bits);
                }
            }

            if (newOxygenList.size() == 1) {
                oxygenBitArray = newOxygenList.get(0);
                break;
            }

            oxygenList = newOxygenList;
        }

        int oxygen = 0;
        for (Boolean oxygenBit : oxygenBitArray) {
            oxygen = (oxygen << 1) | (oxygenBit ? 1 : 0);
        }

        // Find CO2
        ArrayList<Boolean> co2BitArray = new ArrayList<Boolean>();
        ArrayList<ArrayList<Boolean>> co2List = data;
        for (int i = 0; i < data.get(0).size(); i++) {
            int tally = 0;
            for (ArrayList<Boolean> bits : co2List) {
                if (bits.get(i)) {
                    tally++;
                }
            }

            boolean oneMinority = false;
            if (tally == (co2List.size() - tally)) {
                oneMinority = false;
            } else {
                oneMinority = (tally < (co2List.size() - tally));
            }
            ArrayList<ArrayList<Boolean>> newCo2List = new ArrayList<ArrayList<Boolean>>();

            for (ArrayList<Boolean> bits : co2List) {
                if (bits.get(i) == oneMinority) {
                    newCo2List.add(bits);
                }
            }

            if (newCo2List.size() == 1) {
                co2BitArray = newCo2List.get(0);
                break;
            }

            co2List = newCo2List;
        }

        int co2 = 0;
        for (Boolean co2Bit : co2BitArray) {
            co2 = (co2 << 1) | (co2Bit ? 1 : 0);
        }

        return oxygen * co2;
    }
}