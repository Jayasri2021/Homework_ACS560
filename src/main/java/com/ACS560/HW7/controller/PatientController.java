package com.ACS560.HW7.controller;
import com.ACS560.HW7.models.PatientModel;
import com.ACS560.HW7.request.PatientRequest;
import com.ACS560.HW7.services.PatientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/patients")
public class PatientController {
   private final PatientService patientService;
   public PatientController(PatientService patientService) {
       this.patientService = patientService;
   }
   @GetMapping
   public ResponseEntity<List<PatientModel>> getAllPatients() {
       return ResponseEntity.ok(patientService.getAllPatients());
   }
   @GetMapping("/{id}")
   public ResponseEntity<PatientModel> getPatientById(@PathVariable Long id) {
       return ResponseEntity.ok(patientService.getPatientById(id));
   }
   @PostMapping
   public ResponseEntity<PatientModel> createPatient(@RequestBody PatientRequest request) {
       return ResponseEntity.ok(patientService.createPatient(request));
   }
   @PutMapping("/{id}")
   public ResponseEntity<PatientModel> updatePatient(
           @PathVariable Long id,
           @RequestBody PatientRequest request) {
       return ResponseEntity.ok(patientService.updatePatient(id, request));
   }
   @DeleteMapping("/{id}")
   public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
       patientService.deletePatient(id);
       return ResponseEntity.ok().build();
   }
}

