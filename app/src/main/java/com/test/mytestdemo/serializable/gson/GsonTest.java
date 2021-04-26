package com.test.mytestdemo.serializable.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStreamReader;
import java.io.Reader;

public class GsonTest {

    public void test(){
//        final GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(Book.class, new BookSerialiser());
//        gsonBuilder.setPrettyPrinting();
//        final Gson gson = gsonBuilder.create();
//
//        final Book javaPuzzlers = new Book();
//        javaPuzzlers.setTitle("Java Puzzlers: Traps, Pitfalls, and Corner Cases");
//        javaPuzzlers.setIsbn10("032133678X");
//        javaPuzzlers.setIsbn13("978-0321336781");
//        javaPuzzlers.setAuthors(new String[] { "Joshua Bloch", "Neal Gafter" });
//
//        // Format to JSON
//        final String json = gson.toJson(javaPuzzlers);
//        System.out.println(json);

//        GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(Book.class, new BookDeserializer());
//        Gson gson = gsonBuilder.create();
//
//        Book book = gson.fromJson(Book.json, Book.class);
//        System.out.println(book);

//        final GsonBuilder gsonBuilder = new GsonBuilder();
//        gsonBuilder.registerTypeAdapter(Book.class, new BookTypeAdapter());
//        final Gson gson = gsonBuilder.create();

        // Configure GSON
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LargeData.class, new LargeDataSerialiser());
        gsonBuilder.setPrettyPrinting();
        final Gson gson = gsonBuilder.create();
        final LargeData data = new LargeData();
        data.create(10485760);
        final String json = gson.toJson(data);

    }
    public void gsonTest(){
        String jsonString = "{\"name\":\"renyiguang\"}";
        Gson gson = new Gson();
        Book book = gson.fromJson(jsonString, Book.class);



    }
}
