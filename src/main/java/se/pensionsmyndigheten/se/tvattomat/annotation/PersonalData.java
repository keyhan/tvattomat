package se.pensionsmyndigheten.se.tvattomat.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PersonalData {
    public WashProgram WashProgram() default WashProgram.RANDOM_DATA;
}
