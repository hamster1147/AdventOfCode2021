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
     * Part 2 of Day 3 Challenge. My solution is a little involved. The idea is to
     * make a 2D table, each cell is the majority of 1 bits from that position to
     * the end of file.
     * 
     * @param data List of boolean "bit" arrays parsed from input file.
     * @return The answer for part 1.
     */
    static public int part2(ArrayList<ArrayList<Boolean>> data) {
        int total = data.size();
        ArrayList<ArrayList<Double>> onesTallyGraph = new ArrayList<ArrayList<Double>>();

        // Transpose list because my solution is too big to fail now
        ArrayList<ArrayList<Boolean>> dataTransposed = new ArrayList<ArrayList<Boolean>>();
        for (ArrayList<Boolean> bitList : data) {
            int i = 0;
            for (Boolean bit : bitList) {
                if (dataTransposed.size() <= i) {
                    dataTransposed.add(new ArrayList<Boolean>());
                }
                dataTransposed.get(i).add(bit);
                i++;
            }
        }

        for (ArrayList<Boolean> bitList : dataTransposed) {
            int i = 0;
            int j = 0;
            ArrayList<Double> singleBitOnesTallyGraph = new ArrayList<Double>();
            for (Boolean bit : bitList) {
                if (singleBitOnesTallyGraph.size() <= j) {
                    singleBitOnesTallyGraph.add(0.0); // Add new entry
                }

                // Why the heck is the toIndex for subList exclusive?
                ArrayList<Boolean> subBitList = new ArrayList<Boolean>(bitList.subList(j, total));
                for (Boolean subBit : subBitList) {
                    if (subBit) {
                        double incremented = singleBitOnesTallyGraph.get(j).doubleValue() + 1.0;
                        singleBitOnesTallyGraph.set(j, Double.valueOf(incremented));
                    }
                }

                double percent = singleBitOnesTallyGraph.get(j) / (total - j);
                singleBitOnesTallyGraph.set(j, percent);
                j++;
            }
            onesTallyGraph.add(singleBitOnesTallyGraph);
        }

        int i = 0;
        boolean found = false;

        // Find Oxygen Rate
        int oxygen = 0;
        for (ArrayList<Double> onesTally : onesTallyGraph) {
            ArrayList<Double> onesTallySub = new ArrayList<Double>(onesTally.subList(i, total));
            int temp_i = i;
            for (Double onesPercent : onesTally) {
                if (Math.abs(onesPercent - 0.5) < 1.0e-9) {
                    i = temp_i;
                    break;
                }
                temp_i++;

                if (temp_i == total) {
                    found = true;
                    break;
                }
            }

            if (found) {
                break;
            }
        }

        for (Boolean oxygenBit : data.get(i)) {
            oxygen = (oxygen << 1) | (oxygenBit ? 1 : 0);
        }

        // Find CO2 Rate
        int co2 = 0;
        for (ArrayList<Double> onesTally : onesTallyGraph) {
            ArrayList<Double> onesTallySub = new ArrayList<Double>(onesTally.subList(i, total));
            int temp_i = i;
            for (Double onesPercent : onesTally) {
                if (Math.abs((1.0 - onesPercent) - 0.5) < 1.0e-9) {
                    i = temp_i;
                    break;
                }
                temp_i++;

                if (temp_i == total) {
                    found = true;
                    break;
                }
            }

            if (found) {
                break;
            }
        }

        for (Boolean co2Bit : data.get(i)) {
            co2 = (co2 << 1) | (co2Bit ? 1 : 0);
        }

        return oxygen * co2;
    }
}