package com.example.vagasSite.services;

import java.util.List;



import com.example.vagasSite.model.vagas;



public interface vagasServices {
    List<vagas> findAll();
    List<vagas> findByidUsuario(Integer id);
    vagas findById(Integer id);
    vagas save(vagas v);
    List<vagas> findVagasByTipoLike(String tipo);
    vagas deleteById(Integer id);
}
