package se.pensionsmyndigheten.se.tvattomat.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@TypeDefs({
        @TypeDef(name = "excerpt", typeClass = ExcerptUserType.class)
})
public class Book {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="S1")
    @SequenceGenerator(name="S1", sequenceName = "seq_book", allocationSize = 1)
    private long id;
    private String title;
    private String author;
    private String isbn;
    private int published;
    private String genre;

    @Type(type = "excerpt")
    private Excerpt excerpt;
}
