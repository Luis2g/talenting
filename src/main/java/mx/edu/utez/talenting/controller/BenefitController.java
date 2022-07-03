package mx.edu.utez.talenting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.utez.talenting.entity.Benefit;
import mx.edu.utez.talenting.service.BenefitService;

@RestController
@RequestMapping("/talenting")
public class BenefitController {
	
	@Autowired
	private BenefitService benefitSer;
	
	@GetMapping("/benefits")
	public List<Benefit> list(){
		return benefitSer.getAll();
	}
	
	@GetMapping("/benefits/{id}")
	public Benefit edit(@PathVariable("id") long id) {
		return benefitSer.getOne(id);
	}
	
	@PutMapping("/benefits")
	public Benefit update(@RequestBody Benefit benefit) {
		return benefitSer.saveOrUpdate(benefit);
	}
	
	@DeleteMapping("/benefits")
	public void delete(@RequestParam("id") long id) {
		benefitSer.remove(id);
	}

	
}
