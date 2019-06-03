package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cartao implements EntidadeBase, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String tipo;
    private String cor;
    private String motivo;
    private String data;

    @ManyToOne
    private Funcionario funcionario;
    
    public Cartao() {}

    public Cartao(String tipo, String cor, String motivo, String data, Funcionario funcionario) {
        this.tipo = tipo;
        this.cor = cor;
        this.motivo = motivo;
        this.data = data;
        this.funcionario = funcionario;
        }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Funcionario getfuncionario() {
        return funcionario;
    }

    public void setfuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
 
}
