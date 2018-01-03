package fr.alteca.poc.pojo;

import java.io.Serializable;

public class RetourService<T> implements Serializable {

    private T content;

    public RetourService(T content) {
        this.content = content;
    }

    /**
     * Obtient le contenu du wrapper.
     *
     * @return T
     */
    public T getContent() {
        return this.content;
    }

}
