package com.engeto.filesexceptions;

public class Person {
    private String name;
    private int height;

    public Person(String name, int height) {
        this.name = name;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static Person parsePerson(String data) {
        String[] items = data.split(":");
        String name = items[0];
        int height = Integer.parseInt(items[1]);
        Person result = new Person(name, height);
        return result;
    }
}
