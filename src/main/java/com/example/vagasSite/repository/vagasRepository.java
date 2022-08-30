package com.example.vagasSite.repository;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vagasSite.model.vagas;



@Repository
public interface vagasRepository extends JpaRepository<vagas,Integer>{
   
    List<vagas> findVagasByTipoLike(String tipo);
    List<vagas> findByidUsuario(Integer id);
    
       
}
