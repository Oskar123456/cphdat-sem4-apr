package searchandsort;

import searchandsort.entities.Student;

import java.util.*;

public class SortExamples {

    public static void bubbleSort(List<Student> students) {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).getId() > students.get(j + 1).getId()) {
                    Collections.swap(students, j, j + 1);
                }
            }
        }
    }

    public static void heapSort(List<Student> students) {
        PriorityQueue<Student> heap = new PriorityQueue<>(Comparator.comparingInt(Student::getId));
        heap.addAll(students);

        students.clear();
        while (!heap.isEmpty()) {
            students.add(heap.poll());
        }
    }

    // rekursiv metode
    public static void quickSort(List<Student> students, int low, int high) {
        // base case - vi hopper ud af rekursion hvis low er >=  high
        if (low < high) {
            int pivot = partition(students, low, high);
            quickSort(students, low, pivot - 1);
            quickSort(students, pivot + 1, high);
        }
    }

    private static int partition(List<Student> students, int low, int high) {
        int pivotValue = students.get(high).getId();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (students.get(j).getId() <= pivotValue) {
                i++;
                Collections.swap(students, i, j);
            }
        }
        Collections.swap(students, i + 1, high);
        return i + 1;
    }

    public static void mergeSort(List<Student> students) {
        if (students.size() > 1) {
            int mid = students.size() / 2;
            List<Student> left = new ArrayList<>(students.subList(0, mid));
            List<Student> right = new ArrayList<>(students.subList(mid, students.size()));

            mergeSort(left);
            mergeSort(right);

            merge(students, left, right);
        }
    }

    private static void merge(List<Student> students, List<Student> left, List<Student> right) {
        int i = 0, j = 0, k = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).getId() <= right.get(j).getId()) {
                students.set(k++, left.get(i++));
            } else {
                students.set(k++, right.get(j++));
            }
        }

        while (i < left.size()) {
            students.set(k++, left.get(i++));
        }

        while (j < right.size()) {
            students.set(k++, right.get(j++));
        }
    }




}
