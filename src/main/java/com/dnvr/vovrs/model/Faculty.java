package com.dnvr.vovrs.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Faculty extends StructuralUnit{

    private String deanName;

    public Faculty(long id, String facultyName, String deanName){
        super(id, facultyName);
        this.deanName = deanName;
    }
}
