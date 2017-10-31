package com.webapp.mvcapp.userservice.datamodel;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Builder
@NoArgsConstructor
public class AppUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter private long id;

    @Column(nullable = false, unique = true)
    @Getter @Setter private String login;
    @Getter @Setter private String password;
    @Getter @Setter private String name;
    @Getter @Setter private String description;
    @Getter @Setter private long photoId;
    @Getter @Setter private long stateId;
}
