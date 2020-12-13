package com.koy.util;

import java.util.List;

/**
 * @author Koy  https://github.com/Koooooo-7
 * @Description util for list
 */

public class ListUtil {

    public static <T> T pop(List<T> list) {
        return pop(list, list.size() - 1);
    }

    public static <T> T pop(List<T> list, int index) {
        if (list.isEmpty()) {
            return null;
        }

        return list.remove(index);
    }
}
