package com.aaqua.data.processor;

public class DPFile {
    String name;
    int size;

    public DPFile(String name,int size ) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
