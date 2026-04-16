package br.com.foodhub.foodhub.Services;

import br.com.foodhub.foodhub.dtos.AlterarSenhaDTO;
import br.com.foodhub.foodhub.dtos.UsuarioResponseDTO;
import br.com.foodhub.foodhub.dtos.UsuarioResquestDTO;
import br.com.foodhub.foodhub.entities.DonoRestaurante;
import br.com.foodhub.foodhub.exceptions.RecursoNaoEncontradoException;
import br.com.foodhub.foodhub.repositories.DonoRestauranteRepository;

public class DonoRestauranteService {

    private final DonoRestauranteRepository donoRepository;
    private final UsuarioService usuarioService;

    public DonoRestauranteService(DonoRestauranteRepository donoRepository, UsuarioService usuarioService) {
        this.donoRepository = donoRepository;
        this.usuarioService = usuarioService;
    }

    public UsuarioResponseDTO saveDono(UsuarioResquestDTO dto) {
        usuarioService.validarEmailDisponivel(dto.email());

        var dono = new DonoRestaurante();
        dono.setNome(dto.nome());
        dono.setEmail(dto.email());
        dono.setLogin(dto.login());
        dono.setSenha(usuarioService.criptografarSenha(dto.senha()));
        dono.setEndereco(dto.endereco());
        dono.setDataUltAlteracao(usuarioService.dataAtual());

        return UsuarioResponseDTO.from(donoRepository.save(dono));
    }

    public UsuarioResponseDTO atualizarCliente(Long id, UsuarioResponseDTO dto) {
        var dono = donoRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Id não encontrado: " + id));
        if (!dono.getEmail().equals(dto.email())) {
            usuarioService.validarEmailDisponivel(dto.email());
        }

        dono.setNome(dto.nome());
        dono.setEmail(dto.email());
        dono.setLogin(dto.login());
        dono.setEndereco(dto.endereco());
        dono.setDataUltAlteracao(usuarioService.dataAtual());

        return UsuarioResponseDTO.from(donoRepository.save(dono));
    }

    public void deletarCliente(Long id) {
        if (!donoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Dono não encontrado com id: " + id);
        }

        donoRepository.deleteById(id);
    }


    public void alterarSenha(Long id, AlterarSenhaDTO dto) {
        if (!donoRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Cliente não encontrado com id: " + id);
        }
        usuarioService.alterarSenha(id, dto);
    }

}
