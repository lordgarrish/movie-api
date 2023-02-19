package lordgarrish.movie.api.repository;

import lordgarrish.movie.api.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT * FROM movies WHERE (:title is null or title LIKE %:title%) AND (:type is null or type = :type) " +
            "AND (:year is null or EXTRACT(YEAR FROM RELEASE_DATE) = :year)", nativeQuery = true)
    Page<Movie> findAllByParams(@Param("title")String title, @Param("type")String type, @Param("year")Integer year,
                                Pageable pageable);
}
