package se.pensionsmyndigheten.se.tvattomat.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class EmployeeTest {

    @Test
    public void employeeObjectToXml() throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
        String xml = xmlMapper.writeValueAsString(Employee.builder()
                .name("Adrian Hadjari")
                .personalId("20130101-1111")
                .employeeId("A1234")
                .department("R11").build());
        System.out.println("The XML:\n" + xml);
    }

    @Test
    public void xmlToEmployeeObject() throws IOException{
        String adrian = "<Employee>\n" +
                "  <personalId/>\n" +
                "  <name>Adrian Hadjari</name>\n" +
                "  <employeeId>A1234</employeeId>\n" +
                "  <department>R11</department>\n" +
                "</Employee>";
        XmlMapper xmlMapper = new XmlMapper();
        Employee employee = xmlMapper.readValue(adrian, Employee.class);
        assertTrue(employee.getDepartment().equals("R11") && employee.getEmployeeId().equals("A1234"));

    }



}