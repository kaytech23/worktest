package net.djkaytech.demo.java8.streams;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;

/**
 * Created by PLKASIU on 2017-10-16.
 */
public class CollectorsApp {

    public static void main(String[] args) {

//        String test = Objects.requireNonNull(null);

        int[] ints = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 4321 ,43, 21, 432,14,33 };
        Arrays.stream(ints).forEach(i -> System.out.println(i));
        Arrays.stream(ints).forEach(System.out::println);

        Runnable r1 = () -> start();



        Runnable r = new Runnable() {
            @Override
            public void run() {
                start();
            }
        };



        BinaryOperator<List<Integer>> accumulator = (left, right) -> {
            left.addAll(right);
            return left;
        };

        Collector<Integer, ?, List<Integer>> collector1 = Collector.of(ArrayList::new, List::add, accumulator);

        Collector<Integer, ?, StringBuilder> collector2 = Collector.of(StringBuilder::new, StringBuilder::append, (left, right) -> {
            left.append(right);
            return left;
        });

        System.out.println(Arrays.stream(ints).boxed().reduce(0, (integer, integer2) -> integer = (integer + integer2) % 2, (integer, integer2) ->  (integer + integer2) % 2));

        //StringBuilder sb = new StringBuilder();
        //StringBuilder sb2 = Arrays.stream(ints).boxed().parallel().reduce(getStringBuilder(), (stringBuilder, integer) -> stringBuilder.append(integer % 2), (stringBuilder, stringBuilder2) -> stringBuilder.append(stringBuilder2));

        //System.out.println(sb2);

        System.out.println(Arrays.stream(ints).boxed().parallel().collect(collector1));
        //System.out.println(Arrays.stream(ints).boxed().collect(collector2));




//        List<String> givenList = Arrays.asList("a", "bb", "ccc", "dd");
//        Map<String, Integer> result = givenList.stream().collect(Collectors.toMap(Function.identity(), String::length, (i1, i2) -> i1));

    }

    private static void start() {
    }


    public static StringBuilder getStringBuilder() {
        return new StringBuilder();
    }
}

