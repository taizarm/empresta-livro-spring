package com.devsjavagirl.emprestalivro.servico;

import static com.devsjavagirl.emprestalivro.dominio.Emprestimo.EmprestimoStatus.*;

import com.devsjavagirl.emprestalivro.dominio.Emprestimo;
import com.devsjavagirl.emprestalivro.repositorio.EmprestimoRepository;
import org.springframework.stereotype.Service;

@Service
public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository;
    private UsuarioService usuarioService;

    public EmprestimoService(
        final EmprestimoRepository emprestimoRepository,
        final UsuarioService usuarioService
    ) {
        this.emprestimoRepository = emprestimoRepository;
        this.usuarioService = usuarioService;
    }


//    public void save(Emprestimo emprestimo){
//        if (!usuarioService.isValidUser(emprestimo.getUsuario())) {
//            throw new IllegalArgumentException("Usuario invalido");
//        }
//
//        emprestimoRepository.save(emprestimo);
//    }

    public Emprestimo save(Emprestimo emprestimo){

        if (!usuarioService.isValidUser(emprestimo.getUsuario())) {
            throw new IllegalArgumentException("Usuario invalido");
        }

        emprestimo.setStatus(ATIVO);
        emprestimoRepository.save(emprestimo);

        return emprestimo;
    }

}
