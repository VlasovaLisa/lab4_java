//вариант 4 Власова Елизавета

import java.util.Scanner;
import static java.lang.System.out;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //1.1
        out.println("------------------ЗАДАНИЕ 1.1------------------");
        Box<Integer> box = new Box<>();
        out.print("введите целое число для добавления в коробку: ");
        int n;
        while (true) {
            if (in.hasNextInt()) {
                n = in.nextInt();
                break;
            } else {
                out.print("введите целое число: ");
                in.next();
            }
        }
        try {
            box.putItem(n);
            out.println("добавление прошло успешно");
        } catch (Exception e) {
            out.println(e.getMessage());
        }
        out.println(box.toString());
        printBoxValue(box);
        out.println(box.toString());

        //1.3
        out.println("------------------ЗАДАНИЕ 1.3------------------");
        out.print("создание первого объекта, введите целое число: ");
        Example obj1 = null;
        int value1;
        while (true) {
            if (in.hasNextInt()) {
                value1 = in.nextInt();
                break;
            } else {
                out.print("введите целое число: ");
                in.next();
            }
        }
        obj1 = new Example(value1);

        out.print("создание второго объекта, введите целое число: ");
        Example obj2 = null;
        int value2;
        while (true) {
            if (in.hasNextInt()) {
                value2 = in.nextInt();
                break;
            } else {
                out.print("введите целое число: ");
                in.next();
            }
        }
        obj2 = new Example(value2);

        out.println("первый объект: " + obj1);
        out.println("второй объект: " + obj2);

        //сравнение
        int res = obj1.сравнить(obj2);
        if (res > 0) {
            out.println("первый объект больше второго");
        } else if (res < 0) {
            out.println("первый объект меньше второго");
        } else {
            out.println("оба объекта равны");
        }

        //2.2
        out.println("------------------ЗАДАНИЕ 2.2------------------");
        Box<Integer> intBox = new Box<>();
        intBox.putItem(66);

        Box<Double> doubleBox = new Box<>();
        doubleBox.putItem(18.5);

        Box<Float> floatBox = new Box<>();
        floatBox.putItem(33.3f);

        List<Box<? extends Number>> boxes = List.of(intBox, doubleBox, floatBox);

        double max = findMax(boxes);
        out.println("максимальное значение: " + max);

        //для заданий 3.1. - 3.4.
        List<String> list_of_str = List.of("qwerty", "asdfg", "zx");
        List<Integer> list_of_num = List.of(1, -3, 7);
        List<int[]> arrays = List.of(new int[]{0, 7, -2}, new int[]{-1, -11, -5}, new int[]{14, 8, 9, 3, 25});

        //3.1
        out.println("------------------ЗАДАНИЕ 3.1------------------");

        out.println("преобразование списка строк в список их длин");
        out.println("исходный список: " + list_of_str);
        List<Integer> lenlist = method(list_of_str, x -> x.length());
        out.println("новый список: " + lenlist);

        out.println("\nпреобразование списка целых чисел в список положительных чисел");
        out.println("исходный список: " + list_of_num);
        List<Integer> rezlist = method(list_of_num, x -> Math.abs(x));
        out.println("новый список: " + rezlist);

        out.println("\nпреобразование списка массивов целых чисел в список с максимальными значениями из исходных массивов");
        out.println("исходные массивы: ");
        for (int[] i : arrays) {
            out.println(Arrays.toString(i));
        }
        List<Integer> maximum = method(arrays, x -> Arrays.stream(x).max().orElse(0));
        out.println("максимальные значения: " + maximum);

        //3.2
        out.println("------------------ЗАДАНИЕ 3.2------------------");

        out.println("преобразование списка строк в список строк, длины которых больше 3");
        out.println("исходный список: " + list_of_str);
        List<String> lenlist2 = filter(list_of_str, x->x.length() >= 3);
        out.println("новый список: " + lenlist2);

        out.println("\nпреобразование списка целых чисел в список неположительных чисел");
        out.println("исходный список: " + list_of_num);
        List<Integer> rezlist2 = filter(list_of_num, x -> x <= 0);
        out.println("новый список: " + rezlist2);

        out.println("\nпреобразование списка массивов целых чисел в список массивов неположительных чисел");
        out.println("исходные массивы: ");
        for (int[] i : arrays) {
            out.println(Arrays.toString(i));
        }
        List<int[]> newarr2 = filter(arrays, x -> Arrays.stream(x).allMatch(y -> y <= 0));
        out.println("новые массивы: ");
        for (int[] i : newarr2) {
            out.println(Arrays.toString(i));
        }

        //3.3
        out.println("------------------ЗАДАНИЕ 3.3------------------");

        out.println("преобразование списка строк в одну строку");
        out.println("исходный список: " + list_of_str);
        String str = cokr(list_of_str, (s1, s2) -> s1 + s2);
        out.println("сформированная строка: " + str);

        out.println("\nпреобразование списка целых чисел в сумму");
        out.println("исходный список: " + list_of_num);
        int sum = cokr(list_of_num, Integer::sum);
        out.println("сумма чисел: " + sum);

        out.println("\nпреобразование списка массивов целых чисел в общее количество элементов");
        List<List<Integer>> nList = Arrays.asList(
                Arrays.asList(0, 7, -2),
                Arrays.asList(-1, -11, -5),
                Arrays.asList(14, 8, 9, 3, 25)
        );
        out.println("исходные массивы: ");
        nList.forEach(subList -> out.println(subList));
        int count = cokr(nList, (l1, l2) -> {
            List<Integer> merged = new ArrayList<>(l1); // Создаем новый список
            merged.addAll(l2); // Добавляем элементы из второго списка
            return merged;
        }).size();
        out.println("общее количество элементов: " + count);

        //3.4
        out.println("------------------ЗАДАНИЕ 3.4------------------");
        out.println("преобразование списка целых чисел на подожительные и отрицательные");
        List<Integer> intList = Arrays.asList(1, -3, 7);
        Map<String, List<Integer>> part = conver(
                intList,
                HashMap::new,
                (map, value) -> {
                    if (value >= 0) {
                        map.computeIfAbsent("положительные", k -> new ArrayList<>()).add(value);
                    } else {
                        map.computeIfAbsent("отрицательные", k -> new ArrayList<>()).add(value);
                    }
                }
        );
        out.println("положительные и отрицательные числа:");
        out.println("положительные: " + part.get("положительные"));
        out.println("отрицательные: " + part.get("отрицательные"));

        out.println("\nпреобразование списка со значениями по длине");
        List<String> stringList1 = Arrays.asList("qwerty", "asdfg", "zx", "qw");
        Map<Integer, List<String>> group = conver(
                stringList1,
                HashMap::new,
                (map, value) -> map.computeIfAbsent(value.length(), k -> new ArrayList<>()).add(value)
        );
        group.forEach((length, strings) ->
                out.println("длина " + length + ": " + strings)
        );

        out.println("\nпреобразование списка по уникальным строкам");
        List<String> stringList2 = Arrays.asList("qwerty", "asdfg", "qwerty", "qw");
        Set<String> uniq = conver(
                stringList2,
                HashSet::new,
                Set::add
        );
        out.println("уникальные строки:");
        out.println(uniq);

        in.close();
    }

    //метод для коробки, извлекает и выводит значение 1.1
    public static void printBoxValue(Box<Integer> box) {
        try {
            Integer value = box.getItem();
            out.println("извлеченное значение: " + value);
        } catch (Exception e) {
            out.println(e.getMessage());
        }
    }

    //2.2 метод для поиска максимального значения
    public static double findMax(List<Box<? extends Number>> boxes) {
        double max = Double.NEGATIVE_INFINITY;
        for (Box<? extends Number> box : boxes) {
            if (box.isFull()) {
                double value = box.getItem().doubleValue();
                if (value > max) {
                    max = value;
                }
            }
        }
        if (max == Double.NEGATIVE_INFINITY) {
            throw new IllegalArgumentException("все коробки пусты");
        }
        return max;
    }

    //3.1
    public static <T, P> List<P> method(List<T> list, Function<T, P> obj) {
        List<P> res = new ArrayList<>();
        for (T item : list) {
            res.add(obj.apply(item));
        }
        return res;
    }

    //3.2
    public static <T> List<T> filter(List<T> list, Predicate<T> obj) {
        List<T> res = new ArrayList<>();
        for (T item : list) {
            if (obj.test(item)) {
                res.add(item);
            }
        }
        return res;
    }

    //3.3 сокращение
    public static <T> T cokr(List<T> list, BinaryOperator<T> reducer) {
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("список не должен быть пустым");
        }
        T res = list.get(0); //инициализация первого элемента
        for (int i = 1; i < list.size(); i++) {
            res = reducer.apply(res, list.get(i));
        }
        return res;
    }

    //3.4 коллекционирование
    public static <T, P> P conver(
            List<T> list,
            Supplier<P> collectionCreator, //фабрика для создания коллекции
            BiConsumer<P, T> valueAdder   //логика добавления элементов
    ) {
        if (list == null) {
            throw new IllegalArgumentException("список не должен быть пустым");
        }
        P res = collectionCreator.get(); //создаем пустую коллекцию
        for (T value : list) {
            valueAdder.accept(res, value); //добавляем элементы
        }
        return res;
    }

}