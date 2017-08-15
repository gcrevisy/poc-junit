package fr.alteca.poc.service;

import fr.alteca.poc.exception.CustomException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RunWith(Parameterized.class)
public class GenericServiceTest {

    private final List<TestData> testDatas;

    @Parameterized.Parameters
    public static List<TestData> getParams() {
        List<TestData> result = new ArrayList<TestData>();


        return result;
    }

    public GenericServiceTest(List<TestData> items) {
        this.testDatas = items;
    }

    @Test
    public void nullParametersTest() throws IllegalAccessException, InstantiationException {
        Reflections reflections = new Reflections("fr.alteca.poc");

        String errors = "";

        Set<Class<? extends Service>> classes = reflections.getSubTypesOf(Service.class);

        for (Class<? extends Service> service : classes) {
            if (service.isInterface()) {
                continue;
            }

            Service newInstance = service.newInstance();
            List<Method> methods = Arrays.asList(service.getDeclaredMethods());

            for (Method method : methods) {
                if (method.getParameterCount() == 0)
                    continue;
                try {
                    List<Object[]> argsList = getParamterTabs(method.getParameterTypes());
                    for (Object[] args : argsList)
                        method.invoke(newInstance, args);
                    method.getDeclaringClass();
                } catch (Exception e) {
                    if (!(e instanceof CustomException || (e.getCause() != null && e.getCause() instanceof CustomException))) {
                        errors += " \r\n " + service.getName() + "#" + method.getName() + "#" + e.getMessage();
                    } else {
                        Assert.assertTrue(service.getName() + "#" + method.getName() + "#" + e.getMessage(), true);
                    }
                    continue;
                }
                errors += " \r\n " + service.getName() + "#" + method.getName() + "#pas de CustomException";
            }
        }

        Assert.assertEquals("", errors);
    }

    private List<Object[]> getParamterTabs(Class<?>[] parameterTypes) throws IllegalAccessException, InstantiationException {
        List<Object[]> result = new ArrayList<Object[]>();

        for (int i = 0; i < parameterTypes.length; i++) {
            Object[] var = new Object[parameterTypes.length];
            int index = 0;
            for (Class clazz : parameterTypes) {
                if (i == index) {
                    var[index] = null;
                } else {
                    var[index] = clazz.newInstance();
                }
                index++;
            }
            result.add(var);
        }

        return result;
    }
}


