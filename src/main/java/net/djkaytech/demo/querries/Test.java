package net.djkaytech.demo.querries;

import java.util.stream.IntStream;

/**
 * Created by PLKASIU on 2017-10-13.
 */
public class Test {

    public static void main(String[] args) {
        //System.out.println(3^3);
        System.out.println(IntStream.range(0, 10).map(i -> (int)Math.pow(i, 4)).average().getAsDouble());
    }

}
