package com.group2.CareHub.guardian.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Data
@Table(name = "guardians")
public class GuardianEntity {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;

}
