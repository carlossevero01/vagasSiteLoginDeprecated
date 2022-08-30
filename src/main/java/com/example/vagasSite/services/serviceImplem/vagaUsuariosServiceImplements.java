package com.example.vagasSite.services.serviceImplem;

import java.util.List;

import com.example.vagasSite.model.vagaUsuarios;
import com.example.vagasSite.repository.vagaUsuariosRepository;
import com.example.vagasSite.services.vagaUsuariosServices;

public class vagaUsuariosServiceImplements implements vagaUsuariosServices{
    vagaUsuariosRepository vagaUsuariosRepository;
    @Override
    public List<vagaUsuarios> findAll() {
        // TODO Auto-generated method stub
        return vagaUsuariosRepository.findAll();
    }

    @Override
    public vagaUsuarios findById(Integer id) {
        // TODO Auto-generated method stub
        return vagaUsuariosRepository.findById(id).get();
    }

    @Override
    public vagaUsuarios save(vagaUsuarios vu) {
        // TODO Auto-generated method stub
        System.out.println("oi");
        return vagaUsuariosRepository.save(vu);
    }

    @Override
    public vagaUsuarios deleteById(Integer id) {
        // TODO Auto-generated method stub
        return deleteById(id);
    }

    @Override
    public List<vagaUsuarios> findByIdVaga(int id) {
        
        return vagaUsuariosRepository.findByIdVaga(id);
    }

    
    
}
