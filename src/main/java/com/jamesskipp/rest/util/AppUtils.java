package com.jamesskipp.rest.util;

import com.jamesskipp.rest.domain.BaseDomainClass;
import org.apache.commons.lang3.StringUtils;

public class AppUtils {

    /**
     * Returns whether a subtype of BaseDomainClass is non-empty
     * and has an Id.
     *
     * @param object
     * @return
     */
    public static boolean exists(BaseDomainClass object) {
        return object != null && StringUtils.isNotEmpty(object.getId());
    }

    /**
     * Takes an optional number of strings and returns true
     * if any of them are empty or null.
     *
     * @param strings
     * @return
     */
    public static boolean stringsAreEmpty(String... strings) {
        boolean result = false;

        for (String string : strings) {
            if (StringUtils.isEmpty(string)) {
                result = true;
            }
        }

        return result;
    }
}