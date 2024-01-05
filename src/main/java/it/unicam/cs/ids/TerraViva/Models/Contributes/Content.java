package it.unicam.cs.ids.TerraViva.Models.Contributes;

public abstract class Content<T> implements Contribute<T> {
    private final String author;
    private T data;

    public Content(String author, T data) {
        this.author = author;
        this.data = data;
    }

    public String getAuthor() {
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
