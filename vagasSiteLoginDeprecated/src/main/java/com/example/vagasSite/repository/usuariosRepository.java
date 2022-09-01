package com.example.vagasSite.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vagasSite.model.usuario;

@Repository
public interface usuariosRepository extends JpaRepository<usuario,Integer>{

    usuario findByUsername(String username);
    List<usuario> findByNomeLike(String nome);
    Optional<usuario> findByIdentificacao(Long identificacao);
    
}
