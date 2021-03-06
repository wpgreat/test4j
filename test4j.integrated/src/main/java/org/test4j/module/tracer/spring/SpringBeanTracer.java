package org.test4j.module.tracer.spring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.test4j.module.tracer.TracerHelper;
import org.test4j.module.tracer.TracerManager;

@SuppressWarnings("rawtypes")
public class SpringBeanTracer implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        boolean tracerEnabled = TracerHelper.doesTracerEnabled();
        if (tracerEnabled) {
            return this.tracerProceed(invocation);
        } else {
            return invocation.proceed();
        }
    }

    private Object tracerProceed(MethodInvocation invocation) throws Throwable {
        Class beanClazz = invocation.getThis().getClass();
        String method = invocation.getMethod().getName();
        Object[] paras = invocation.getArguments();
        TracerManager.traceBeanInputs(beanClazz, method, paras);
        try {
            Object result = invocation.proceed();
            TracerManager.traceBeanReturn(beanClazz, method, result);
            return result;
        } catch (Throwable e) {
            TracerManager.traceBeanException(beanClazz, method, e);
            throw e;
        }
    }

    /**
     * 增加自动跟踪的auto tracer bean definition
     * 
     * @param beanFactory
     */
    public static void addTracerBeanDefinition(final BeanDefinitionRegistry beanFactory) {
        AbstractBeanDefinition pointcut = new GenericBeanDefinition();
        pointcut.setBeanClassName(TracerMethodRegexPointcut.class.getName());
        pointcut.setScope("singleton");
        pointcut.setAutowireCandidate(false);

        pointcut.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_NO);

        pointcut.setLazyInit(true);
        beanFactory.registerBeanDefinition("test4j-internal-methodname-pointcut", pointcut);

        AbstractBeanDefinition advice = new GenericBeanDefinition();
        advice.setBeanClassName(SpringBeanTracer.class.getName());
        advice.setScope("singleton");
        advice.setAutowireCandidate(false);
        advice.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_NO);

        advice.setLazyInit(true);
        beanFactory.registerBeanDefinition("test4j-internal-springbeantracer", advice);

        AbstractBeanDefinition advisor = new GenericBeanDefinition();
        advisor.setBeanClassName(DefaultPointcutAdvisor.class.getName());
        advisor.setScope("singleton");
        advisor.setAutowireCandidate(false);

        advisor.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_NO);
        advisor.getPropertyValues().addPropertyValue("pointcut",
                new RuntimeBeanReference("test4j-internal-methodname-pointcut"));
        advisor.getPropertyValues().addPropertyValue("advice",
                new RuntimeBeanReference("test4j-internal-springbeantracer"));

        advisor.setLazyInit(true);
        beanFactory.registerBeanDefinition("test4j-internal-beantracer-advisor", advisor);
    }
}
