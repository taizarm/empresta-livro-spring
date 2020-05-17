package com.devsjavagirl.emprestalivro.dominio;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Emprestimo {

    @Id
    @GeneratedValue
    private Long codigo;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Livro livro;

    private EmprestimoStatus status;

    public enum EmprestimoStatus {
         ATIVO, DEVOLVIDO
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public EmprestimoStatus getStatus() {
        return status;
    }

    public void setStatus(final EmprestimoStatus status) {
        this.status = status;
    }
}
