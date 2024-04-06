package com.group2.CareHub.checking.rest;

import com.group2.CareHub.common.enumeration.CheckingType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckingRequestBody {

    private int childId;
    private CheckingType checkingType;
}
