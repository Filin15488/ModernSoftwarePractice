package com.Filin.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    private int id;
    private int RoleId;
    private String FirstName;
    private String LastName;
    private GregorianCalendar birthday;

}
