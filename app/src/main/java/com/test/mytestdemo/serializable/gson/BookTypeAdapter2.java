package com.test.mytestdemo.serializable.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookTypeAdapter2 extends TypeAdapter<BookType> {

    @Override
    public void write(JsonWriter out, BookType book) throws IOException {
        out.beginArray();
        out.value(book.getIsbn());
        out.value(book.getTitle());
        for (final String author : book.getAuthors()) {
            out.value(author);
        }
        out.endArray();
    }

    @Override
    public BookType read(JsonReader in) throws IOException {
        final BookType book = new BookType();

        in.beginArray();
        book.setIsbn(in.nextString());
        book.setTitle(in.nextString());
        final List<String> authors = new ArrayList<>();
        while (in.hasNext()) {
            authors.add(in.nextString());
        }
        book.setAuthors(authors.toArray(new String[authors.size()]));
        in.endArray();

        return book;
    }
}
