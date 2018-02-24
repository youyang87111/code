package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Account;

public interface IAccountService {

	int add(Account account);

    int update(Account account);

    int delete(int id);

    Account findAccountById(int id);

    List<Account> findAccountList();
}
