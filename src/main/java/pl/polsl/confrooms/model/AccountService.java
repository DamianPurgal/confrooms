package pl.polsl.confrooms.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.polsl.confrooms.model.Account;
import pl.polsl.confrooms.repository.AccountRepository;

//KLASA ZBEDNA. JEST TO TYLKO PRZYKLAD.
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public String addNewUser(String login, String password, String accountType) {
        Account n = new Account();
        n.setLogin(login);
        n.setPassword(password);
        n.setAccountType(accountType);
        accountRepository.save(n);
        return "Saved";
    }

    public Iterable<Account> getAllUsers() {
        // This returns a JSON or XML with the users
        return accountRepository.findAll();
    }

    public Iterable<Account> getAccountsPage(int page) {
        // This returns a JSON or XML with the users
        int amount_of_elements_in_page = 3;
        return accountRepository.findAll(PageRequest.of(page, amount_of_elements_in_page));
    }

}