package mmk.omak.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mmk.omak.enums.Authorities;
import mmk.omak.enums.ContactTypes;
import mmk.omak.enums.CostingTypes;
import mmk.omak.enums.DeliveryStatus;
import mmk.omak.enums.OfferStatus;
import mmk.omak.enums.Origins;
import mmk.omak.enums.ProductUnits;
import mmk.omak.enums.ReceivingStatus;

@RestController
@RequestMapping("/enums")
public class EnumController {
	
	@GetMapping("/authorities")
	public List<List<String>> getAuthorities() {
		List<List<String>> result = new ArrayList<>();
		for (Authorities list : Authorities.values()) {
			result.add(Arrays.asList(list.name(), list.getValue()));
		}
		return result;
	}
	@GetMapping("/contact-types")
	public List<List<String>> getContactTypes() {
		List<List<String>> result = new ArrayList<>();
		for (ContactTypes list : ContactTypes.values()) {
			result.add(Arrays.asList(list.name(), list.getData()));
		}
		return result;
	}
	@GetMapping("/costing-types")
	public List<List<String>> getCostingTypes() {
		List<List<String>> result = new ArrayList<>();
		for (CostingTypes list : CostingTypes.values()) {
			result.add(Arrays.asList(list.name(), list.getData()));
		}
		return result;
	}
	@GetMapping("/delivery-status")
	public List<List<String>> getDeliveryStatus() {
		List<List<String>> result = new ArrayList<>();
		for (DeliveryStatus list : DeliveryStatus.values()) {
			result.add(Arrays.asList(list.name(), list.getData()));
		}
		return result;
	}
	@GetMapping("/offer-status")
	public List<List<String>> getOfferStatus() {
		List<List<String>> result = new ArrayList<>();
		for (OfferStatus list : OfferStatus.values()) {
			result.add(Arrays.asList(list.name(), list.getData()));
		}
		return result;
	}
	@GetMapping("/origins")
	public List<List<String>> getOrigins() {
		List<List<String>> result = new ArrayList<>();
		for (Origins list : Origins.values()) {
			result.add(Arrays.asList(list.name(), list.getData()));
		}
		return result;
	}
	@GetMapping("/product-units")
	public List<List<String>> getProductUnits() {
		List<List<String>> result = new ArrayList<>();
		for (ProductUnits list : ProductUnits.values()) {
			result.add(Arrays.asList(list.name(), list.getData()));
		}
		return result;
	}
	@GetMapping("/receiving-status")
	public List<List<String>> getReceivingStatus() {
		List<List<String>> result = new ArrayList<>();
		for (ReceivingStatus list : ReceivingStatus.values()) {
			result.add(Arrays.asList(list.name(), list.getData()));
		}
		return result;
	}
}
