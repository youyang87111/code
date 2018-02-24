package com.example.demo;


import java.util.List;

import com.example.demo.domain.Account;

public interface IAccountDAO {

	int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();
}
