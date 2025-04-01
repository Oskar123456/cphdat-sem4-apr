package cphdatadvprg;

import java.lang.System;
import java.util.*;

/**
 *
 * Exercise template
 *
 *
 */
public class Main
{
    public static void main( String[] args )
    {
        System.out.println(9 == sumIntervals(new int[][]{{1, 2}, {6, 10}, {11, 15}}));
        System.out.println(11 == sumIntervals(new int[][]{{4, 8}, {9, 10}, {15, 21}}));
        System.out.println(7 == sumIntervals(new int[][]{{-1, 4}, {-5, -3}}));
        System.out.println(78 == sumIntervals(new int[][]{{-245, -218}, {-194, -179}, {-155, -119}}));
        System.out.println(54 == sumIntervals(new int[][]{{1, 2}, {2, 6}, {6, 55}}));
        System.out.println(23 == sumIntervals(new int[][]{{-2, -1}, {-1, 0}, {0, 21}}));
        System.out.println(7 == sumIntervals(new int[][]{{1, 4}, {7, 10}, {3, 5}}));
        System.out.println(6 == sumIntervals(new int[][]{{5, 8}, {3, 6}, {1, 2}}));
        System.out.println(19 == sumIntervals(new int[][]{{1, 5}, {10, 20}, {1, 6}, {16, 19}, {5, 11}}));
    }

    public static String firstNonRepeatingLetter(String s)
    {
        Map<Character, Integer> freqs = new HashMap<Character, Integer>();

        int i, i_first = 0;
        for (i = 0; i < s.length(); ++i) {
            char c = Character.toLowerCase(s.charAt(i));
            freqs.put(c, (freqs.containsKey(c)) ? freqs.get(c) + 1 : 1);
        }

        for (i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (freqs.get(Character.toLowerCase(c)) == 1) {
                return String.valueOf(c);
            }
        }

        return new String();
    }

    public static int sumIntervals(int[][] intervals)
    {
        List<Integer[]> ret_intervals = new ArrayList<>();
        List<Integer[]> input_intervals = new ArrayList<>();
        for (int i = 0; i < intervals.length; ++i) {
            ret_intervals.add(new Integer[] { intervals[i][0], intervals[i][1] });
        }

        for (int i = 0; i < intervals.length; ++i) {
            int delete_at;
            boolean should_add = true;
            for (int j = 0; j < ret_intervals.size(); j++) {
                int[] ival = ret_intervals.get(i);
                int i_lo = intervals[i][0];
                int i_hi = intervals[i][1];

                if ((i_lo <= ival[0] && ival[0] <= i_hi)
                        || (i_lo <= ival[1] && ival[1] <= i_hi)
                        || (ival[0] <= i_lo && i_lo <= ival[1])
                        || (ival[0] <= i_hi && i_hi <= ival[1])) {
                    ival[0] = Math.min(ival[0], i_lo);
                    ival[1] = Math.max(ival[1], i_hi);
                    should_add = false;
                    delete_at = j;
                    input_intervals.add(ival);

                    System.out.printf("[%d, %d] was merged...%n",  intervals[i][0], intervals[i][1] );
                    // for (var _ival : ret_intervals) {
                    //     System.out.printf("[%d, %d] ", _ival[0], _ival[1]);
                    // }
                    // System.out.println();

                    break;
                        }

            }
            if (should_add) {
                ret_intervals.add(new Integer[] { intervals[i][0], intervals[i][1] });

                System.out.printf("[%d, %d] was added: ",  intervals[i][0], intervals[i][1] );
                for (var ival : ret_intervals) {
                    System.out.printf("[%d, %d] ", ival[0], ival[1]);
                }
                System.out.println();
            }
        }

        for (var ival : intervals) {
            System.out.printf("[%d, %d] ", ival[0], ival[1]);
        }
        System.out.println();

        for (var ival : ret_intervals) {
            System.out.printf("[%d, %d] ", ival[0], ival[1]);
        }
        System.out.println();

        int len = 0;
        for (var ival : ret_intervals) {
            len += Math.abs(ival[0] - ival[1]);
        }
        return len;
    }
}
