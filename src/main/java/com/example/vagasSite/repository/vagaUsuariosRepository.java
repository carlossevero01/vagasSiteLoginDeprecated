package com.example.vagasSite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vagasSite.model.vagaUsuarios;

@Repository
public interface vagaUsuariosRepository extends JpaRepository<vagaUsuarios,Integer>{
    List<vagaUsuarios> findByIdVaga(int id);
}
