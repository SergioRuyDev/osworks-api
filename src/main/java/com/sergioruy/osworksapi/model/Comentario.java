package com.sergioruy.osworksapi.model;

import com.sergioruy.osworksapi.domain.model.OrdemServico;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private OrdemServico ordemServico;

    private String descricao;
    private OffsetDateTime dataEnvio;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemServico(OrdemServico ordemServico) {
        this.ordemServico = ordemServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public OffsetDateTime getDataEnvio() {
        return dataEnvio;
    }

    public void setDataEnvio(OffsetDateTime dataEnvio) {
        this.dataEnvio = dataEnvio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comentario)) return false;

        Comentario that = (Comentario) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getOrdemServico() != null ? !getOrdemServico().equals(that.getOrdemServico()) : that.getOrdemServico() != null)
            return false;
        if (getDescricao() != null ? !getDescricao().equals(that.getDescricao()) : that.getDescricao() != null)
            return false;
        return getDataEnvio() != null ? getDataEnvio().equals(that.getDataEnvio()) : that.getDataEnvio() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getOrdemServico() != null ? getOrdemServico().hashCode() : 0);
        result = 31 * result + (getDescricao() != null ? getDescricao().hashCode() : 0);
        result = 31 * result + (getDataEnvio() != null ? getDataEnvio().hashCode() : 0);
        return result;
    }
}
