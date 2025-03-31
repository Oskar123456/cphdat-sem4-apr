package searchandsort;

import searchandsort.entities.Student;

import java.util.List;
import java.util.Scanner;


public class SearchExamples {

    public static void main(String[] args) {
        System.out.println("Tænk på et tal mellem 1 og 100");
        System.out.println("Jeg prøver at gætte det...");

        System.out.println("\nLineær søgning:");
        guessNumberLinearly();

        System.out.println("\nBinær søgning:");
        guessNumberBinary();
    }

    private static Scanner scanner = new Scanner(System.in);

    // Lineær søgning i array
    public static int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) return i; // fundet!
        }
        return -1; // ikke fundet
    }

    // Binær søgning (sorteret array)
    public static int binarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] == target) {
                return mid; // fundet!
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1; // ikke fundet
    }

    // Lineær søgning i liste
    public static Student linearSearch(List<Student> students, int id) {
        for (Student student : students) {
            if (student.getId() == id) return student;
        }
        return null;
    }

    // Binær søgning - O(log n) - Kræver sorteret liste!
    public static Student binarySearch(List<Student> students, int id) {
        int low = 0;
        int high = students.size() - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            Student midStudent = students.get(mid);
            if (midStudent.getId() == id) {
                return midStudent;
            } else if (midStudent.getId() < id) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }

    // Lineær søgning: prøver 1, 2, 3, ...
    public static void guessNumberLinearly() {

    }

        // Binær søgning: deler søgeområdet og spørger større/mindre
    public static void guessNumberBinary() {

    }



}
