
package com.hospital.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.model.Appointment;
import com.hospital.repository.AppointmentRepository;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentRepository repo;

    public AppointmentController(AppointmentRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Appointment> all() {
        return repo.findAll();
    }

    @PostMapping
    public Appointment add(@RequestBody Appointment appointment) {
        appointment.setId(null);
        return repo.save(appointment);
    }

    @PatchMapping("/{id}/status")
    public Appointment updateStatus(
            @PathVariable Long id,
            @RequestBody Map<String, String> body) {

        String status = body.get("status");

        Set<String> allowedStatuses = Set.of(
                "Booked",
                "Checked In",
                "In Consultation",
                "Completed",
                "Checked Out",
                "Cancelled"
        );

        if (status == null || !allowedStatuses.contains(status)) {
            throw new IllegalArgumentException(
                    "Invalid appointment status: " + status);
        }

        Appointment appointment = repo.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Appointment not found: " + id));

        appointment.setStatus(status);

        return repo.save(appointment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
