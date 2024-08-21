package org.example.service;

import org.example.model.TrainingType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TrainingTypeServiceTest {
    TrainingTypeService trainingTypeService = new TrainingTypeService();
    TrainingService trainingService =new TrainingService();

    @Test
    void addTrainingType() {

        trainingTypeService.addTrainingType("AA", "Company", "omething");
        assertEquals(1, trainingTypeService.getTrainingTypeList().size());
    }

    @Test
    void updateTrainingType() {
       TrainingType trainingType1 = new TrainingType(1, "AA", "Company", "omething");
       trainingTypeService.trainingTypeList.add(trainingType1);
       trainingTypeService.updateTrainingType(1,"BB","OtherCompany", "nothing");
        assertEquals("BB", trainingType1.getName());
        assertEquals("OtherCompany", trainingType1.getCompany());
        assertEquals("nothing", trainingType1.getDescription());
    }


    @Test
    void getTrainingTypeById() {
        TrainingType trainingType1 = new TrainingType(1, "AA", "Company", "something");
        TrainingType trainingType2 = new TrainingType(2, "BB", "Team", "kati");
        TrainingType trainingType3 = new TrainingType(3, "CC", "Omada", "neco");
        trainingTypeService.trainingTypeList.add(trainingType1);
        trainingTypeService.trainingTypeList.add(trainingType2);
        trainingTypeService.trainingTypeList.add(trainingType3);

        assertEquals(trainingType2, trainingTypeService.getTrainingTypeById(2));
        assertEquals(trainingType1, trainingTypeService.getTrainingTypeById(1));
        assertEquals(trainingType3, trainingTypeService.getTrainingTypeById(3));
    }

    @Test
    void findTrainingTypeByCompany() {
        TrainingType trainingType1 = new TrainingType(1, "AA", "Company", "something");
        TrainingType trainingType2 = new TrainingType(2, "BB", "Team", "kati");
        TrainingType trainingType3 = new TrainingType(3, "CC", "Omada", "neco");
        trainingTypeService.trainingTypeList.add(trainingType1);
        trainingTypeService.trainingTypeList.add(trainingType2);
        trainingTypeService.trainingTypeList.add(trainingType3);

        assertEquals(trainingType1, trainingTypeService.findTrainingTypeByCompany("com").stream().findFirst().get());
        assertEquals(trainingType2, trainingTypeService.findTrainingTypeByCompany("ea").stream().findFirst().get());
        assertEquals(trainingType3, trainingTypeService.findTrainingTypeByCompany("omada").stream().findFirst().get());
    }

    @Test
    void findTrainingTypeByDescription() {
        TrainingType trainingType1 = new TrainingType(1, "AA", "Company", "something");
        TrainingType trainingType2 = new TrainingType(2, "BB", "Team", "kati");
        TrainingType trainingType3 = new TrainingType(3, "CC", "Omada", "neco");
        trainingTypeService.trainingTypeList.add(trainingType1);
        trainingTypeService.trainingTypeList.add(trainingType2);
        trainingTypeService.trainingTypeList.add(trainingType3);

        assertEquals(trainingType1, trainingTypeService.findTrainingTypeByDescription("some").stream().findFirst().get());
        assertEquals(trainingType2, trainingTypeService.findTrainingTypeByDescription("kat").stream().findFirst().get());
        assertEquals(trainingType3, trainingTypeService.findTrainingTypeByDescription("eco").stream().findFirst().get());
    }

    @Test
    void findTrainingType() {
        TrainingType trainingType1 = new TrainingType(1, "AA", "Company", "something");
        TrainingType trainingType2 = new TrainingType(2, "BB", "Team", "kati");
        TrainingType trainingType3 = new TrainingType(3, "CC", "Omada", "neco");
        trainingTypeService.trainingTypeList.add(trainingType1);
        trainingTypeService.trainingTypeList.add(trainingType2);
        trainingTypeService.trainingTypeList.add(trainingType3);

        assertEquals(trainingType1, trainingTypeService.findTrainingType("aa").stream().findFirst().get());
        assertEquals(trainingType2, trainingTypeService.findTrainingType("ea").stream().findFirst().get());
        assertEquals(trainingType3, trainingTypeService.findTrainingType("neco").stream().findFirst().get());
        assertEquals(trainingType3, trainingTypeService.findTrainingType(3).stream().findFirst().get());
    }

    @Test
    void findTrainingTypeByName() {
        TrainingType trainingType1 = new TrainingType(1, "AA", "Company", "something");
        TrainingType trainingType2 = new TrainingType(2, "BB", "Team", "kati");
        TrainingType trainingType3 = new TrainingType(3, "CC", "Omada", "neco");
        trainingTypeService.trainingTypeList.add(trainingType1);
        trainingTypeService.trainingTypeList.add(trainingType2);
        trainingTypeService.trainingTypeList.add(trainingType3);

        assertEquals(trainingType1, trainingTypeService.findTrainingTypeByName("aa").stream().findFirst().get());
        assertEquals(new ArrayList<>(), trainingTypeService.findTrainingTypeByName("ll"));
    }
}