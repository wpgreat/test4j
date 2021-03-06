package org.test4j.hamcrest.matcher.property;

import org.junit.Test;
import org.test4j.fortest.beans.User;
import org.test4j.junit.Test4J;

@SuppressWarnings("serial")
public class PropertyEqMapTest extends Test4J {
    @Test(expected = AssertionError.class)
    public void testPropertyEqMap() {
        User user = new User();
        user.setAssistor(User.newUser("siri", new String[] { "139xxx", "159xxx" }));
        want.object(user).propertyEqMap(new DataMap() {
            {
                this.put("assistor.phones", new String[] { "133xxx", "131xxx" });
            }
        });
    }

    @Test(expected = AssertionError.class)
    public void testPropertyEqMap_List() {
        User[] users = new User[] { new User() {
            {
                setAssistor(User.newUser("siri", new String[] { "139xxx", "159xxx" }));
            }
        }, new User() {
            {
                setAssistor(User.newUser("wade", new String[] { "130xxx", "0571xx" }));
            }
        } };
        want.list(users).propertyEqMap(2, new DataMap() {
            {
                this.put("assistor.phones",// <br>
                        new String[] { "133xxx", "131xxx" },// <br>
                        new String[] { "130xxx", "0571xx" });
            }
        });
    }

    @Test
    public void testPropertyEqMap_List_Equals() {
        User[] users = new User[] { new User() {
            {
                setAssistor(User.newUser("siri", new String[] { "139xxx", "159xxx" }));
            }
        }, new User() {
            {
                setAssistor(User.newUser("wade", new String[] { "130xxx", "0571xx" }));
            }
        } };
        want.list(users).propertyEqMap(2, new DataMap() {
            {
                this.put("assistor.phones",// <br>
                        new String[] { "139xxx", "159xxx" },// <br>
                        new String[] { "130xxx", "0571xx" });
            }
        });
    }
}
