package fr.alteca.poc.service;

import java.lang.reflect.Method;

public class TestData {

    private Method methodToInvoke;

    private Object[] parametersTypes;

    private Object returnType;

    public TestData(Method methodToInvoke, Object[] parametersTypes, Object returnType) {
        this.methodToInvoke = methodToInvoke;
        this.parametersTypes = parametersTypes;
        this.returnType = returnType;
    }

    public Method getMethodToInvoke() {
        return this.methodToInvoke;
    }

    public void setMethodToInvoke(Method methodToInvoke) {
        this.methodToInvoke = methodToInvoke;
    }

    public Object[] getParametersTypes() {
        return this.parametersTypes;
    }

    public void setParametersTypes(Object[] parametersTypes) {
        this.parametersTypes = parametersTypes;
    }

    public Object getReturnType() {
        return this.returnType;
    }

    public void setReturnType(Object returns) {
        this.returnType = returns;
    }
}
