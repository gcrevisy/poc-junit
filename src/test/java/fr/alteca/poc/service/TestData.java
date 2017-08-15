package fr.alteca.poc.service;

import java.lang.reflect.Method;

public class TestData {

    private Method methodToInvoke;

    private Object[] params;

    public TestData(Method methodToInvoke, Object[] params) {
        this.methodToInvoke = methodToInvoke;
        this.params = params;
    }

    public Method getMethodToInvoke() {
        return methodToInvoke;
    }

    public void setMethodToInvoke(Method methodToInvoke) {
        this.methodToInvoke = methodToInvoke;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
