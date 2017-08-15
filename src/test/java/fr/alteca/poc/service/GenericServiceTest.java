package fr.alteca.poc.service;

import fr.alteca.poc.exception.CustomException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RunWith(Parameterized.class)
public class GenericServiceTest {

    private final TestData testData;

    @Parameterized.Parameters
    public static List<TestData> getParams() {
        List<TestData> result = new ArrayList<TestData>();

        Reflections reflections = new Reflections("fr.alteca.poc");
        Set<Class<? extends Service>> classes = reflections.getSubTypesOf(Service.class);

        for (Class<? extends Service> service : classes) {
            if (service.isInterface()) {
                continue;
            }

            List<Method> methods = Arrays.asList(service.getDeclaredMethods());
            for (Method method : methods) {
                if (method.getParameterCount() == 0)
                    continue;
                result.addAll(getParamterTabs(method));
            }
        }
        return result;
    }

    public GenericServiceTest(TestData item) {
        this.testData = item;
    }

    @Test(expected = CustomException.class)
    public void nullParametersTest() throws Throwable {

        Class<? extends Service> clazz = (Class<? extends Service>) testData.getMethodToInvoke().getDeclaringClass();
        Service instance = null;
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException e) {
            Assert.fail("Erreur lors de l'instaciation de " + clazz.getName() + " : " + e.getMessage());
        } catch (IllegalAccessException e) {
            Assert.fail("Erreur lors de l'instaciation de " + clazz.getName() + " : " + e.getMessage());
        }

        try {
            testData.getMethodToInvoke().invoke(instance, testData.getParams());
        } catch (IllegalAccessException e) {
            Assert.fail("Erreur lors de l'appel de " + testData.getMethodToInvoke().getName() + " : " + e.getMessage());
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof CustomException) {
                throw e.getTargetException();
            } else {
                Assert.fail("Erreur lors de l'appel de " + testData.getMethodToInvoke().getName() + " : " + e.getMessage());
            }
        }
    }

    private static List<TestData> getParamterTabs(Method method) {
        List<TestData> result = new ArrayList<TestData>();
        Class<?>[] parameterTypes = method.getParameterTypes();
        for (int i = 0; i < method.getParameterTypes().length; i++) {
            Object[] var = new Object[parameterTypes.length];
            int index = 0;
            for (Class clazz : parameterTypes) {
                if (i == index) {
                    var[index] = null;
                } else {
                    try {
                        var[index] = clazz.newInstance();
                    } catch (InstantiationException e) {
                        Assert.fail("Erreur lors de l'instaciation de " + clazz.getName() + " : " + e.getMessage());
                        var[index] = null;
                    } catch (IllegalAccessException e) {
                        Assert.fail("Erreur lors de l'instaciation de " + clazz.getName() + " : " + e.getMessage());
                        var[index] = null;
                    }
                }
                index++;
            }
            result.add(new TestData(method, var));
        }

        return result;
    }
}


