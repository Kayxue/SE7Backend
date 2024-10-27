package com.seg7.backendproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.seg7.backendproject.Accounts;
import com.seg7.backendproject.AccountRequest;

@Service
public class MyService {

	@Autowired
	private AccountRepository repository;

	public Account createAccount(AccountRequest request, String filePath) {
		Account accounts = new Account();
		accounts.setRemark(request.getRemark());
		accounts.setTime(request.getTime());
		accounts.setCategory(request.getCategory());
		accounts.setAttach(filePath);
		accounts.setPrice(request.getPrice());

		// 加入資料庫

		return accounts;
	}

}

