package com.group2.CareHub.attendance.data;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "attendances")
public class AttendanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int attendanceId;
    private int childId;
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private AttendanceStatus attendanceStatus;
}
