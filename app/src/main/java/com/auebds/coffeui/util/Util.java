package com.auebds.coffeui.util;

import java.util.Locale;

public class Util {

    public static String localizedToString(int amount) {
        return String.format(Locale.getDefault(), "%d", amount);
    }
}
