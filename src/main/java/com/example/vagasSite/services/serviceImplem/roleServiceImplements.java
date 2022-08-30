package com.example.vagasSite.services.serviceImplem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.vagasSite.model.Role;
import com.example.vagasSite.repository.roleRepository;

import com.example.vagasSite.services.roleServices;


public class roleServiceImplements implements roleServices{
        @Autowired
         roleRepository rRepository;

        @Override
        public List<Role> findAll() {
            return rRepository.findAll();
        }

        @Override
        public Role findByNameLike(String name) {
            return rRepository.findByNameLike(name);
        }

   
    

   

    
    
}
