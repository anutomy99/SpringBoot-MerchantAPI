package org.jsp.merchantbootapp.controller;

import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MerchantController {
	@Autowired
	private MerchantService service;
	@PostMapping("/merchant")
	public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(@RequestBody Merchant merchant){
		return service.saveMerchant(merchant);
	}
	@PutMapping("/merchant/{id}")
	public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(@RequestBody Merchant merchant,@PathVariable int id){
		return service.updateMerchant(merchant);
	}
	@PutMapping("/merchant/verify-merchant")
	public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(@RequestParam long phone,String password){
		return service.verifyMerchant(phone, password);
	}

}
