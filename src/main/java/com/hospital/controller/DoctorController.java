package com.hospital.controller;
import com.hospital.model.Doctor; import com.hospital.repository.DoctorRepository;
import org.springframework.web.bind.annotation.*; import java.util.List;
@RestController @RequestMapping("/api/doctors")
public class DoctorController {
 private final DoctorRepository repo; public DoctorController(DoctorRepository repo){this.repo=repo;}
 @GetMapping public List<Doctor> all(){return repo.findAll();}
 @PostMapping public Doctor add(@RequestBody Doctor d){d.setId(null);return repo.save(d);}
 @PutMapping("/{id}") public Doctor update(@PathVariable Long id,@RequestBody Doctor d){Doctor old=repo.findById(id).orElseThrow();old.setName(d.getName());old.setSpecialization(d.getSpecialization());old.setPhone(d.getPhone());old.setEmail(d.getEmail());old.setAvailability(d.getAvailability());old.setAvailable(d.getAvailable());return repo.save(old);}
 @DeleteMapping("/{id}") public void delete(@PathVariable Long id){repo.deleteById(id);}
}
