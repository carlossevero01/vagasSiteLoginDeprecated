package com.example.vagasSite.services;

import java.util.List;

import com.example.vagasSite.model.vagaUsuarios;

public interface vagaUsuariosServices {
    List<vagaUsuarios> findAll();
    vagaUsuarios findById(Integer id);
    vagaUsuarios save(vagaUsuarios vu);
    vagaUsuarios deleteById(Integer id);
    List<vagaUsuarios> findByIdVaga(int id);
}
