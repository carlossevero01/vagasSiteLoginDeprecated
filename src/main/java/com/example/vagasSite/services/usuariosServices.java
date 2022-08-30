package com.example.vagasSite.services;

import java.util.List;
import java.util.Optional;



import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.vagasSite.model.usuario;
import com.example.vagasSite.web.dto.UserRegistrationDto;


public interface usuariosServices extends UserDetailsService{
    List<usuario> findAll();
    usuario findById(Integer id);
    usuario save(UserRegistrationDto registrationDto);
    usuario save(usuario usuarioexistente);
    usuario deleteById(Integer id);
    usuario findByUsername(String username);
    List<usuario> findByNomeLike(String nome);
    Optional<usuario> findByIdentificacao(Long identificacao);
}
