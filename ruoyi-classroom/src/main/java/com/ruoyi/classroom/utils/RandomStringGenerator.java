package com.ruoyi.classroom.utils;

import java.security.SecureRandom;

public class RandomStringGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom random = new SecureRandom();

    public static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }
        return sb.toString();
    }

//    public static void main(String[] args) {
////        int length = 6;
////        String randomString = generateRandomString(length);
////        System.out.println("Random 6-character string: " + randomString);
//
////        Integer a = 9;
////        int n1 = System.identityHashCode(a);
////        System.out.println(n1);
////        int n3 = System.identityHashCode(a);
////        System.out.println(n3);
////        a += 10;
////        int n2 = System.identityHashCode(a);
////        System.out.println(n2);
////        System.out.println(a);
//
//        Integer a = 10;
//        int n1 = System.identityHashCode(a);
//        System.out.println(n1);
//        Integer b = 10;
//        int n2 = System.identityHashCode(b);
//        System.out.println(n2);
//        System.out.println(a == b);
//    }
}
