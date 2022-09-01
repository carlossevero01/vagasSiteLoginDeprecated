package com.example.vagasSite.services;

import java.util.List;

import com.example.vagasSite.model.Role;


public interface roleServices {
    List<Role> findAll();
    Role findByNameLike(String name);
}
