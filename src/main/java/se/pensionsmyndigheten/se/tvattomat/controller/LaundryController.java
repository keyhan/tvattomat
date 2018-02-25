package se.pensionsmyndigheten.se.tvattomat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import se.pensionsmyndigheten.se.tvattomat.domain.Employee;
import se.pensionsmyndigheten.se.tvattomat.reflection.PersonDataWasher;

@RestController
@RequestMapping("/laundry")
public class LaundryController {

    @Autowired
    private PersonDataWasher washer;

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public Employee washEmployee(@RequestBody  Employee employee) {
        return (Employee) washer.washEmployee(employee);
    }
}
