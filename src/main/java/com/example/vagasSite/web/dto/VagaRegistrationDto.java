package com.example.vagasSite.web.dto;

public class VagaRegistrationDto {
    private String nome;
    private String tipo;
    private String descricao;
    private String idUsuario;
    private Boolean visible;
    public VagaRegistrationDto() {
    }
    public VagaRegistrationDto(String nome, String tipo, String descricao, String idUsuario, Boolean visible) {
        super();
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.idUsuario = idUsuario;
        this.visible = visible;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public String getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    public Boolean getVisible() {
        return visible;
    }
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    @Override
    public String toString() {
        return "VagaRegistrationDto [descricao=" + descricao + ", idUsuario=" + idUsuario + ", nome=" + nome + ", tipo="
                + tipo + ", visible=" + visible + "]";
    }
   
    
}
