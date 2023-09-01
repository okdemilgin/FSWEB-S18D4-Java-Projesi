package com.workintech.jpamany.controller;

import com.workintech.jpamany.entity.Account;
import com.workintech.jpamany.entity.Customer;
import com.workintech.jpamany.service.AccountService;
import com.workintech.jpamany.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private AccountService accountService;
    private CustomerService customerService;

    @Autowired
    public AccountController(AccountService accountService, CustomerService customerService) {
        this.accountService = accountService;
        this.customerService = customerService;
    }

    @GetMapping("/")
    public List<Account> get(){
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable int id){
        return accountService.find(id);
    }

    @PostMapping("/{customerId}")
    public Account save(@RequestBody Account account, @PathVariable int customerId){
        Customer customer = customerService.find(customerId);
        customer.add(account);
        account.setCustomer(customer);
        return accountService.save(account);
    }

    @PutMapping("/{customerId}")
    public Account update(@RequestBody Account account, @PathVariable int customerId){
        Customer customer = customerService.find(customerId);

        //[account1, account4, account3]
//        Account foundAccount = customer.getAccountList().stream().filter(account1 ->
//                account1.getId() == account.getId()).collect(Collectors.toList()).get(0);

        Account foundAccount = null;
        for(Account account1: customer.getAccountList()){
            if(account1.getId() == account.getId()){
                foundAccount = account1;
            }
        }
        if(foundAccount == null){
            //throw Exception
        }

        int index = customer.getAccountList().indexOf(foundAccount);
        customer.getAccountList().set(index, account);

        account.setCustomer(customer);
        return accountService.save(account);
    }

    @DeleteMapping("/{id}")
    public Account delete(@PathVariable int id){
        Account account = accountService.find(id);
        accountService.delete(account);
        return account;
    }
}