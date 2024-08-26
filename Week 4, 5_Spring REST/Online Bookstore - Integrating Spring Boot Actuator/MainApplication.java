// MainApplication.java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Configuration
    public static class ActuatorConfig {
        @Bean
        public MeterRegistryCustomizer<MeterRegistry> configureMetrics() {
            return registry -> registry.counter("custom.book.requests", "type", "GET");
        }
    }

    @RestController
    @RequestMapping("/books")
    public static class BookController {

        private final Counter bookRequestCounter;

        public BookController(MeterRegistry meterRegistry) {
            this.bookRequestCounter = meterRegistry.counter("custom.book.requests", "type", "GET");
        }

        @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
        public Book getBook(@PathVariable Long id) {
            bookRequestCounter.increment(); // Increment the custom metric
            return new Book(id, "Sample Title", "Sample Author"); // Example book data
        }
    }

    public static class Book {
        private Long id;
        private String title;
        private String author;

        public Book(Long id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
        }

        // Getters and setters
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }
}
