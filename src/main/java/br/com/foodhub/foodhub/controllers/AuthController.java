package br.com.foodhub.foodhub.controllers;

import br.com.foodhub.foodhub.Services.UsuarioService;
import br.com.foodhub.foodhub.dtos.LoginRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequestDTO dto) {
        usuarioService.validarLogin(dto.login(), dto.senha());
        return ResponseEntity.ok("Login realizado com sucesso");
    }
}
