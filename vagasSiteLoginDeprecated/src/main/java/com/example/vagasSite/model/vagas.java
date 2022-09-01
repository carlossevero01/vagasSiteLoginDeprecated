package com.example.vagasSite.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="vagas")
public class vagas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String tipo;
    @Column(nullable = false)
	@Lob
    private String descricao;
    @Column(nullable = false)
    private int idUsuario;
    
    private Boolean visible;
                                //VERIFICAR SE DA PRA INTERAGIR COM AS TABELAS MANY TO MANY E ADC AQUI e EXCLUIR VAGAS_USUARIOS 
    
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
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Boolean getVisible() {
        return visible;
    }
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }
    
    
    public vagas(@NotNull @NotBlank String nome, @NotNull @NotBlank String tipo, @NotBlank String descricao,
            @NotNull int idUsuario, Boolean visible) {
        this.nome = nome;
        this.tipo = tipo;
        this.descricao = descricao;
        this.idUsuario = idUsuario;
        this.visible = visible;
    }
    @Override
    public String toString() {
        return "vagas [descricao=" + descricao + ", idUsuario=" + idUsuario + ", nome=" + nome
                + ", tipo=" + tipo + ", visible=" + visible + "]";
    }
    public vagas() {
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    

}
