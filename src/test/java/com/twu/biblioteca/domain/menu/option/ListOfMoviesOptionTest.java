package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.TestCase;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.repository.MovieRepository;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.service.AuthService;
import com.twu.biblioteca.service.MovieService;
import com.twu.biblioteca.service.UserService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ListOfMoviesOptionTest extends TestCase {
    private Menu menu;
    private MovieService movieService;
    private MovieRepository movieRepository;
    private ListOfMoviesOption listOfMoviesOption;

    @Override
    public void setUp() {
        super.setUp();

        movieRepository = mock(MovieRepository.class);
        UserRepository userRepository = mock(UserRepository.class);
        movieService = new MovieService(movieRepository);
        listOfMoviesOption = new ListOfMoviesOption(out, movieService);
        UserService userService = new UserService(userRepository);

        menu = new Menu(out, new AuthService(userService));
        menu.addOption(listOfMoviesOption);
    }

    @Test
    public void listAvailableMovies() throws Exception {
        List<Movie> movies = new ArrayList<>();

        Movie movie = new Movie("Movie 1", 2020, "Director 1", 10, true);
        movies.add(movie);

        movie = new Movie("Movie 2", 2020, "Director 2", 10, false);
        movies.add(movie);

        movie = new Movie("Movie 3", 2020, "Director 3", 5, true);
        movies.add(movie);

        when(movieRepository.all()).thenReturn(movies);

        listOfMoviesOption.show();

        verify(out).println("Name: Movie 1 | Year: 2020 | Director: Director 1 | Rating: 10\nName: Movie 3 | Year: 2020 | Director: Director 3 | Rating: 5");
    }

    @Test
    public void showListOfMoviesOption() {
        menu.open();

        verify(out).println("4. List of Movies");
    }

    @Test
    public void enterListOfMoviesFromMenu() throws Exception {
        menu.run(4);

        verify(out).printf("\n%s%n", "List of Movies");
    }
}
