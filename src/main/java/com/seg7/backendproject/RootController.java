package com.seg7.backendproject;

import com.seg7.backendproject.MyService;
import com.seg7.backendproject.Accounts;
import com.seg7.backendproject.AccountRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.io.IOException;

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

    @PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<Account> createProduct(
			@RequestParam("data") String data,
			@RequestParam("attach") MultipartFile attach) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		AccountRequest request;
		try {
			request = objectMapper.readValue(data, AccountRequest.class);
		} catch (IOException e) {
			return ResponseEntity.badRequest().build();
		}
		if (!attach.isEmpty()) {
			String fileName = attach.getOriginalFilename();
			System.out.println("附件：" + fileName);
		}

		Account accounts = myService.createAccount(request);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(accounts.getID())
				.toUri();
		return ResponseEntity.created(location).body(accounts);
}
