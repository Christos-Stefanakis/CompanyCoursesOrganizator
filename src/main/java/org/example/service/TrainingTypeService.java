package org.example.service;

import lombok.Getter;
import lombok.Setter;
import org.example.model.Manufacturer;
import org.example.model.Product;
import org.example.model.TrainingType;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class TrainingTypeService implements  IdMaxValue, AutoIdEditor{

    List <TrainingType> trainingTypeList =new ArrayList<>();

    public void addTrainingType(String name, String company, String description){
        this.trainingTypeList.add(new TrainingType(autoIdEditor(), name, company, description));
    }

    public void updateTrainingType(int id, String name, String company, String description){
        TrainingType updateTrainingType = getTrainingTypeById(id);
        updateTrainingType.setName(name);
        updateTrainingType.setCompany(company);
        updateTrainingType.setDescription(description);
    }

    @Override
    public int autoIdEditor() {
        if(this.trainingTypeList.isEmpty()){
            return 1;
        }else{
            for (int i = 1; i <= idMaxValue(); i++) {
                int finalI = i;
                if(this.trainingTypeList.stream().noneMatch(e->e.getId() == finalI)){
                    return finalI;
                }
            }
        }return idMaxValue() + 1;
    }

    @Override
    public int idMaxValue() {
        return this.trainingTypeList.stream()
                .map(TrainingType::getId)
                .max(Integer::compareTo).get();
    }

    public <T> List<TrainingType> findTrainingType(T text){
        List<TrainingType> trainingTypes = new ArrayList<>();
        if (text instanceof Number) {
            trainingTypes.add(getTrainingTypeById(((Number) text).intValue()));
        }
        if(text instanceof String) {
            trainingTypes.addAll(findTrainingTypeByName((String) text));
            trainingTypes.addAll(findTrainingTypeByCompany((String) text));
            trainingTypes.addAll(findTrainingTypeByDescription((String) text));
        }
        return trainingTypes;
    }

    public TrainingType getTrainingTypeById(int id){
        return this.trainingTypeList.stream().filter(p -> p.getId() == id).findFirst().get();
    }

    public List<TrainingType> findTrainingTypeByCompany(String text){
        return this.trainingTypeList.stream().filter(t->t.getCompany().toLowerCase().contains(text.toLowerCase())).toList();
    }

    public List<TrainingType> findTrainingTypeByDescription(String text){
        return this.trainingTypeList.stream().filter(t->t.getDescription().toLowerCase().contains(text.toLowerCase())).toList();
    }

    public List<TrainingType> findTrainingTypeByName(String text){
        return this.trainingTypeList.stream().filter(t->t.getName().toLowerCase().contains(text.toLowerCase())).toList();
    }
}
