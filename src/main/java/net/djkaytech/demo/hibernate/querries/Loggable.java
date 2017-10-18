package net.djkaytech.demo.hibernate.querries;

/**
 * Created by PLKASIU on 2017-10-13.
 */
public interface Loggable {

    default void log(String log) {
        System.out.println(getClass().getName() + ":" + log + ":");
    }


}
