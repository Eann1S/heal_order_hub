package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Map;

@Entity
@Table(
        name = "users",
        indexes = {@Index(columnList = "email"), @Index(columnList = "username")},
        uniqueConstraints = {@UniqueConstraint(columnNames = "email"), @UniqueConstraint(columnNames = "username")}
)
@Data
public class User {

    @Id
    private String id;
    private String email;
    private String username;
    @ElementCollection
    @CollectionTable(
            name = "user_contact_mapping",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    @MapKeyColumn(name = "contact")
    @Column(name = "contact_value")
    private Map<String, String> contactsMap;
}
