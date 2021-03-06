package org.test4j.hamcrest.iassert.common.impl;

import java.io.File;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

import org.junit.Test;
import org.test4j.hamcrest.iassert.common.intf.IAssert;
import org.test4j.junit.Test4J;
import org.test4j.junit.annotations.DataFrom;

@SuppressWarnings({ "rawtypes" })
public class AssertTest extends Test4J {
    @Test
    @DataFrom("assertClass")
    public void wanted(IAssert<?, ?> as, Class claz) {
        want.object(as).propertyEq("valueClaz", claz);
    }

    public static DataIterator assertClass() {
        return new DataIterator() {
            {
                data(the.bool(), Boolean.class);
                data(the.array(), Object[].class);
                data(the.bite(), Byte.class);
                data(the.calendar(), Calendar.class);
                data(the.character(), Character.class);
                data(the.collection(), Collection.class);
                data(the.date(), Date.class);
                data(the.doublenum(), Double.class);
                data(the.file(), File.class);
                data(the.floatnum(), Float.class);
                data(the.integer(), Integer.class);
                data(the.longnum(), Long.class);
                data(the.map(), Map.class);
                data(the.object(), Object.class);
                data(the.shortnum(), Short.class);
                data(the.string(), String.class);
            }
        };
    }

    @Test
    public void wantedMap() {
        want.object(the.map()).propertyEq("valueClaz", Map.class);
    }
}
