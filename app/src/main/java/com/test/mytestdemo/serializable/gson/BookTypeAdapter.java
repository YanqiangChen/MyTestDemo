package com.test.mytestdemo.serializable.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class BookTypeAdapter extends TypeAdapter<BookType> {

    @Override
    public BookType read(JsonReader in) throws IOException {
        final BookType book = new BookType();

        in.beginObject();
        while (in.hasNext()) {
            switch (in.nextName()) {
                case "isbn":
                    book.setIsbn(in.nextString());
                    break;
                case "title":
                    book.setTitle(in.nextString());
                    break;
                case "authors":
                    book.setAuthors(in.nextString().split(";"));
                    break;
            }
        }
        in.endObject();

        return book;
    }

    @Override
    public void write(JsonWriter out, BookType book) throws IOException {
        out.beginObject();
        out.name("isbn").value(book.getIsbn());
        out.name("title").value(book.getTitle());
        String str="";
        for(int i=0;i<book.getAuthors().length;i++){
            str+=book.getAuthors()[i]+";";

        }
        out.name("authors").value(str);
        out.endObject();
    }
}
