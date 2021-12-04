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

        // Find Oxygen Rate
        ArrayList<ArrayList<Double>> oxygenTallyGraph = new ArrayList<ArrayList<Double>>();
        for (ArrayList<Boolean> bitList : dataTransposed) {
            int i = 0;
            ArrayList<Double> singleBitOxygenTallyGraph = new ArrayList<Double>();
            for (Boolean bit : bitList) {
                if (singleBitOxygenTallyGraph.size() <= i) {
                    singleBitOxygenTallyGraph.add(0.0); // Add new entry
                }

                // Why the heck is the toIndex for subList exclusive?
                ArrayList<Boolean> subBitList = new ArrayList<Boolean>(bitList.subList(i, total));
                for (Boolean subBit : subBitList) {
                    if (subBit) {
                        double incremented = singleBitOxygenTallyGraph.get(i).doubleValue() + 1.0;
                        singleBitOxygenTallyGraph.set(i, Double.valueOf(incremented));
                    }
                }

                double percent = singleBitOxygenTallyGraph.get(i) / (total - i);
                singleBitOxygenTallyGraph.set(i, percent);
                i++;
            }
            oxygenTallyGraph.add(singleBitOxygenTallyGraph);
        }

        int oxygen_i = 0;
        boolean oxygen_found = false;

        for (ArrayList<Double> onesTally : oxygenTallyGraph) {
            ArrayList<Double> onesTallySub = new ArrayList<Double>(onesTally.subList(oxygen_i, total));
            int temp_i = oxygen_i;
            for (Double onesPercent : onesTallySub) {
                if (Math.abs(onesPercent - 0.5) < 1.0e-9) {
                    oxygen_i = temp_i;
                    break;
                }
                temp_i++;

                if (temp_i == total) {
                    oxygen_found = true;
                    break;
                }
            }

            if (oxygen_found) {
                break;
            }
        }

        int oxygen = 0;
        for (Boolean oxygenBit : data.get(oxygen_i)) {
            oxygen = (oxygen << 1) | (oxygenBit ? 1 : 0);
        }

        // Find CO2 Rate
        ArrayList<ArrayList<Double>> co2TallyGraph = new ArrayList<ArrayList<Double>>();
        for (ArrayList<Boolean> bitList : dataTransposed) {
            int i = 0;
            ArrayList<Double> singleBitCo2TallyGraph = new ArrayList<Double>();
            for (Boolean bit : bitList) {
                if (singleBitCo2TallyGraph.size() <= i) {
                    singleBitCo2TallyGraph.add(0.0); // Add new entry
                }

                // Why the heck is the toIndex for subList exclusive?
                ArrayList<Boolean> subBitList = new ArrayList<Boolean>(bitList.subList(i, total));
                for (Boolean subBit : subBitList) {
                    if (!subBit) {
                        double incremented = singleBitCo2TallyGraph.get(i).doubleValue() + 1.0;
                        singleBitCo2TallyGraph.set(i, Double.valueOf(incremented));
                    }
                }

                double percent = singleBitCo2TallyGraph.get(i) / (total - i);
                singleBitCo2TallyGraph.set(i, percent);
                i++;
            }
            co2TallyGraph.add(singleBitCo2TallyGraph);
        }

        int co2_i = 0;
        boolean co2_found = false;

        for (ArrayList<Double> onesTally : co2TallyGraph) {
            ArrayList<Double> onesTallySub = new ArrayList<Double>(onesTally.subList(co2_i, total));
            int temp_i = co2_i;
            for (Double onesPercent : onesTallySub) {
                if (Math.abs(onesPercent - 0.5) < 1.0e-9) {
                    co2_i = temp_i;
                    break;
                }
                temp_i++;

                if (temp_i == total) {
                    co2_found = true;
                    break;
                }
            }

            if (co2_found) {
                break;
            }
        }

        int co2 = 0;
        for (Boolean co2Bit : data.get(co2_i)) {
            co2 = (co2 << 1) | (co2Bit ? 1 : 0);
        }

        return oxygen * co2;
    }
}