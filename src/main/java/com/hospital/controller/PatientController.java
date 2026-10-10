package com.hospital.controller;
import com.hospital.model.Patient; import com.hospital.repository.PatientRepository;
import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/patients")
public class PatientController {
 private final PatientRepository repo; public PatientController(PatientRepository repo){this.repo=repo;}
 @GetMapping public List<Patient> all(){return repo.findAll();}
 @PostMapping public Patient add(@RequestBody Patient p){p.setId(null);return repo.save(p);}
 @PutMapping("/{id}") public Patient update(@PathVariable Long id,@RequestBody Patient p){Patient old=repo.findById(id).orElseThrow();old.setName(p.getName());old.setAge(p.getAge());old.setGender(p.getGender());old.setPhone(p.getPhone());old.setAddress(p.getAddress());return repo.save(old);}
 @DeleteMapping("/{id}") public void delete(@PathVariable Long id){repo.deleteById(id);}
}
