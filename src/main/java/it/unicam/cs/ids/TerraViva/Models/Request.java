package it.unicam.cs.ids.TerraViva.Models;

public abstract class Request {
    private final String author;
    private final String motivation;

    public Request(String author, String motivation) {
        this.author = author;
        this.motivation = motivation;
    }

    public String getAuthor() {
        return author;
    }

    public String getMotivation() {
        return motivation;
    }
}
