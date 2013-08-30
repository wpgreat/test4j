package org.test4j.hamcrest.matcher.property.comparator;

import java.util.HashMap;

import org.junit.Test;
import org.test4j.hamcrest.matcher.property.reflection.EqMode;
import org.test4j.json.encoder.beans.test.User;
import org.test4j.junit.JTester;

@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
public class MapComparatorTest implements JTester {
    @Test
    public void testMap() {
        want.object(new HashMap() {
            {
                this.put("id", 123);
                this.put("name", "darui.wu");
            }
        }).reflectionEq(new HashMap() {
            {
                this.put("id", 123);
                this.put("name", null);
            }
        }, EqMode.IGNORE_DEFAULTS);
    }

    @Test
    public void testMap2() {
        want.object(User.newInstance(123, "darui.wu")).reflectionEqMap(new DataMap() {
            {
                this.put("id", 123);
                this.put("name", null);
            }
        }, EqMode.IGNORE_DEFAULTS);
    }

    @Test(expected = AssertionError.class)
    public void testMap3() {
        want.object(new HashMap() {
            {
                this.put("id", 123);
                this.put("name", "darui.wu");
            }
        }).reflectionEq(new HashMap() {
            {
                this.put("id", 123);
            }
        }, EqMode.IGNORE_DEFAULTS);
    }

}