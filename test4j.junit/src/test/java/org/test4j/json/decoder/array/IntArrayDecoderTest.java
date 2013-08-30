package org.test4j.json.decoder.array;

import org.junit.Test;
import org.test4j.json.JSON;
import org.test4j.junit.JTester;

public class IntArrayDecoderTest implements JTester {

    @Test
    public void testParseFromJSONArray() {
        String json = "['1','2','3']";
        Integer[] ints = JSON.toObject(json, int[].class);
        want.array(ints).sizeEq(3).reflectionEq(new int[] { 1, 2, 3 });
    }
}