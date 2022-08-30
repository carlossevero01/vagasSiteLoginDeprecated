package com.example.vagasSite.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.example.vagasSite.model.usuario;
import com.example.vagasSite.model.vagaUsuarios;
import com.example.vagasSite.model.vagas;

import com.example.vagasSite.repository.usuariosRepository;
import com.example.vagasSite.repository.vagaUsuariosRepository;
import com.example.vagasSite.repository.vagasRepository;
import com.example.vagasSite.web.dto.UserRegistrationDto;
import com.example.vagasSite.web.dto.VagaRegistrationDto;



@Controller
public class vagasSiteController {
    @Autowired
    usuariosRepository uRepository;
    @Autowired
    vagasRepository vRepository;
    @Autowired
    vagaUsuariosRepository vuRepository;
    ////////////////////LOGIN////////////////////////////
    @GetMapping("/login")
    public String login(){
        return "login";
    }   
    @GetMapping("/")
    public String home(){
        return "redirect:/index/inicial";
    } 
    ////////////////////TELA INICIAL////////////////////////////
    @GetMapping("/index/inicial")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        List<vagas> vagasList = vRepository.findAll(); 
        List<usuario> usuariosList = uRepository.findAll();
        mv.addObject("vagas", vagasList);
        mv.addObject("usuarios", usuariosList);
        return mv;
    }
    
    /////////////////////////////INICIO CADASTROS/////////////////////////////////
    
    
    @GetMapping("/index/cadVaga")
    public String insertVaga(){
        return "cadVaga";
    }
    @PostMapping("/index/cadVaga")
    public String saveVaga(@Valid VagaRegistrationDto v, BindingResult result, RedirectAttributes attributes){
        if(v.getVisible()==null) v.setVisible(false);
        else v.setVisible(true);

        if(result.hasErrors()){
            attributes.addFlashAttribute("erro","Verifique os campos obrigatórios:"+v.toString());
            return "redirect:/index/cadVaga";
        }
       usuario u = uRepository.findByUsername(v.getIdUsuario());
       vagas vaga = new vagas(v.getNome(), v.getTipo(), v.getDescricao(), u.getId(), v.getVisible());
       vRepository.save(vaga);
       attributes.addFlashAttribute("sucesso","Vaga cadastrada");
       return "redirect:/index/inicial";       
    }
    
    
///////////////////////////LISTAR//////////////////////
    
    @GetMapping("/index/listUsuario")
    public ModelAndView getUsuarios(){
        ModelAndView mv = new ModelAndView("usuarios");
        List<usuario> usuariosList = uRepository.findAll(); 
        mv.addObject("usuarios", usuariosList);
        return mv;
    }
    
    ////////////////////////INICIO DELETAR////////////////////////
   
    @GetMapping("/index/usuario/delete/{id}")
    public String deleteUsuario(@PathVariable("id") int id, RedirectAttributes attributes){
       try{
        uRepository.deleteById(id);
        attributes.addFlashAttribute("sucesso","Usuario "+id+" deletado");
        return "redirect:/index/listUsuario";
       }catch(Exception e){
        attributes.addFlashAttribute("erro","id inexistente ou erro desconhecido");
        return "redirect:/index/listUsuario";
       } 
    }
    
    @GetMapping("/index/vaga/delete/{id}")
    public String deleteVaga(@PathVariable("id") int id, RedirectAttributes attributes){
       try{
        vRepository.deleteById(id);
        attributes.addFlashAttribute("sucesso","Vaga "+id+" deletada");
        return "redirect:/index";
       }catch(Exception e){
        attributes.addFlashAttribute("erro","id inexistente ou erro desconhecido");
        return "redirect:/index";
       } 
    }
    //////////////////////////FIM DELETAR/////////////////////////////
    //////////////////////////INICIO UPDATE////////////////////////////
    
    @GetMapping(value = "/index/usuario/update")                   
    public ModelAndView updateUsuario(@RequestParam("username") String username){                            
        ModelAndView mv = new ModelAndView("updateUsuario");                        
        usuario usu =  uRepository.findByUsername(username);     
        System.out.println(usu.toString()); 
        try {
            mv.addObject("id",usu.getId());
            mv.addObject("nome",usu.getNome());
            mv.addObject("email",usu.getEmail());
            mv.addObject("telefone",usu.getTelefone());
            mv.addObject("tipo",usu.getTipo());
            mv.addObject("identificacao",usu.getIdentificacao());
            mv.addObject("dataNascimento",usu.getDataNascimento());
            mv.addObject("img",usu.getImg());
            mv.addObject("username",usu.getUsername());
            mv.addObject("password",usu.getPassword());
        } catch (Exception e) {
            System.out.println(e.toString() + username + usu.toString());
        }                         
       
        
        return mv;
    }
   
    @PostMapping("/index/usuario/update")                   //Verificação URL, valor = endereço e method = POST (GET,POST,etc.) enviar valores para o formulario
    public String saveUpdateUsuario(@RequestParam("id") int id, UserRegistrationDto usuario, BindingResult result, RedirectAttributes attributes, @RequestParam("file") MultipartFile img){
       usuario.setDataNascimento(usuario.getDataNascimento());
       System.out.println(usuario.getDataNascimento());
        if(result.hasErrors()){
            attributes.addFlashAttribute("erro","Verifique os campos obrigatórios:"+usuario.toString());
            return "redirect:/index/inicial";
        }
        try {
            
            usuario postExistente = uRepository.findById(id).orElse(null);       
            postExistente.setNome(usuario.getNome());
            postExistente.setEmail(usuario.getEmail());
            postExistente.setTelefone(usuario.getTelefone());
            postExistente.setTipo(usuario.getTipo());
            postExistente.setIdentificacao(usuario.getIdentificacao());
            postExistente.setDataNascimento(LocalDate.parse(usuario.getDataNascimento()));
            postExistente.setUsername(usuario.getUsername());
            postExistente.setPassword( new BCryptPasswordEncoder().encode(usuario.getPassword()) );
           
            if(img.isEmpty()){   
                attributes.addFlashAttribute("erro","Imagem obrigatória não inserida");
                return "redirect:/index/inicial";
            }else{
                byte[] bytes = img.getBytes();
                Path caminho = Paths.get("./src/main/resources/static/images/"+img.getOriginalFilename());
                Files.write(caminho,bytes);
                postExistente.setImg(img.getOriginalFilename());
                uRepository.save(postExistente);
                attributes.addFlashAttribute("sucesso","Post Editado com sucesso ");
                return "redirect:/logout";
            }     
        } catch (Exception e) {
            attributes.addFlashAttribute("erro","Não foi possivel editar, confira os campos de texto"+ "id:"+usuario.toString());
            return "redirect:/index/inicial";
        }
    }

    @GetMapping("/index/vaga/update/{id}")                   
    public ModelAndView updateVaga(@PathVariable("id") int id){                            
        ModelAndView mv = new ModelAndView("updateVaga");                        
        Optional<vagas> vag =  vRepository.findById(id);                               
        mv.addObject("nome",vag.get().getNome());
        mv.addObject("tipo",vag.get().getTipo());
        mv.addObject("idUsuario",vag.get().getIdUsuario());
        mv.addObject("visible",vag.get().getVisible());
        mv.addObject("descricao",vag.get().getDescricao());
        return mv;
    }
      
    @PostMapping("/index/vaga/update/{id}")                   //Verificação URL, valor = endereço e method = POST (GET,POST,etc.) enviar valores para o formulario
    public String saveUpdateVaga(@PathVariable("id") int id, VagaRegistrationDto vagadto, RedirectAttributes attributes){
        try {
            vagas vaga = new vagas(vagadto.getNome(), vagadto.getTipo(), vagadto.getDescricao(), Integer.valueOf(vagadto.getIdUsuario()), vagadto.getVisible());
            vagas postExistente = vRepository.findById(vaga.getId()).orElse(null);
            postExistente.setNome(vaga.getNome());
            postExistente.setTipo(vaga.getTipo());
            postExistente.setDescricao(vaga.getDescricao());
            postExistente.setIdUsuario(vaga.getIdUsuario());
            postExistente.setVisible(vaga.getVisible());
            vRepository.save(postExistente);
            attributes.addFlashAttribute("sucesso","Post Editado com sucesso ");
            return "redirect:/index";
            } catch (Exception e) {
            attributes.addFlashAttribute("erro","Não foi possivel editar, confira os campos de texto");
            return "redirect:/index/vaga/update/{id}";
            }
    }
//////////////////////////FIM UPDATE/////////////////////////////
//////////////////////////PESQUISAR//////////////////////////////
    @GetMapping("/pesquisar")                   
    public ModelAndView getVagasByPesquisa(@RequestParam("pesquisar") String pesquisar){
        ModelAndView mv = new ModelAndView("resultado"); 
        List<usuario> usus = uRepository.findByNomeLike("%"+pesquisar+"%");
        
        List<vagas> vagasResult = new ArrayList<>();
        for(usuario usu : usus){
                vagasResult.addAll(vRepository.findByidUsuario(usu.getId()));
                System.out.println(vagasResult.toString());
        }
        mv.addObject("usuarios", usus);
        mv.addObject("vagas",vagasResult);       
        return mv;
    }
////////////////////////////FIM PESQUISAR//////////////////////////////
////////////////////////////IMAGEM /////////////////////////////////////
    @GetMapping("index/images/{img}")
    @ResponseBody
    public byte[] getImg(@PathVariable("img") String img) throws IOException{
        File imagemArquivo = new File("./src/main/resources/static/images/"+img);
        if(img != null || img.trim().length()>0){
           
            return Files.readAllBytes(imagemArquivo.toPath());
        }
        
        return null;
    }
    @GetMapping("/index/vaga/images/{img}")
    @ResponseBody
    public byte[] getImgPost(@PathVariable("img") String img) throws IOException{
        File imagemArquivo = new File("./src/main/resources/static/images/"+img);
        if(img != null || img.trim().length()>0){
           
            return Files.readAllBytes(imagemArquivo.toPath());
        }
        
        return null;
    }
    @GetMapping("/index/vaga/{id}/images/{img}")
    @ResponseBody
    public byte[] getImgListInscritos(@PathVariable("img") String img) throws IOException{
        File imagemArquivo = new File("./src/main/resources/static/images/"+img);
        if(img != null || img.trim().length()>0){
           
            return Files.readAllBytes(imagemArquivo.toPath());
        }
        
        return null;
    }
    
////////////////////////////FIM IMAGEM//////////////////////////////
////////////////////////////VAGA ID/////////////////////////////////
    @GetMapping("index/vaga/{id}")                   
    public ModelAndView getVaga(@PathVariable("id") int id){                            
        ModelAndView mv = new ModelAndView("vaga");                        
        Optional<vagas> vaga =  vRepository.findById(id); 
        Optional<usuario> usu = uRepository.findById(vaga.get().getIdUsuario());                              
        mv.addObject("nome",vaga.get().getNome());
        mv.addObject("tipo",vaga.get().getTipo());
        mv.addObject("idUsuario",vaga.get().getIdUsuario());
        mv.addObject("visible",vaga.get().getVisible());
        mv.addObject("id",vaga.get().getId());
        mv.addObject("img",usu.get().getImg());
        mv.addObject("nomeUsu",usu.get().getNome());
        mv.addObject("descricao",vaga.get().getDescricao());
        return mv;
    }
    //////////////////////////////////CANDIDATAR-SE//////////////////////////
    
    
  
    @PostMapping("index/vaga/inscreverse/{id}")
   public String getSaveInscreverVaga(@PathVariable("id") int id, @RequestParam Long identificacao, RedirectAttributes attributes){
    try {
        vagaUsuarios vu = new vagaUsuarios();
        vu.setData(LocalDate.now());
        vu.setIdVaga(id);
        Optional<usuario> usu = uRepository.findByIdentificacao(identificacao);
        vu.setIdUsuario(usu.get().getId());         
        vuRepository.save(vu);  
        attributes.addFlashAttribute("sucesso","Inscrito com sucesso ");
        return "redirect:/index";
    } catch (Exception e) {
        attributes.addFlashAttribute("erro","Não foi possivel realizar a inscrição");
        return "index/vaga/inscreverse/{id}";
    }
   }

   @GetMapping("/index/vaga/{id}/listar")
   public ModelAndView getInscritosPorVaga(@PathVariable("id") int id){
       ModelAndView mv = new ModelAndView("usuarios");
       List<vagaUsuarios> ListvuVaga = vuRepository.findByIdVaga(id);
       List<usuario> usuariosList = new ArrayList<>();
       for (vagaUsuarios vu : ListvuVaga) {
        usuariosList.add(uRepository.findById(vu.getIdUsuario()).get());    
       }
       System.out.println(usuariosList.toString());
       mv.addObject("usuarios", usuariosList);
       return mv;
   }
}
