package pl.polsl.confrooms.repository;

import org.springframework.data.repository.CrudRepository;
import pl.polsl.confrooms.model.User.User;

import java.util.Optional;

//REPOSITORY ODPOWIEDZIALNE ZA POBIERANIE DANYCH Z TABELI USERS W BAZIE DANYCH
public interface UserRepository extends CrudRepository<User, Long> {
    public Optional<User> findByUsername(String username);
}
