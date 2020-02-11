package com.twu.biblioteca.domain.menu.option;

import com.twu.biblioteca.InteractsWithConsole;
import com.twu.biblioteca.domain.menu.Menu;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.repository.MovieRepository;
import com.twu.biblioteca.repository.UserRepository;
import com.twu.biblioteca.service.AuthService;
import com.twu.biblioteca.service.MovieService;
import com.twu.biblioteca.service.UserService;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ListOfMoviesOptionTest extends InteractsWithConsole {
    private Menu menu;
    private ListOfMoviesOption listOfMoviesOption;

    @Mock
    private MovieRepository movieRepository;

    @Override
    public void setUp() {
        super.setUp();

        UserRepository userRepository = mock(UserRepository.class);
        MovieService movieService = new MovieService(movieRepository);
        UserService userService = new UserService(userRepository);

        listOfMoviesOption = new ListOfMoviesOption(console, movieService);

        menu = new Menu(console, new AuthService(userService));
        menu.addOption(listOfMoviesOption);
    }

    @Test
    public void showListOfMoviesOption() {
        menu.open();

        verify(console).doWrite("4. List of Movies");
    }

    @Test
    public void runListOfMoviesOption() throws Exception {
        List<Movie> movies = new ArrayList<>();

        Movie movie = new Movie("Movie 1", 2020, "Director 1", 10, true);
        movies.add(movie);

        movie = new Movie("Movie 2", 2020, "Director 2", 10, false);
        movies.add(movie);

        movie = new Movie("Movie 3", 2020, "Director 3", 5, true);
        movies.add(movie);

        when(movieRepository.all()).thenReturn(movies);

        expectsOutput("List of Movies")
                .expectsOutput("Name: Movie 1 | Year: 2020 | Director: Director 1 | Rating: 10\nName: Movie 3 | Year: 2020 | Director: Director 3 | Rating: 5")
                .execute(listOfMoviesOption);
    }
}
