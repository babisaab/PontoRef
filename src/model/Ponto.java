package model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ponto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String entrada;
    private String saida;

    @ManyToOne
    private Funcionario funcionario;
    @ManyToOne
    private Horario horario;

    public Ponto() {}

    public Ponto(String entrada, String saida, Funcionario funcionario, Horario horario) {
        this.entrada = entrada;
        this.saida = saida;
        this.funcionario = funcionario;
        this.horario = horario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSaida() {
        return saida;
    }

    public void setSaida(String saida) {
        this.saida = saida;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public String getDiaSemana() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(getDataAtual());
        int day = cal.get(Calendar.DAY_OF_WEEK);
        return null;
    }

    public java.util.Date getDataAtual() {
        Calendar c = Calendar.getInstance();
        return c.getTime();

    }

    public Calendar getHoraAtual() {
        Calendar c = Calendar.getInstance();
        c.get(Calendar.HOUR_OF_DAY);
        c.get(Calendar.MINUTE);
        c.get(Calendar.SECOND);
        return c;
    }

}
