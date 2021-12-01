import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Day1 {
    public static void main(String[] args) {
        ArrayList<Integer> data;
        boolean started = false;
        int prevValue = 0;
        int total = 0;

        if (args.length >= 2) {
            data = loadData(args[1]);
        } else {
            data = loadData("Day1_Input.csv");
        }

        for (Integer value : data) {
            if (started && value.intValue() > prevValue) {
                total++;
            }
            prevValue = value.intValue();
            started = true;
        }

        System.out.println("Answer: " + total);
    }

    static public ArrayList<Integer> loadData(String filePath) {
        ArrayList<Integer> data = new ArrayList<Integer>();

        try {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNextInt()) {
                data.add(Integer.parseInt(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return data;
    }
}