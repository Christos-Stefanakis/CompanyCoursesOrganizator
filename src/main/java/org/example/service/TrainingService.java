package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Product;
import org.example.model.Training;
import org.example.model.TrainingType;

import java.util.*;
public class TrainingService implements  IdMaxValue, AutoIdEditor{

    @Getter private final List<Training> trainingSchedule = new ArrayList<>();

    private TrainingType trainingType;

    public void addTrainingTime(TrainingType trainingType, Date dateAndTime, String location) {
        trainingSchedule.add(new Training(autoIdEditor(), dateAndTime, location, trainingType));
    }

    public void updateTrainingTime(int id, TrainingType trainingType, Date date, String location){
        Training selectedTrainingTime = getTrainingScheduleById(id);
        selectedTrainingTime.setTrainingType(trainingType);
        selectedTrainingTime.setDate(date);
        selectedTrainingTime.setLocation(location);
    }

    public Date addTime(int year, int month, int date, int hour, int minute){
        return new Date(year,month,date,hour,minute);
    }

    public void removeTrainingTime(int id) {
        this.trainingSchedule.remove(getTrainingScheduleById(id));
    }

    public Training getTrainingScheduleById(int id){
        return this.trainingSchedule.stream().filter(t->t.getId() == id).findFirst().get();
    }

    public List<Training> findTrainingScheduleByDate(Date dateAndTime){
        return this.trainingSchedule.stream().filter(t->t.getDate() == dateAndTime).toList();
    }

    public List<Training> findTrainingScheduleByLocation(String location){
        return this.trainingSchedule.stream().filter(t->t.getLocation().toLowerCase().contains(location.toLowerCase())).toList();
    }

    public List<Training> findTrainingScheduleByTrainingType(TrainingType trainingType){
        return this.trainingSchedule.stream().filter(t->t.getTrainingType() == trainingType).toList();
    }


    @Override
    public int autoIdEditor() {
        if(this.trainingSchedule.isEmpty()){
            return 1;
        }else{
            for (int i = 1; i <= idMaxValue(); i++) {
                int finalI = i;
                if(this.trainingSchedule.stream().noneMatch(e->e.getId() == finalI)){
                    return finalI;
                }
            }
        }return idMaxValue() + 1;
    }

    @Override
    public int idMaxValue() {
        return this.trainingSchedule.stream()
                .map(Training::getId)
                .max(Integer::compareTo).get();
    }

    public <T> List<Training> findScheduleTime(T text){
        List<Training> scheduleTime = new ArrayList<>();
        if (text instanceof Number) {
            scheduleTime.add(getTrainingScheduleById(((Number)text).intValue()));
        }
        if(text instanceof String) {
            scheduleTime.addAll(findTrainingScheduleByLocation((String) text));
        }
        if(text instanceof Date){
            scheduleTime.addAll(findTrainingScheduleByDate((Date) text));
        }
        return scheduleTime;
    }
}
