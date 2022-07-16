package mx.edu.utez.talenting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.utez.talenting.entity.Employeer;
import mx.edu.utez.talenting.service.EmployeerService;

@RestController
@RequestMapping("/talenting")
@CrossOrigin(origins = "http://127.0.0.1:8081")
public class EmployeerController {
	
	@Autowired
	private EmployeerService employeerSer;
	
//	@GetMapping("/employeers")
//	public List<Employeer> list(){
//		return employeerSer.getAll();
//	}
	
	@GetMapping("/employeers")
	public Employeer edit(@RequestParam("personId") long id) {
		return employeerSer.getOne(id);
	}
	
	@PutMapping("/employeers")
	public Employeer update(@RequestBody Employeer employeer) {
		return employeerSer.saveOrUpdate(employeer);
	}
	
	@DeleteMapping("/employeers")
	public void delete(@RequestParam("id") long id) {
		employeerSer.remove(id);
	}

	
}
