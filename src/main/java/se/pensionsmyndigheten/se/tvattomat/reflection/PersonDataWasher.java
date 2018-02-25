package se.pensionsmyndigheten.se.tvattomat.reflection;

import org.springframework.stereotype.Service;
import se.pensionsmyndigheten.se.tvattomat.annotation.PersonalData;

import java.lang.reflect.Field;
import java.util.Arrays;

@Service
public class PersonDataWasher {

    public Object washEmployee(Object employee) {

        Field[] fields = employee.getClass().getDeclaredFields();
        washIt(employee, fields);

        Class<?> superclass = employee.getClass().getSuperclass();

        while(superclass != null) {
            Field[] superFields = superclass.getDeclaredFields();
            washIt(employee, superFields);
            superclass = superclass.getSuperclass();
        }


        return employee;
    }

    private void washIt(Object result, Field[] fields) {
        Arrays.stream(fields)
                .forEach(field -> {
                    final PersonalData annotation = field.getAnnotation(PersonalData.class);
                    if(annotation != null) {
                        field.setAccessible(true);
                        switch (annotation.WashProgram()) {
                            case RANDOM_DATA:
                                try {
                                    field.set(result, "RANDOM DATA");
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case REMOVE:
                                try {
                                    field.set(result, null);
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case TRANSITION_TABLE:
                                try {
                                    field.set(result, "Transition Table Data");
                                } catch (IllegalAccessException e) {
                                    e.printStackTrace();
                                }
                                break;

                        }
                    }
                });
    }
}
