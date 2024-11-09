package com.seg7.backendproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.UUID;

@RequestMapping(value = "/Account")
public class AccountsController {

	@Autowired
	private MyService myService;

	@PostMapping(value = "/addAccount", consumes = "multipart/form-data") // 新增帳目
	public ResponseEntity<Account> createAccount(
			@RequestParam("data") String data,
			@RequestParam(value = "attach", required = false) MultipartFile attach) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		AccountRequest request = objectMapper.readValue(data, AccountRequest.class);
		String filePath = null;
		if (attach != null && !attach.isEmpty()) {
			String uploadDir = "upload";
			Path uploadPath = Paths.get(uploadDir);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			filePath = myService.saveAttach(attach, uploadDir);
		}

		Account accounts = myService.createAccount(request, filePath);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(accounts.getID())
				.toUri();
		return ResponseEntity.created(location).body(accounts);
	}

	@DeleteMapping(value = "/{id}") // 刪除帳目
	public ResponseEntity<Account> deleteAccount(@PathVariable("id") String id) {
		myService.deleteAccount(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping // 取得帳目資訊
	public ResponseEntity<ArrayList<Account>> getAccounts(@ModelAttribute QueryParameter param) {
		ArrayList<Account> items = myService.getAccounts(param);
		return ResponseEntity.ok(items);
	}

	@PutMapping(value = "/{id}", consumes = "multipart/form-data") // 更新帳目
	public ResponseEntity<Account> updateProduct(
			@PathVariable("id") String id, @RequestParam("data") String data,
			@RequestParam(value = "attach", required = false) MultipartFile attach) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		AccountRequest updatedFields = objectMapper.readValue(data, AccountRequest.class);
		Account updatedAccount = myService.updateAccount(id, updatedFields, attach);

		return ResponseEntity.ok(updatedAccount);
	}
}