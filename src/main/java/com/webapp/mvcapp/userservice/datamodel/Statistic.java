package com.webapp.mvcapp.userservice.datamodel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@NoArgsConstructor
public class Statistic implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long id;

    @Getter @Setter private long responseCount;
    @Getter @Setter private float punctuality;
    @Getter @Setter private float funFactor;
}
