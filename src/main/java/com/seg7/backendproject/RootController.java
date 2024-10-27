package com.seg7.backendproject;

import com.seg7.backendproject.MyService;
import com.seg7.backendproject.AccountRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.io.IOException;
import java.util.ArrayList;
import java.nio.file.*;
import java.util.UUID;

@RestController
@RequestMapping(value = "/Account")
public class AccountsController {

	@Autowired
	private MyService myService;

	@PostMapping(value = "/addAccount", consumes = "multipart/form-data") // 新增帳目
	public ResponseEntity<Account> createAccount(
			@RequestParam("data") String data,
			@RequestParam(value = "attach", required = false) MultipartFile attach) throws IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		AccountRequest request;
		try {
			request = objectMapper.readValue(data, AccountRequest.class);
		} catch (IOException e) {
			return ResponseEntity.badRequest().build();
		}
		String filePath = null;
		if (attach != null && !attach.isEmpty()) {
			String uploadDir = "upload";
			Path uploadPath = Paths.get(uploadDir);
			if (!Files.exists(uploadPath)) {
				Files.createDirectories(uploadPath);
			}
			String uniqueFileName = UUID.randomUUID().toString() + "_" + attach.getOriginalFilename();
			filePath = uploadDir + "/" + uniqueFileName;
			Path destinationPath = Paths.get(filePath);
			Files.copy(attach.getInputStream(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
		}

		Account accounts = myService.createAccount(request, filePath);
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(accounts.getID())
				.toUri();
		return ResponseEntity.created(location).body(accounts);
	}
}
