package org.example.model;
import java.util.Date;
import java.util.List;

public class Training {
    private int id;
    private String location;
    private Date date;
    private int participants;
    private TrainingType trainingType;
    private List<Person> persons;

    public Training() {
    }

    public Training(int id, Date date, String location, int participants, TrainingType trainingType, List<Person> persons) {
        this.id = id;
        this.location = location;
        this.participants = participants;
        this.date = date;
        this.trainingType = trainingType;
        this.persons = persons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public TrainingType getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
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
