package se.pensionsmyndigheten.se.tvattomat.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.pensionsmyndigheten.se.tvattomat.annotation.PersonalData;
import se.pensionsmyndigheten.se.tvattomat.annotation.WashProgram;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {

    @PersonalData(WashProgram = WashProgram.TRANSITION_TABLE)
    private String personalId;

    @PersonalData(WashProgram = WashProgram.REMOVE)
    private String name;

}
