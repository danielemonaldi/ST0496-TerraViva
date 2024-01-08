package it.unicam.cs.ids.TerraViva.Models.Contributes;

import it.unicam.cs.ids.TerraViva.Models.User;

public abstract class Content<T> implements Contribute<T> {
    private final User author;
    private T data;

    public Content(User author, T data) {
        this.author = author;
        this.data = data;
    }

    public User getAuthor() {
        return author;
    }

    @Override
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
