package org.test4j.hamcrest.iassert.object.impl;

import org.junit.Test;
import org.test4j.junit.Test4J;

public class ArrayAssertTest extends Test4J {
    @Test
    public void hasItems() {
        want.array(new String[] { "first item", "second item", "third item" }).hasAllItems("first item", "second item");
    }
}
