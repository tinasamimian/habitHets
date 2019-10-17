package main.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventHandler implements IHandler {

    private static EventHandler instant;
    private static List<Event> eventList;
    private int i; //todo

    private EventHandler() {
        this.eventList = new ArrayList<>();
        i = 0;
    }

    public static EventHandler getInstant() {
        if (instant == null) {

            instant = new EventHandler();
            return instant;

        } else {
            return instant;
        }
    }


    @Override
    public void add() { //todo
        if (i == 0) {
            eventList.add(Factory.createBasicEvent(LocalDateTime.of(2019, 10, 9, 12, 30), LocalDateTime.of(2019, 10, 9, 13, 30), "hello3"));
        } else if (i == 1) {
            eventList.add(Factory.createBasicEvent(LocalDateTime.of(2019, 10, 9, 11, 00), LocalDateTime.of(2019, 10, 9, 12, 00), "hello"));
        } else if (i == 2) {
            eventList.add(Factory.createBasicEvent(LocalDateTime.of(2019, 10, 9, 11, 45), LocalDateTime.of(2019, 10, 9, 12, 45), "hello2"));
        } else if (i == 3) {
            eventList.add(Factory.createBasicEvent(LocalDateTime.of(2019, 10, 9, 21, 45), LocalDateTime.of(2019, 10, 9, 22, 45), "hello4"));
        }else if (i == 4) {
            eventList.add(Factory.createBasicEvent(LocalDateTime.of(2019, 10, 9, 9, 10), LocalDateTime.of(2019, 10, 9, 10, 30), "hello4"));
        }
        i++;
    }

    public void addEvent(LocalDateTime ldt, Integer startHour, Integer startMinute, Integer endHour, Integer endMinute, String title, String location, String desc){
        eventList.add(Factory.createAdvEvent(ldt.withHour(startHour).withMinute(startMinute), ldt.withHour(endHour).withMinute(endMinute), title, location, desc, null));
    }

    @Override
    public void remove(int id) {
        for (Event event : eventList){
            if (event.getId() == id){
                eventList.remove(event);
                return;
            }
        }
        System.out.println("The ID: '" + id + "' does not exist");
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public List<Event> getEventsOfDay(LocalDateTime ldt){
        List<Event> eventList = new ArrayList<>();
        for (Event event : getEventList()){
            if (event.getStartTime().getYear() == ldt.getYear() && event.getStartTime().getDayOfYear() == ldt.getDayOfYear()){
                eventList.add(event);
            }
        }
        return eventList;
    }

    public Event getEventOfId(int id){
        for (Event event : eventList){
            if (event.getId() == id){
                return event;
            } else {
                System.out.println("NO SUCH EVENT");
            }
        }
        return null;
    }
}
