package pl.polsl.confrooms.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.polsl.confrooms.model.Account;

//KLASA ZBEDNA. JEST TO TYLKO PRZYKLAD.
public interface AccountRepository extends PagingAndSortingRepository<Account, Integer> {


}