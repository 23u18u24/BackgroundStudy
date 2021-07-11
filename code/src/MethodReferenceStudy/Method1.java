package MethodReferenceStudy;

import java.util.Locale;

public class Method1 {
    public void printUpperCase(String s) {
        System.out.println(s.toUpperCase(Locale.ROOT));
    }

    public static void printLowerCase(String s) {
        System.out.println(s.toLowerCase(Locale.ROOT));
    }
}
