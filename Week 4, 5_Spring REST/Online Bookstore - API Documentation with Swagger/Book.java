import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Details about the book")
public class Book {
    @Schema(description = "The unique ID of the book", example = "1")
    private Long id;

    @Schema(description = "The title of the book", example = "Sample Title")
    private String title;

    @Schema(description = "The author of the book", example = "Sample Author")
    private String author;

    // Constructors, getters, and setters
    public Book(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

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
