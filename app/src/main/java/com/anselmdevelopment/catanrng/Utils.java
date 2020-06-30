package com.anselmdevelopment.catanrng;

import java.util.Random;

class Utils {

    private static Random random = new Random();

    public static int choose(int... numbers) {
        return numbers[random.nextInt(numbers.length)];
    }
}

