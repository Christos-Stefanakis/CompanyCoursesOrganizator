package org.example.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
public class Training {
    int id;
    private String location;
    private Date date;
    private TrainingType trainingType;

    public Training(int id, Date date, String location, TrainingType trainingType) {
        this.id = id;
        this.location = location;
        this.date = date;
        this.trainingType = trainingType;
    }

    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", date=" + date +
                ", trainingType=" + trainingType +
                '}';
    }
}
