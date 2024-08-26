import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@Tag(name = "Books", description = "Operations related to books")
public class BookController {

    @GetMapping("/{id}")
    @Operation(
        summary = "Get a book by ID",
        description = "Retrieve details of a book using its ID",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Successfully retrieved book",
                content = @Content(schema = @Schema(implementation = Book.class))
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Book not found"
            )
        }
    )
    public Book getBook(@PathVariable Long id) {
        // Sample implementation
        return new Book(id, "Sample Title", "Sample Author");
    }
}
