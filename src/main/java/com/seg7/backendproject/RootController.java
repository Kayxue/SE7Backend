package com.seg7.backendproject;

import com.seg7.backendproject.MyService;
import com.seg7.backendproject.Accounts;
import com.seg7.backendproject.AccountRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class RootController {
	@Autowired
	private AccountRepository accountRepository;

    @Autowired
	private MyService myService;
    
    @GetMapping("/")
    public String root() {
        return "Hello World";
    }

    @PostMapping
	public ResponseEntity<Accounts> createProduct(@Valid @RequestBody AccountRequest request) {
		Accounts accounts = myService.createProduct(request);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(accounts.getID())
				.toUri();

		return ResponseEntity.created(location).body(accounts);
	}
}
