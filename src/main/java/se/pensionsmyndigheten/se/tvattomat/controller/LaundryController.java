package se.pensionsmyndigheten.se.tvattomat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.pensionsmyndigheten.se.tvattomat.domain.Employee;
import se.pensionsmyndigheten.se.tvattomat.reflection.PersonDataWasher;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/laundry")
public class LaundryController {

    @Autowired
    private PersonDataWasher washer;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public List<Employee> washEmployee(@RequestBody List<Employee> employees) {
        return employees.stream().map(employee -> (Employee) washer.washEmployee(employee)).collect(Collectors.toList());
    }
}
