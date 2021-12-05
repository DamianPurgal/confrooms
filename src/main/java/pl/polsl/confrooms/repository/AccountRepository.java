package pl.polsl.confrooms.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.polsl.confrooms.model.Account;

public interface AccountRepository extends PagingAndSortingRepository<Account, Integer> {

}