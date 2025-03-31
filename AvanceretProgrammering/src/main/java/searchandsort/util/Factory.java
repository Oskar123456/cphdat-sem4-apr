package searchandsort.util;

import searchandsort.entities.Course;
import searchandsort.entities.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


public class Factory {

    private static List<String> studentNames = new ArrayList<>();
    private static List<Course> courses = new ArrayList<>();
    private static String studentPath = "data/Students.txt";
    private static String coursePath = "data/Courses.txt";


    static{

        readStudentNames();
        readCourses();

    }
    private static void readCourses(){
        File file = new File(coursePath);
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                courses.add(new Course(scanner.next()));
            }
        } catch (FileNotFoundException e) {
            System.out.println("The file " + coursePath + " cannot be found");
        }
    }

    private static void readStudentNames(){
        File file = new File(studentPath);
        try {

            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                studentNames.add(scanner.next());

            }
        } catch (FileNotFoundException e) {
            System.out.println("The file " + studentPath + " cannot be found");
        }
    }

    public static Collection<Course> getCourses(){
        return courses;
    }

    public static void fillWithStudents(Collection<Student> collection, int amount){

        for(int i = 0; i < amount; i++){
            collection.add(new Student(studentNames.get(i % studentNames.size()), i));
        }
    }

    public static void fillWithStudentsAndCourses(Map<Student, Course> map, int amount){
        Random random = new Random();
        for(int i = 0; i < amount; i++){
            map.put(new Student(studentNames.get(i % studentNames.size()), i), courses.get(random.nextInt(courses.size())));
        }
    }
}
