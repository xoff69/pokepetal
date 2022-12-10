package com.xoff.pokepetal.model;

public class Pokemon {

    private final long id;
    private final String content;

    public Pokemon(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}
