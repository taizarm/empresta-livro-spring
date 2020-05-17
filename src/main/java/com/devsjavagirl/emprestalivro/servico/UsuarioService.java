package com.devsjavagirl.emprestalivro.servico;

import com.devsjavagirl.emprestalivro.dominio.Usuario;
import com.devsjavagirl.emprestalivro.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void salvaUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    /**
     * Regra sem sentido, apenas para facilitar implementacao
     */
    public boolean isValidUser(Usuario usuario) {
        return usuario.getCodigo() % 2 == 0;
    }

}
