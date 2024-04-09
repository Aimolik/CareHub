package com.group2.CareHub.child.data;


import com.group2.CareHub.attendance.data.AttendanceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "children")
public class ChildEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int childId;
    private int guardianId;
    private String name;
    private LocalDate dateOfBirth;
    private String address;
    private String medicalInformation;
    @Enumerated(EnumType.STRING)
    private AttendanceStatus attendanceStatus;

    public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }
}
