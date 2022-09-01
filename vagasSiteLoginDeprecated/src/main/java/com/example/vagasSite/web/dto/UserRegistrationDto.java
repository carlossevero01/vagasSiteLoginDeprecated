package com.example.vagasSite.web.dto;





public class UserRegistrationDto {
    private String nome;
    private String email;
    private long telefone;
    private String tipo;
    private long identificacao;
	private String dataNascimento;
    private String img;
    private String username;
    private String password;
    public UserRegistrationDto() {
    }
   
    public UserRegistrationDto(String nome, String email, long telefone, String tipo, long identificacao,
            String dataNascimento, String img, String username, String password) {
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
    public String getDataNascimento() {
        return dataNascimento;
    }
    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserRegistrationDto [dataNascimento=" + dataNascimento + ", email=" + email + ", identificacao="
                + identificacao + ", img=" + img + ", nome=" + nome + ", password=" + password + ", telefone="
                + telefone + ", tipo=" + tipo + ", username=" + username + "]";
    }

    
    
}
