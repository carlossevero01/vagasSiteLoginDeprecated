package com.example.vagasSite.model;


import java.time.LocalDate;
import java.util.Collection;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;




@Entity
@Table(name = "usuario" , uniqueConstraints = @UniqueConstraint(columnNames = "identificacao"))
public class usuario{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable=false)
    private String nome;
    @Column(nullable=false)
    private String email;
    @Column(nullable=false)
    private long telefone;
    @Column(nullable=false)
    private String tipo;
    @Column(nullable=false,unique = true)
    private long identificacao;
    @Column(nullable=false)
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy")
	private LocalDate dataNascimento;
    private String img;
    @Column(nullable=false, unique=true)
    private String username;
    @Column(nullable=false)
    private String password;

     @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
     @JoinTable(name = "TB_USERS_ROLES",
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
     private Collection<Role> roles;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public long getTelefone() {
        return telefone;
    }
    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }
    
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public long getIdentificacao() {
        return identificacao;
    }
    public void setIdentificacao(long identificacao) {
        this.identificacao = identificacao;
    }
  
    
    public usuario(String nome, String email, long telefone, String tipo, long identificacao, LocalDate dataNascimento,
            String img, String username, String password, Collection<Role> roles) {
        super();
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.tipo = tipo;
        this.identificacao = identificacao;
        this.dataNascimento = dataNascimento;
        this.img = img;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
    public usuario() {
    }
    
    public usuario orElseThrow(Object object) {
        return null;
    }
    @Override
    public String toString() {
        return "usuario [dataNascimento=" + dataNascimento + ", email=" + email + ", id=" + id + ", identificacao="
                + identificacao + ", img=" + img + ", nome=" + nome + ", password=" + password + ", roles=" + roles
                + ", telefone=" + telefone + ", tipo=" + tipo + ", username=" + username + "]";
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public Collection<Role> getRoles() {
        return roles;
    }
    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
