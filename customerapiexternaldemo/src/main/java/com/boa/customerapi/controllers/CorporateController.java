package com.boa.customerapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boa.customerapi.models.Corporate;
import com.boa.customerapi.services.CorporateService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/corporates")
public class CorporateController {
	
	@Autowired
	private CorporateService CorporateService;
	

	//post
	@PostMapping({"/v1.0/"})
	public ResponseEntity<String> addCorporate(@RequestBody Corporate Corporate) {
		
	  Corporate CorporateObj=this.CorporateService.addCorporate(Corporate);
	  Gson gson=new Gson();
	  if(CorporateObj!=null) {
		  return ResponseEntity.status(HttpStatus.CREATED)
				  .body(gson.toJson(CorporateObj));
	  }
	  else
	  {
		  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				  .body("Corporate Customer Data Not saved");
	  }
	}
	
	//get
	@GetMapping({"/v1.0"})
	public List<Corporate> getCorporates(){
		return this.CorporateService.viewAllCorporates();
	}
	
	@GetMapping({"/v1.0/{customerId}"})
	public Corporate getCorporateById(@PathVariable("customerId") long customerId) {
	   return this.CorporateService.viewCorporateById(customerId);	
	}
	
	@DeleteMapping({"/v1.0/{customerId}"})
	public ResponseEntity<String> deleteCorporateById(@PathVariable("customerId") long customerId) {
	   if(this.CorporateService.deleteCorporateById(customerId)) {
		   return ResponseEntity.status(HttpStatus.ACCEPTED)
					  .body("Corporate Customer Object Deleted");
	   }
	   else
	   {
		   return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					  .body("Customer Id not existing");
	   }
	}
	
	@PutMapping({"/v1.0/{customerId}"})
	public ResponseEntity<String> updateCorporate(@PathVariable("customerId") long customerId,
			@RequestParam(name = "email") String email, 
			@RequestParam(name="contactNumber") long contactNumber) {
		 Corporate CorporateObj=this.CorporateService.updateCorporate(customerId,
				 email, contactNumber);
		  Gson gson=new Gson();
		  if(CorporateObj!=null) {
			  return ResponseEntity.status(HttpStatus.CREATED)
					  .body(gson.toJson(CorporateObj));
		  }
		  else
		  {
			  return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					  .body("Customer Data Not updated");
		  }
	}
	
}
