package se.pensionsmyndigheten.se.tvattomat.domain;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "excerpt")
public class Excerpt {

    public Excerpt(int chapter, String text) {
        this.chapter = chapter;
        this.text = text;
    }


    @JacksonXmlProperty(isAttribute = true)
    private int chapter;

    @JacksonXmlProperty
    private String text;

}
