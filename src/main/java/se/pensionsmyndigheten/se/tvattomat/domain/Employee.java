package se.pensionsmyndigheten.se.tvattomat.domain;

import lombok.*;
import se.pensionsmyndigheten.se.tvattomat.annotation.PersonalData;


@NoArgsConstructor
@Data
@ToString(callSuper = true)
public class Employee extends Person {

    @PersonalData
    private String employeeId;

    private String department;

    @Builder
    public Employee(String personalId, String name, String employeeId, String department) {
        super(personalId, name);
        this.department = department;
        this.employeeId = employeeId;
    }
}
