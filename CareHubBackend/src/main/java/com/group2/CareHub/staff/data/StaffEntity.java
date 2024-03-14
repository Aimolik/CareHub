package com.group2.CareHub.staff.data;

import com.group2.CareHub.account.data.AccountEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "staffs")
public class StaffEntity extends AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int staffId;
    private String position;
    private String contactInfo;

    private StaffEntity(Builder builder) {
        super(builder.name, builder.email, builder.password);
        position = builder.position;
        contactInfo = builder.contactInfo;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String name;
        private String email;
        private String password;
        private String position;
        private String contactInfo;

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

        public Builder position(String val) {
            position = val;
            return this;
        }

        public Builder contactInfo(String val) {
            contactInfo = val;
            return this;
        }

        public StaffEntity build() {
            return new StaffEntity(this);
        }
    }
}
