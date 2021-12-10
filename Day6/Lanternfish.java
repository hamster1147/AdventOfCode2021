import java.util.ArrayList;

public class Lanternfish {
    private static int k_resetAge = 6;
    private static int k_childStartingAge = 8;
    private int m_age;
    private ArrayList<Lanternfish> m_kids = new ArrayList<Lanternfish>();

    public Lanternfish(int age) {
        m_age = age;
    }

    public int sizeOfFamily() {
        int result = 1; // Include self
        for (Lanternfish kid : m_kids) {
            result += kid.sizeOfFamily();
        }
        return result;
    }

    public void tick() {
        // Age children first since new children don't age yet
        for (Lanternfish kid : m_kids) {
            kid.tick();
        }

        // Spawn Child
        if (m_age == 0) {
            m_kids.add(new Lanternfish(k_childStartingAge));
            m_age = k_resetAge;
        } else {
            m_age--;
        }
    }

    public int familySizeAfterDays(int days) {
        int totalCount = 1;

        for (int day = days; day >= 1; day--) {
            if (m_age == 0) {
                Lanternfish kid = new Lanternfish(k_childStartingAge);
                totalCount += kid.familySizeAfterDays(day - 1);
                m_age = k_resetAge;
            } else {
                m_age--;
            }
        }

        return totalCount;
    }
}
