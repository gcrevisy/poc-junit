package fr.alteca.poc.service;

import org.junit.Assert;
import org.junit.Test;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class GenericServiceTest {

    @Test
    public void nullParametersTest() {
        Reflections reflections = new Reflections("fr.alteca.poc");

        String errors = "";

        Set<Class<? extends Service>> classes = reflections.getSubTypesOf(Service.class);

        for (Class<? extends Service> service : classes) {
            if (!service.isInterface()) {
                Service newInstance = null;
                try {
                    newInstance = service.newInstance();
                } catch (InstantiationException e) {
                    errors += service.getCanonicalName();
                } catch (IllegalAccessException e) {
                    errors += service.getCanonicalName();
                }
                List<Method> methods = Arrays.asList(service.getDeclaredMethods());

                for (Method method : methods) {
                    try {
                        if (method.getParameterCount() > 0){
                            method.invoke(newInstance, null);
                        }
                    } catch (InvocationTargetException e) {
                        errors += service.getCanonicalName() + "#" + method.getName();
                    } catch (IllegalAccessException e) {
                        errors += service.getCanonicalName() + "#" + method.getName();
                    }
                }
            }
        }

        Assert.assertEquals("", errors);

    }
}
