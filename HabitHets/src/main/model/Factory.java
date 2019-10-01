package main.model;

import java.time.LocalDateTime;

public class Factory {
    private static int id = 0;

    public static Event createBasicEvent(LocalDateTime start, LocalDateTime end, String title){
        Event createdBasicEvent = new Event(id,start, end, title);
        id++;
        return createdBasicEvent;
    }

    public static Event createAdvEvent(LocalDateTime start, LocalDateTime end, String title, String location, String desc, String color){
        Event createdAdvEvent = new Event(id,start, end, title,location,desc,color);
        id++;
        return createdAdvEvent;
    }
    public static Note createNote(String title, String description, LocalDateTime day){
        Note createdNote = new Note(id, title, description, day);
        id++;
        return createdNote;
    }
}
