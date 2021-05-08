package com.example.notesapp.db;

import java.io.Serializable;

public class Note implements Serializable {

    private int _id;
    private String Content;

    public Note() {
    }

    public Note(String content) {
        Content = content;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public String toString() {
        return "Note{" +
                "_id=" + _id +
                ", Content='" + Content + '\'' +
                '}';
    }
}
