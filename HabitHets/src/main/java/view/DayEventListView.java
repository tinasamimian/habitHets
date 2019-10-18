package view;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import main.model.Facade;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DayEventListView extends StackPane {
    private VBox vBox;
    private List<HBox> hBoxList;
    private Line tl;
    private double hHeight;
    private double vBoxWidth;
    private LocalDateTime ldt;

    public DayEventListView(LocalDateTime ldt) {
        this.ldt = ldt;
        hBoxList = new ArrayList<>();
        vBox = new VBox();
        for (int i = 0; i < 10; i++) {
            hBoxList.add(new HBox());
        }
        for (int i = 0; i < 24; i++) {
            HourView hour = new HourView();
            vBox.getChildren().add(hour);
        }
        this.getChildren().add(vBox);
        for (HBox hBox : hBoxList) {
            this.getChildren().add(hBox);
            hBox.setPickOnBounds(false);
        }
        setUpTimeLine();
        vBox.setPickOnBounds(false);
    }

    private void setUpTimeLine() {
        tl = new Line(0, 0, 2000, 0);
        tl.setStroke(Color.valueOf("#47bcad"));
        tl.setStrokeWidth(2);
        this.getChildren().add(tl);

        hHeight = new HourView().getPrefHeight();
    }

    public void updateDay(LocalDateTime ldt, double vBoxWidth) {
        Facade facade = new Facade();
        this.vBoxWidth = vBoxWidth;
        System.out.println(vBoxWidth);
        this.ldt = ldt;
        for (HBox hBox : hBoxList) {
            hBox.getChildren().clear();
        }
        int i = 0;
        int overlap = 0;
        //for (Event event : EventOrganizer.getInstant().getEventsOfDay(day.getLdt())) {
        for (int id : facade.getAllIdsOfDay(ldt))
            if (this.ldt.getDayOfYear() == ldt.getDayOfYear()) {
                i++;
                AnchorPane a = new EventView(facade.getEventFromID(id));
                a.setTranslateY((Integer.valueOf(facade.getEventFromID(id).getInfo().get(6)) * 120) + (Integer.valueOf(facade.getEventFromID(id).getInfo().get(7)) * 2)); //todo WTF
                double height = calculateLenght(id) * 2;
                a.setPrefHeight(height);
                double width = (vBoxWidth)/facade.calculateWidth(ldt,id);
                System.out.println("asdadsasdaa" + width);
                a.setPrefWidth((width));
                if (calculateTranslateX(id) != 0) {
                    if (overlap == 3) {
                        a.setTranslateX(calculateTranslateX(id) * 3);
                    } else if (overlap == 2) {
                        a.setTranslateX(calculateTranslateX(id) * 2);
                    } else if (overlap == 1) {
                        a.setTranslateX(calculateTranslateX(id));
                    }
                    overlap++;
                }
                hBoxList.get(i - 1).getChildren().add(a);
            }
    }

    public void updateTimeline(int hour, int minute) {
        double timeHeight = 0;
        if (hour < 12) {
            timeHeight = (12 - hour) * hHeight;
            timeHeight -= hHeight - (minute * 2);
            timeHeight *= -1;
        } else {
            timeHeight = (hour - 12) * hHeight;
            timeHeight += (minute * 2);
        }
        tl.setTranslateY(timeHeight);
    }

    private double calculateLenght(int id) {
        Facade facade = new Facade();
        return facade.getLength(id);

    }

    private double calculateTranslateX(int id) {
        Facade facade = new Facade();
        double x = vBoxWidth/facade.calculateWidth(ldt,id);
        if (x == vBoxWidth) {
            return 0;
        } else {
            return x;
        }
    }

    private LocalDateTime getDay() {
        return ldt;
    }
}
