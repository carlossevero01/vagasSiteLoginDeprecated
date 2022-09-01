package com.example.vagasSite.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.vagasSite.repository.usuariosRepository;
import com.example.vagasSite.services.usuariosServices;
import com.example.vagasSite.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
    private usuariosServices usuariosServices;
    @Autowired
    usuariosRepository uRepository;
    public UserRegistrationController(usuariosServices usuariosServices) {
        super();
        this.usuariosServices = usuariosServices;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto(){
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(){
        return "registration";
    }
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") UserRegistrationDto registrationDto, @RequestParam("file") MultipartFile img, RedirectAttributes attributes){
        if(!img.isEmpty()){
            try {
                byte[] bytes = img.getBytes();
                Path caminho = Paths.get("./src/main/resources/static/images/"+img.getOriginalFilename());
                Files.write(caminho,bytes);
                registrationDto.setImg(img.getOriginalFilename());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            registrationDto.setImg("não selecionado");
        }
       if(uRepository.findByUsername(registrationDto.getUsername())==null && uRepository.findByIdentificacao(registrationDto.getIdentificacao()).isEmpty()){
        usuariosServices.save(registrationDto);
        attributes.addFlashAttribute("sucesso","Cadastro efetuado!");
        return "redirect:/registration?success";
       }else{
        attributes.addFlashAttribute("erro","Consulte os campos : Nome de usuario ou Identificação");
        return "redirect:/registration?error";
       }
        
        
    }
}
