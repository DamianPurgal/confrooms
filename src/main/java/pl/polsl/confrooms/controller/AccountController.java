package pl.polsl.confrooms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.polsl.confrooms.model.Account;
import pl.polsl.confrooms.model.AccountService;

@RestController
@RequestMapping(path="/account")
public class AccountController {
//test
    @Autowired
    private AccountService accountService;

//    @GetMapping("/hello")
//    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
//        return String.format("Hello %s!", name);
//    }

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody
    String addNewUser (@RequestParam String login
            , @RequestParam String password, @RequestParam String accountType) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        return accountService.addNewUser(login,password,accountType);
    }
    @CrossOrigin
    @GetMapping(path="/getAllAccounts")
    public @ResponseBody Iterable<Account> getAllUsers() {
        // This returns a JSON or XML with the users
        return accountService.getAllUsers();
    }
    @CrossOrigin
    @GetMapping(path="/getAccounts")
    public @ResponseBody Iterable<Account> getAccountsPage(@RequestParam(value = "page", defaultValue = "0") int page) {
        // This returns a JSON or XML with the users
        return accountService.getAccountsPage(page);
    }
}