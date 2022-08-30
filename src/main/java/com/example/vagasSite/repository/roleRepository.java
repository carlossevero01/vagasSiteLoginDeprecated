package com.example.vagasSite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vagasSite.model.Role;


@Repository
public interface roleRepository extends JpaRepository<Role,Integer>{
    Role findByNameLike(String name);
}
