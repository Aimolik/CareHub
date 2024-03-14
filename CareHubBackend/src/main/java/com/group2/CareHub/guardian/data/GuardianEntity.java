package com.group2.CareHub.guardian.data;

import com.group2.CareHub.account.data.AccountEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@Table(name = "guardians")
public class GuardianEntity extends AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int guardianId;
    private String phone;
    private String address;

    public GuardianEntity() {}

    private GuardianEntity(Builder builder) {
        super(builder.name, builder.email, builder.password);
        phone = builder.phone;
        address = builder.address;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static final class Builder {
        private String name;
        private String email;
        private String password;
        private String phone;
        private String address;

        private Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder password(String val) {
            password = val;
            return this;
        }

        public Builder phone(String val) {
            phone = val;
            return this;
        }

        public Builder address(String val) {
            address = val;
            return this;
        }

        public GuardianEntity build() {
            return new GuardianEntity(this);
        }
    }
}
