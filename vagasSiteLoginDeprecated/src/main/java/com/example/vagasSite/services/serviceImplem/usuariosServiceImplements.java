package com.example.vagasSite.services.serviceImplem;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.vagasSite.model.Role;
import com.example.vagasSite.model.usuario;
import com.example.vagasSite.repository.roleRepository;
import com.example.vagasSite.repository.usuariosRepository;
import com.example.vagasSite.services.usuariosServices;
import com.example.vagasSite.web.dto.UserRegistrationDto;


@Service
public class usuariosServiceImplements implements usuariosServices{
    @Autowired
     usuariosRepository usuariosRepository;
     @Autowired
        roleRepository rRepository;
    
    public usuariosServiceImplements(com.example.vagasSite.repository.usuariosRepository usuariosRepository) {
        super();
        this.usuariosRepository = usuariosRepository;
    }

    @Override
    public List<usuario> findAll() {
        return usuariosRepository.findAll();
    }

    @Override
    public usuario findById(Integer id) {
        return usuariosRepository.findById(id).get();
    }

    @Override
    public usuario save(UserRegistrationDto registrationDto) {
        
        usuario user = new usuario(registrationDto.getNome(),
        registrationDto.getEmail(), registrationDto.getTelefone(),
        registrationDto.getTipo(), registrationDto.getIdentificacao(),
        LocalDate.parse(registrationDto.getDataNascimento()) ,registrationDto.getImg(),
        registrationDto.getUsername(),
        new BCryptPasswordEncoder().encode(registrationDto.getPassword()), Arrays.asList(new Role( "ROLE_USER")));
    if(rRepository.findByNameLike("ROLE_USER")==null || rRepository.findByNameLike("ROLE_EMPRESA")==null || rRepository.findByNameLike("ROLE_ADMIN")==null){
        if(registrationDto.getTipo().equals("cpf") ){
            user.setRoles(Arrays.asList(new Role("ROLE_USER")));
            return usuariosRepository.save(user);
        }else if(registrationDto.getTipo().equals("cnpj") ){
            user.setRoles(Arrays.asList(new Role("ROLE_EMPRESA")));
            return usuariosRepository.save(user);
        }else {
            user.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
            return usuariosRepository.save(user);
        }
    }else{
        if(registrationDto.getTipo().equals("cpf") ){
            user.setRoles(Arrays.asList( rRepository.findByNameLike("ROLE_USER")));
            return usuariosRepository.save(user);
        }else if(registrationDto.getTipo().equals("cnpj") ){
            user.setRoles(Arrays.asList(rRepository.findByNameLike("ROLE_EMPRESA")));
            return usuariosRepository.save(user);
        }else {
            user.setRoles(Arrays.asList(rRepository.findByNameLike("ROLE_ADMIN")));
            return usuariosRepository.save(user);
        }
    } 
    }
    

    @Override
    public usuario deleteById(Integer id) {
        
        return deleteById(id);
    }
    @Override
    public usuario findByUsername(String username){
        return usuariosRepository.findByUsername(username);
    }

    @Override
    public List<usuario> findByNomeLike(String nome) {
        return usuariosRepository.findByNomeLike(nome);
    }

    @Override
    public Optional<usuario> findByIdentificacao(Long identificacao) {
        return usuariosRepository.findByIdentificacao(identificacao);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        usuario user = usuariosRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(Role -> new SimpleGrantedAuthority(Role.getName())).collect(Collectors.toList());
    }

    @Override
    public usuario save(usuario usuarioexistente) {
       
    if(rRepository.findByNameLike("ROLE_USER")==null || rRepository.findByNameLike("ROLE_EMPRESA")==null || rRepository.findByNameLike("ROLE_ADMIN")==null){
        if(usuarioexistente.getTipo().equals("cpf") ){
            usuarioexistente.setRoles(Arrays.asList(new Role("ROLE_USER")));
            return usuariosRepository.save(usuarioexistente);
        }else if(usuarioexistente.getTipo().equals("cnpj") ){
            usuarioexistente.setRoles(Arrays.asList(new Role("ROLE_EMPRESA")));
            return usuariosRepository.save(usuarioexistente);
        }else {
            usuarioexistente.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
            return usuariosRepository.save(usuarioexistente);
        }
    }else{
        if(usuarioexistente.getTipo().equals("cpf") ){
            usuarioexistente.setRoles(Arrays.asList( rRepository.findByNameLike("ROLE_USER")));
            return usuariosRepository.save(usuarioexistente);
        }else if(usuarioexistente.getTipo().equals("cnpj") ){
            usuarioexistente.setRoles(Arrays.asList(rRepository.findByNameLike("ROLE_EMPRESA")));
            return usuariosRepository.save(usuarioexistente);
        }else {
            usuarioexistente.setRoles(Arrays.asList(rRepository.findByNameLike("ROLE_ADMIN")));
            return usuariosRepository.save(usuarioexistente);
        }
    } 
       
    }

    
    
}
