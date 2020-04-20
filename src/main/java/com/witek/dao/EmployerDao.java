package com.witek.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.witek.model.Employer;


@Repository
public interface EmployerDao extends JpaRepository<Employer, Long>{

}
