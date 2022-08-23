package mx.edu.utez.talenting.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.utez.talenting.entity.Skill;
import mx.edu.utez.talenting.service.SkillService;

@RestController
@RequestMapping("/talenting")
public class SkillController {
	
	@Autowired
	private SkillService skillSer;
	
	@RolesAllowed({"employeer", "employee"})
	@GetMapping("/skills")
	public List<Skill> list(){
		return skillSer.getAll();
	}
	
	@RolesAllowed("employee")
	@GetMapping("/skills/{id}")
	public Skill edit(@PathVariable("id") long id) {
		return skillSer.getOne(id);
	}
	
	@RolesAllowed( "employee")
	@PutMapping("/skills")
	public Skill update(@RequestBody Skill skill) {
		return skillSer.saveOrUpdate(skill);
	}
	
	@RolesAllowed("employee")
	@DeleteMapping("/skills")
	public void delete(@RequestParam("id") long id) {
		skillSer.remove(id);
	}

	
}
