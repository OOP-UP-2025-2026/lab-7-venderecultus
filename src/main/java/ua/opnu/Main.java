package ua.opnu;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        Predicate<Integer> isPrime = x -> {
            if (x <= 1) return false;
            for (int i = 2; i <= x / 2; i++) {
                if (x % i == 0) return false;
            }
            return true;
        };

        int[] nums = {1, 2, 3, 4, 5, 6, 7, 11, 12, 13};
        System.out.println("Перевірка на прості числа:");
        for (int n : nums) {
            System.out.println(n + " -> " + isPrime.test(n));
        }

        Student[] students = {
                new Student("Іванов Данііл", "УП-241", new int[]{70, 65, 78, 62}),
                new Student("Фірсов Микита", "УП-241", new int[]{90, 95, 70, 95}),
                new Student("Мартиненко Вікторія", "УІ-241", new int[]{100, 99, 98, 97})
        };

        Predicate<Integer> notFailed = mark -> mark >= 60;
        Student[] passed = filter(students, notFailed);

        System.out.println("\nСтуденти без боргів:");
        for (Student s : passed) {
            System.out.println(s.getName());
        }

        Predicate<Integer> isGood = mark -> mark >= 75;
        Predicate<Integer> isExcellent = mark -> mark <= 100;

        Student[] goodStudents = filterTwoConditions(students, isGood, isExcellent);
        System.out.println("\nСтуденти з гарними оцінками (75-100):");
        for (Student s : goodStudents) {
            System.out.println(s.getName());
        }

        System.out.println("\nСписок групи:");
        Consumer<Student> showName = s -> System.out.println("Студент: " + s.getName());
        performAction(students, showName);

        System.out.println("\nЧисла кратні 10:");
        int[] numbers = {5, 10, 15, 20, 25, 30, 100};
        Predicate<Integer> modTen = x -> x % 10 == 0;
        Consumer<Integer> printInt = x -> System.out.print(x + " ");
        processNumbers(numbers, modTen, printInt);
        System.out.println();

        System.out.println("\nСтупені двійки:");
        int[] inputs = {1, 2, 3, 4, 5};
        Function<Integer, Integer> powerOfTwo = x -> (int) Math.pow(2, x);

        for (int i : inputs) {
            System.out.println("2^" + i + " = " + powerOfTwo.apply(i));
        }

        System.out.println("\nЧисла прописом:");
        int[] digits = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Function<Integer, String> numToText = x -> {
            String[] names = {"нуль", "один", "два", "три", "чотири", "п'ять", "шість", "сім", "вісім", "дев'ять"};
            if (x >= 0 && x < names.length) return names[x];
            return "невідомо";
        };

        printMapped(digits, numToText);

        System.out.println("\nДодаткове завдання");

        Student[] groupForSort = {
                new Student("Іванов Данііл", "УП-241", new int[]{}),
                new Student("Фірсов Микита", "УП-241", new int[]{}),
                new Student("Мартиненко Вікторія", "УІ-241", new int[]{})
        };

        SortingList list = new SortingList(groupForSort);

        System.out.println("До сортування:");
        list.print();

        System.out.println("\nСортування по імені (алфавіт):");
        list.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
        list.print();

        System.out.println("\nСортування по групі (зворотній порядок):");
        list.sort((s1, s2) -> s2.getGroup().compareTo(s1.getGroup()));
        list.print();
    }

    public static Student[] filter(Student[] arr, Predicate<Integer> condition) {
        Student[] temp = new Student[arr.length];
        int count = 0;

        for (Student s : arr) {
            boolean ok = true;
            for (int mark : s.getMarks()) {
                if (!condition.test(mark)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                temp[count++] = s;
            }
        }
        return Arrays.copyOf(temp, count);
    }

    public static Student[] filterTwoConditions(Student[] arr, Predicate<Integer> p1, Predicate<Integer> p2) {
        Student[] temp = new Student[arr.length];
        int count = 0;

        for (Student s : arr) {
            boolean ok = true;
            for (int mark : s.getMarks()) {
                if (!p1.test(mark) || !p2.test(mark)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                temp[count++] = s;
            }
        }
        return Arrays.copyOf(temp, count);
    }

    public static void performAction(Student[] arr, Consumer<Student> action) {
        for (Student s : arr) {
            action.accept(s);
        }
    }

    public static void processNumbers(int[] arr, Predicate<Integer> p, Consumer<Integer> c) {
        for (int n : arr) {
            if (p.test(n)) {
                c.accept(n);
            }
        }
    }

    public static void printMapped(int[] arr, Function<Integer, String> converter) {
        for (int n : arr) {
            System.out.println(n + " : " + converter.apply(n));
        }
    }
}