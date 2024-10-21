package com.seg7.backendproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.seg7.backendproject.Accounts;
import com.seg7.backendproject.AccountRequest;

@Service
public class MyService {

	public Accounts createProduct(AccountRequest request) {
		Accounts accounts = new Accounts();
		accounts.setRemark(request.getRemark());
		accounts.setCategory(request.getCategory());
		accounts.setTime(request.getTime());
		accounts.setAttach(request.getAttach());
		accounts.setPrice(request.getPrice());

		return accounts;
	}

}

