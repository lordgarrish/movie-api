package lordgarrish.movie.api.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Entity
@Table(name = "movies")
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    @NotBlank(message = "Title is mandatory")
    private String title;
    @Column(name = "description")
    @NotBlank(message = "Description is mandatory")
    private String description;
    @Column(name = "type")
    @NotBlank(message = "Type is mandatory")
    @Pattern(regexp = "(\\bfull-length\\b)|(\\bshort-length\\b)|(\\bseries\\b)",
             message = "Type must be full-length, short-length or series")
    private String type;
    @Column(name = "genre")
    @NotBlank(message = "Genre is mandatory")
    private String genre;
    @Column(name = "release_date")
    @NotNull(message = "Release date is mandatory")
    private LocalDate releaseDate;

    public Movie() {}

    public Movie(String title, String description, String type, String genre, LocalDate releaseDate) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }
}
