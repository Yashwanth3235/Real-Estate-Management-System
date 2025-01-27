package com.example.rse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.rse.model.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
	List<Property> findByOwnerName(String ownerName);
}
