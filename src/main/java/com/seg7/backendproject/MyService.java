package com.seg7.backendproject;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {
	@Autowired
	private AccountRepository repository;

	public Account createAccount(AccountRequest request, String filePath) {
		Account accounts = new Account();
		accounts.setID(NanoIdUtils.randomNanoId());
		accounts.setRemark(request.getRemark());
		accounts.setTime(request.getTime());
		accounts.setCategory(request.getCategory());
		accounts.setAttach(filePath);
		accounts.setPrice(request.getPrice());
		// 加入資料庫
		accounts = repository.insert(accounts);
		return accounts;
	}

}

