package org.test4j.testng.spring.utility;

import org.test4j.module.spring.annotations.AutoBeanInject;
import org.test4j.module.spring.annotations.SpringBeanByName;
import org.test4j.module.spring.annotations.SpringContext;
import org.test4j.testng.Test4J;
import org.testng.annotations.Test;

@Test(groups = "test4j")
@SpringContext("org/test4j/module/spring/testedbeans/xml/data-source.xml")
@AutoBeanInject
public class Test4JReflectorTest_Proxy extends Test4J {
    @SpringBeanByName(claz = MyImpl.class)
    MyIntf myIntf;

    public void testProxy_SetValueInotSpringProxy() {
        reflector.setField(myIntf, "myHello", "proxy private field");
        String hello = myIntf.hello();
        want.string(hello).isEqualTo("proxy private field");
    }

    public void testProxy_GetValueFromSpringProxy() {
        reflector.invoke(myIntf, "initHell", "get proxy private field");
        String hello = (String) reflector.getField(myIntf, "myHello");
        want.string(hello).isEqualTo("get proxy private field");
    }

    public void testProxy_CallSpringProxyPrivateMethod() {
        reflector.invoke(myIntf, "initHell", "proxy private method");
        String hello = myIntf.hello();
        want.string(hello).isEqualTo("proxy private method");
    }

    public static interface MyIntf {
        String hello();
    }

    public static class MyImpl implements MyIntf {
        private String myHello = "my hello";

        @SuppressWarnings("unused")
        private void initHell(String hello) {
            this.myHello = hello;
        }

        @Override
        public String hello() {
            return myHello;
        }
    }
}
