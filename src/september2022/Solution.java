package september2022;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Solution {

    private static String TEST_INPUT = """
        3
        2
        blue 2 1
        yellow 1 2
        2
        blue 2 1
        brown 2 2
        1
        red 1 1
        """;

    private static String TEST_INPUT_2 = """
        1
        5
        blue 1 2
        green 1 4
        orange 2 5
        red 3 6
        yellow 3 7
        """;

    private static String TEST_INPUT_3 = """
        1
        5
        blue 1 5
        blue 1 2
        blue 2 3
        blue 2 4
        blue 1 1
        """;

    public static void main(String[] args) {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
//        Scanner in = new Scanner(new ByteArrayInputStream(TEST_INPUT_3.getBytes()));
//        int nTestCases = in.nextInt(); // Scanner has functions to read ints, longs, strings, chars, etc.

        String s = in.nextLine();
        int nTestCases = Integer.parseInt(s);

        for (int i = 1; i <= nTestCases; ++i) {
            String line = in.nextLine();
            int nFabrics = Integer.parseInt(line);

            List<Fabric> fabrics = new ArrayList<>();

            for (int j = 0; j < nFabrics; j++) {
                String fabricLine = in.nextLine();
                String[] tokens = fabricLine.split(" ");


                String color = tokens[0];
                int durability = Integer.parseInt(tokens[1]);
                int id = Integer.parseInt(tokens[2]);

                Fabric fabric = new Fabric(color, durability, id);
                fabrics.add(fabric);
            }

            List<Fabric> sortedCopyAda = new ArrayList<>(fabrics);
            sortedCopyAda.sort((o1, o2) -> {
                int colorCompareResult = o1.color.compareTo(o2.color);
                if (colorCompareResult != 0) {
                    return colorCompareResult;
                } else {
                    return o1.id - o2.id;
                }
            });

            List<Fabric> sortedCopyCharles = new ArrayList<>(fabrics);
            sortedCopyCharles.sort((o1, o2) -> {
                int durabilityCompareResult = o1.durability - o2.durability;
                if (durabilityCompareResult != 0) {
                    return durabilityCompareResult;
                } else {
                    return o1.id - o2.id;
                }
            });

            int count = 0;
            for (int j = 0; j < nFabrics; j++) {
                Fabric fabric2 = sortedCopyAda.get(j);
                Fabric fabric3 = sortedCopyCharles.get(j);
                if (fabric2.equals(fabric3)) {
                    count++;
                }
            }

            System.out.printf("Case #%d: %d\n", i, count);
        }

    }

    public static class Fabric {

        public final String color;
        public final int durability;
        public final int id;

        public Fabric(String color, int durability, int id) {
            this.color = color;
            this.durability = durability;
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Fabric fabric = (Fabric) o;
            return durability == fabric.durability && id == fabric.id && Objects.equals(color, fabric.color);
        }

        @Override
        public int hashCode() {
            return Objects.hash(color, durability, id);
        }
    }

}
