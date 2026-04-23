package br.com.foodhub.foodhub.Services;

import br.com.foodhub.foodhub.dtos.AlterarSenhaDTO;
import br.com.foodhub.foodhub.dtos.UsuarioResponseDTO;
import br.com.foodhub.foodhub.dtos.UsuarioResquestDTO;
import br.com.foodhub.foodhub.entities.Cliente;
import br.com.foodhub.foodhub.exceptions.RecursoNaoEncontradoException;
import br.com.foodhub.foodhub.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private  final UsuarioService usuarioService;

    public ClienteService(ClienteRepository clienteRepository, UsuarioService usuarioService) {
        this.clienteRepository = clienteRepository;
        this.usuarioService = usuarioService;
    }

    public UsuarioResponseDTO saveCliente(UsuarioResquestDTO dto) {
        usuarioService.validarEmailDisponivel(dto.email());

        var cliente = new Cliente();
        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        cliente.setLogin(dto.login());
        cliente.setSenha(usuarioService.criptografarSenha(dto.senha()));
        cliente.setEndereco(dto.endereco());
        cliente.setDataUltAlteracao(usuarioService.dataAtual());

        return UsuarioResponseDTO.from(clienteRepository.save(cliente));
    }

    public UsuarioResponseDTO atualizarCliente(Long id, UsuarioResquestDTO dto) {
        var cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Id não encontrado: " + id));

        if (!cliente.getEmail().equals(dto.email())) {
            usuarioService.validarEmailDisponivel(dto.email());
        }

        cliente.setNome(dto.nome());
        cliente.setEmail(dto.email());
        cliente.setLogin(dto.login());
        cliente.setEndereco(dto.endereco());
        cliente.setDataUltAlteracao(usuarioService.dataAtual());

        return UsuarioResponseDTO.from(clienteRepository.save(cliente));
    }

    public void deletarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Cliente não encontrado com id: " + id);
        }

        clienteRepository.deleteById(id);
    }

    public List<UsuarioResponseDTO> buscarPorNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome)
                .stream()
                .map(UsuarioResponseDTO::from)
                .toList();
    }


    public void alterarSenha(Long id, AlterarSenhaDTO dto) {
        if (!clienteRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("Cliente não encontrado com id: " + id);
        }
        usuarioService.alterarSenha(id, dto);
    }


}
