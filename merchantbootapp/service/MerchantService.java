package org.jsp.merchantbootapp.service;

import java.util.Optional;

import org.jsp.merchantbootapp.dao.MerchantDao;
import org.jsp.merchantbootapp.dto.Merchant;
import org.jsp.merchantbootapp.dto.Product;
import org.jsp.merchantbootapp.dto.ResponseStructure;
import org.jsp.merchantbootapp.exception.IdNotFoundException;
import org.jsp.merchantbootapp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {
        @Autowired
		private MerchantDao dao;
        
        public ResponseEntity<ResponseStructure<Merchant>> saveMerchant(Merchant merchant){
        	ResponseStructure<Merchant> structure=new ResponseStructure<>();
        	Merchant m=dao.saveMerchant(merchant);
        	structure.setData(m);
        	structure.setMessage("Merchant Registered SuccessFully");
        	structure.setStatusCode(HttpStatus.CREATED.value());
        	return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.CREATED);
        }
        
        public ResponseEntity<ResponseStructure<Merchant>> updateMerchant(Merchant merchant){
        	ResponseStructure<Merchant> structure=new ResponseStructure<>();
        	Merchant m=dao.updateMerchant(merchant);
        	structure.setData(m);
        	structure.setMessage("Merchant Updated SuccessFully");
        	structure.setStatusCode(HttpStatus.ACCEPTED.value());
        	return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.ACCEPTED);
        }
        
        public ResponseEntity<ResponseStructure<Merchant>> findById(int id){
        	ResponseStructure<Merchant> structure=new ResponseStructure<>();
        	Optional<Merchant> recMerchant=dao.findById(id);
        	if(recMerchant.isPresent()) {
        		structure.setData(recMerchant.get());
        		structure.setMessage("Merchant Found");
        		structure.setStatusCode(HttpStatus.OK.value());
        		return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);
        	}
        	throw new IdNotFoundException();
        	
        }
        
        public ResponseEntity<ResponseStructure<Merchant>> verifyMerchant(long phone,String password){
        	ResponseStructure<Merchant> structure=new ResponseStructure<>();
        	Optional<Merchant> recMerchant=dao.verifyMerchant(phone, password);
        	if(recMerchant.isPresent()) {
        		structure.setData(recMerchant.get());
        		structure.setMessage("Merchant Verified");
        		structure.setStatusCode(HttpStatus.OK.value());
        		return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.OK);
        	}
        	throw new InvalidCredentialsException();
        }
}
