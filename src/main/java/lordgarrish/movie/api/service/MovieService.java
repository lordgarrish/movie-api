package lordgarrish.movie.api.service;

import lordgarrish.movie.api.model.Movie;
import lordgarrish.movie.api.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MovieService {
    private final MovieRepository movieRepo;

    @Autowired
    public MovieService(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    public List<Movie> add(List<Movie> movies) {
        return movieRepo.saveAll(movies);
    }

    public Page<Movie> getAllByParams(String title, String type, Integer year, Pageable pageable) {
        return movieRepo.findAllByParams(title, type,  year,  pageable);
    }
}
