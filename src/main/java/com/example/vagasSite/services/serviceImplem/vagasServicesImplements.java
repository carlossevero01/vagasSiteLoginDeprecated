package com.example.vagasSite.services.serviceImplem;



import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;


import com.example.vagasSite.model.vagas;

import com.example.vagasSite.repository.vagasRepository;
import com.example.vagasSite.services.vagasServices;

public class vagasServicesImplements implements vagasServices{
    @Autowired
    vagasRepository vagasRepository;
    
    @Override
    public List<vagas> findAll() {
        return vagasRepository.findAll();
    }

    @Override
    public vagas findById(Integer id) {
        return vagasRepository.findById(id).get();
    }

    @Override
    public vagas save(vagas v) {
        return vagasRepository.save(v);
    }

    @Override
    public List<vagas> findVagasByTipoLike(String tipo) {
        return vagasRepository.findVagasByTipoLike(tipo);
    }

    @Override
    public vagas deleteById(Integer id) {
        return deleteById(id);
    }
    @Override
    public List<vagas> findByidUsuario(Integer id){
        return vagasRepository.findByidUsuario(id);
    } 
    
}
