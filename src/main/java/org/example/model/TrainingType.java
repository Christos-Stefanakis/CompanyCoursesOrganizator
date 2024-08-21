package org.example.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TrainingType {
    private int id;
    private String name;
    private String company;
    private String description;

    public TrainingType(int id, String name, String company, String description) {
        this.id = id;
        this.name = name;
        this.company = company;
        this.description = description;
    }

    @Override
    public String toString() {
        return "TrainingType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
