package se.pensionsmyndigheten.se.tvattomat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import se.pensionsmyndigheten.se.tvattomat.domain.Book;
import se.pensionsmyndigheten.se.tvattomat.domain.Excerpt;
import se.pensionsmyndigheten.se.tvattomat.repository.BookRepository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class TvattomatApplication {

	@Autowired
	private BookRepository bookRepository;

	public static void main(String[] args) {
		SpringApplication.run(TvattomatApplication.class, args);

		//new TvattomatApplication().initData();

	}

	@PostConstruct
	private void initData() {

		List<Book> books = new ArrayList<>();
		for (int i = 0; i <1000; i++) {
			books.add(Book.builder()
					.author("Keyhan Hadjari" + i)
					.title("Book of Aliens" + i)
					.genre("SciFi" + i)
					.isbn("1111111" + i)
					.published(1950 + i)
					.excerpt(new Excerpt(1, "Be aware of the aliens!!!"))
					.build());
		}
		bookRepository.save(books);

//		bookRepository.save(Book.builder()
//				.author("Keyhan Hadjari")
//				.title("Book of Aliens")
//				.genre("SciFi")
//				.isbn("1111111")
//				.published(1998)
//				.excerpt(new Excerpt(1, "Be aware of the aliens!!!"))
//				.build()
//		);
	}
}
