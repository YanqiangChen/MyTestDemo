package com.test.mytestdemo.serializable.gson;

public class Book {
    public static final String json="{\n" +
            "  \"title\": \"Java Puzzlers: Traps, Pitfalls, and Corner Cases\",\n" +
            "  \"isbn-10\": \"032133678X\",\n" +
            "  \"isbn-13\": \"978-0321336781\",\n" +
            "  \"authors\": [\n" +
            "    \"Joshua Bloch\",\n" +
            "    \"Neal Gafter\"\n" +
            "  ]\n" +
            "}";

    private String[] authors;
    private String isbn10;
    private String isbn13;
    private String title;

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
