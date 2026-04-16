package br.com.foodhub.foodhub.Services;

import br.com.foodhub.foodhub.dtos.AlterarSenhaDTO;
import br.com.foodhub.foodhub.dtos.UsuarioResquestDTO;
import br.com.foodhub.foodhub.entities.Cliente;
import br.com.foodhub.foodhub.entities.Usuario;
import br.com.foodhub.foodhub.exceptions.EmailJaCadastradoException;
import br.com.foodhub.foodhub.exceptions.RecursoNaoEncontradoException;
import br.com.foodhub.foodhub.exceptions.SenhaInvalidaExpecption;
import br.com.foodhub.foodhub.repositories.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void validarEmailDisponivel(String email) {
        if (usuarioRepository.existsByEmail(email)){
            throw new EmailJaCadastradoException(email);
        }
    }

    public String criptografarSenha(String senha) {
        return passwordEncoder.encode(senha);
    }

    public LocalDate dataAtual() {
        return LocalDate.now();
    }

    public void alterarSenha(Long id, AlterarSenhaDTO dto) {
        var usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario não encontrado com id" + id));

        if (!passwordEncoder.matches(dto.senhaAtual(), usuario.getSenha())) {
            throw new SenhaInvalidaExpecption("Senha atual incorreta");
        }

        if (!dto.novaSenha().equals(dto.confirmaSenha())) {
            throw new SenhaInvalidaExpecption("A nova senha e a confirmação não coincidem");
        }
        if (!passwordEncoder.matches(dto.novaSenha(), usuario.getSenha())) {
            throw new SenhaInvalidaExpecption("A senha deve ser diferente da atual");
        }

        usuario.setSenha(passwordEncoder.encode(dto.novaSenha()));
        usuario.setDataUltAlteracao(LocalDate.now());
        usuarioRepository.save(usuario);
    }

}
