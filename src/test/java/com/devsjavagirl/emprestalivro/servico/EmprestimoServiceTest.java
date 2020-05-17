package com.devsjavagirl.emprestalivro.servico;

import static com.devsjavagirl.emprestalivro.dominio.Emprestimo.EmprestimoStatus.ATIVO;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.devsjavagirl.emprestalivro.dominio.Emprestimo;
import com.devsjavagirl.emprestalivro.repositorio.EmprestimoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.ArgumentCaptor;

public class EmprestimoServiceTest {

    EmprestimoRepository emprestimoRepository = mock(EmprestimoRepository.class);
    UsuarioService usuarioService = mock(UsuarioService.class);
    Emprestimo emprestimo = new Emprestimo();

    EmprestimoService emprestimoService = new EmprestimoService(emprestimoRepository, usuarioService);

    @Test
    public void deveSalvarEmprestimoSeUsuarioValido() {
        /*
         * O que eu preciso fazer para esse teste funcionar?
         * 1) Usuario deve ser válido
         * 2) Chamar método que salva emprestimo (método sob test)
         * 3) Verificar se o emprestimo foi realmente salvo
         * 4) Verificar se o status do emprestimo retornado é ATIVO
         */

        // 1) Usuario deve ser válido
        when(usuarioService.isValidUser(emprestimo.getUsuario())).thenReturn(true);

        // 2) Chamar método que salva emprestimo (método sob test)
        Emprestimo emprestimoSalvo = emprestimoService.save(emprestimo);

        //3) Verificar se o emprestimo foi realmente salvo
        verify(emprestimoRepository).save(emprestimo);

        //4) Verifica se o status do emprestimo retornado é ATIVO
        Assert.assertEquals(ATIVO, emprestimoSalvo.getStatus());
    }

    @Test
    public void deveSalvarEmprestimoSeUsuarioValidoComArgumentCaptor() {
        /*
         * O que eu preciso configurar para esse teste funcionar?
         * 1) Usuario deve ser válido
         * 2) Chamar método que salva emprestimo (método sob test)
         * 3) Verificar se o emprestimo foi realmente salvo
         * 4) Verifica se o status do emprestimo passado como argumento é ATIVO
         */

        // 1) Usuario deve ser válido
        when(usuarioService.isValidUser(emprestimo.getUsuario())).thenReturn(true);

        // 2) Chamar método que salva emprestimo (método sob test)
        // ATENCAO: Nesse caso, como estou usando ArgumentCaptor, meu metodo poderia ser void tranquilamente
        // Pq eu vou verificar o estado do meu objeto atraves do ArgumentCaptor
        emprestimoService.save(emprestimo);

        ArgumentCaptor<Emprestimo> captor = ArgumentCaptor.forClass(Emprestimo.class);
        verify(emprestimoRepository).save(captor.capture());
        Emprestimo emprestimoPassadoComoArgumento = captor.getValue();

        //3) Verificar se o emprestimo foi realmente salvo
        verify(emprestimoRepository).save(emprestimo);

        //4) Verifica se o status do emprestimo é ATIVO
        Assert.assertEquals(ATIVO, emprestimoPassadoComoArgumento.getStatus());
    }

    @Test
    public void deveRetornarExcecaoSeUsuarioForInvalido() {
        /*
         * O que eu preciso configurar para esse teste funcionar?
         * 1) Usuario deve ser inválido
         * 2) Chamar método que salva emprestimo (método sob test)
         * 3) Verificar se o método terminou em excecao
         */

        // 1) Usuario deve ser válido
        when(usuarioService.isValidUser(emprestimo.getUsuario())).thenReturn(false);

        // 2) Chamar método que salva emprestimo (método sob test)
        // 3) Verificar se o método terminou em excecao
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            emprestimoService.save(emprestimo);
        });

    }
}