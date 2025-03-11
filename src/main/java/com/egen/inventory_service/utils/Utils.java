package com.egen.inventory_service.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Utils {
    public static <T> List<T> convertToList(Iterable<T> iterable) {
        if (iterable == null) {
            return new ArrayList<>();
        }
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }
}
