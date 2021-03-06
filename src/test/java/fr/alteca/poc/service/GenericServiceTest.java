package fr.alteca.poc.service;

import fr.alteca.poc.exception.CustomException;
import fr.alteca.poc.pojo.RetourService;
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
        String fileFormat = System.getProperty("fileFormat");

        
        List<TestData> result = new ArrayList<TestData>();

      // List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
      //classLoadersList.add(ClasspathHelper.contextClassLoader());
      //classLoadersList.add(ClasspathHelper.staticClassLoader());
      //classLoadersList.add(ClassLoader.getSystemClassLoader());
      
      //Reflections reflections = new Reflections(new ConfigurationBuilder()
      //.setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
      //.setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
      //.filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("fr.alteca.poc"))));
          
        //List<TestData> result = new ArrayList<TestData>();

        Reflections reflections = new Reflections("fr.alteca.poc");
        Set<Class<? extends Service>> classes = reflections.getSubTypesOf(Service.class);

        for (Class<? extends Service> service : classes) {
            if (service.isInterface()) {
                continue;
            }

            List<Method> methods = Arrays.asList(service.getDeclaredMethods());
            for (Method method : methods) {
                if (method.getParameterCount() == 0) {
                    continue;
                }
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

        Class<? extends Service> clazz = (Class<? extends Service>) this.testData.getMethodToInvoke().getDeclaringClass();
        Service instance = null;
        try {
            instance = clazz.newInstance();
        } catch (InstantiationException e) {
            Assert.fail("Erreur lors de l'instaciation de " + clazz.getName() + " : " + e.getMessage());
        } catch (IllegalAccessException e) {
            Assert.fail("Erreur lors de l'instaciation de " + clazz.getName() + " : " + e.getMessage());
        }

        try {
            this.testData.getMethodToInvoke().invoke(instance, this.testData.getParametersTypes());
        } catch (IllegalAccessException e) {
            Assert.fail("Erreur lors de l'appel de " + this.testData.getMethodToInvoke().getName() + " : " + e.getMessage());
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof CustomException) {
                throw e.getTargetException();
            } else {
                Assert.fail("Erreur lors de l'appel de " + this.testData.getMethodToInvoke().getName() + " : " + e.getMessage());
            }
        }
    }

    @Test
    public void returnTypesTest() {
        boolean testInstance = this.testData.getReturnType().getClass().isInstance(RetourService.class);
        Assert.assertTrue(this.testData.getMethodToInvoke().toString(), testInstance);
    }

    private static List<TestData> getParamterTabs(Method method) {
        List<TestData> result = new ArrayList<TestData>();
        Class<?>[] parameterTypes = method.getParameterTypes();
        Class<?> returnType = method.getReturnType();
        for (int i = 0; i < method.getParameterTypes().length; i++) {
            Object[] var = new Object[parameterTypes.length];
            int index = 0;
            for (Class<?> clazz : parameterTypes) {
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
            result.add(new TestData(method, var, returnType));
        }

        return result;
    }
}


