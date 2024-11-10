package com.ACS560.HW6.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ACS560.HW6.model.PatientModel;
import com.ACS560.HW6.request.PatientRequest;
import com.ACS560.HW6.service.PatientService;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    
    @Autowired
    private PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientModel>> getPatients() {
        return ResponseEntity.ok(patientService.getPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientModel> getPatient(@PathVariable Long id) {
        return patientService.getPatient(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<PatientModel> addPatient(@Valid @RequestBody PatientRequest request) {
        return ResponseEntity.ok(patientService.addPatient(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientModel> updatePatient(
            @PathVariable Long id, 
            @Valid @RequestBody PatientRequest request) {
        return ResponseEntity.ok(patientService.updatePatient(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return ResponseEntity.ok().build();
    }
}