package com.ACS560.HW6.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.ACS560.HW6.entity.PatientEntity;
import com.ACS560.HW6.model.PatientModel;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends CrudRepository<PatientEntity, Long> {
    
	@Query("SELECT new com.ACS560.HW6.model.PatientModel(p.id, p.name, p.address) " +
		       "FROM PatientEntity p WHERE p.name LIKE %?1%")
		List<PatientModel> findModelsByNameContaining(String namePart);
    
    @Query("SELECT new com.ACS560.HW6.model.PatientModel(p.id, p.name, p.address) " +
           "FROM PatientEntity p ORDER BY p.name ASC")
    List<PatientModel> findAllModelsOrderByName();
    
    Optional<PatientEntity> findByName(String name);
    List<PatientEntity> findByNameContaining(String namePart);
    List<PatientEntity> findByAddress(String address);
    boolean existsByName(String name);
}