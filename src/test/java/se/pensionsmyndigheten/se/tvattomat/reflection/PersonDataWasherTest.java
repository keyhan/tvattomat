package se.pensionsmyndigheten.se.tvattomat.reflection;

import org.junit.Test;
import se.pensionsmyndigheten.se.tvattomat.domain.Employee;

public class PersonDataWasherTest {

    @Test
    public void testWashing() {
        Employee adrian = Employee.builder()
                .name("Adrian Hadjari")
                .department("3334444")
                .employeeId("1234")
                .personalId("130124-1111")
                .build();

        PersonDataWasher personDataWasher = new PersonDataWasher();

        final Employee employee = (Employee) personDataWasher.washEmployee(adrian);

        System.out.println("employee = " + employee);
    }

}