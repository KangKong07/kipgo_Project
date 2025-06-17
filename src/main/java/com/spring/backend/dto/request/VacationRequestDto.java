package com.spring.backend.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationRequestDto {
    private int week;
    private String weekStaDate;
    private String weekEndDate;
    private String vacationYn;
}
