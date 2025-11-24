package ua.opnu;

import java.util.Comparator;

public class SortingList {
    private Student[] students;

    public SortingList(Student[] students) {
        this.students = students;
    }

    public void sort(Comparator<Student> c) {
        for (int i = 0; i < students.length - 1; i++) {
            for (int j = 0; j < students.length - i - 1; j++) {
                if (c.compare(students[j], students[j + 1]) > 0) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }

    public void print() {
        for (Student s : students) {
            System.out.println(s.getName() + " (" + s.getGroup() + ")");
        }
    }
}