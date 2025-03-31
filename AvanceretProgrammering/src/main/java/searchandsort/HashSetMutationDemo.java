package searchandsort;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashSetMutationDemo {

    static class Student {
        private String name;
        private int id;

        public Student(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Student)) return false;
            Student student = (Student) o;
            return id == student.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return "Student{" + name + ", id=" + id + '}';
        }
    }

    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();

        Student alice = new Student("Alice", 1);
        students.add(alice);

        System.out.println("Før ændring:");
        System.out.println("Indeholder Alice? " + students.contains(alice)); // true

        // Ændrer ID, som bruges i hashCode + equals
        alice.setId(99);

        System.out.println("\nEfter ændring:");
        System.out.println("Indeholder Alice? " + students.contains(alice)); // false!
        System.out.println("Prøver at fjerne Alice: " + students.remove(alice)); // false!
        System.out.println("Sættet indeholder:");
        for (Student s : students) {
            System.out.println(" - " + s);
        }
    }
}

