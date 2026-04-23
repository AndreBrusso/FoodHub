package br.com.foodhub.foodhub.controllers;

import br.com.foodhub.foodhub.Services.ClienteService;
import br.com.foodhub.foodhub.dtos.AlterarSenhaDTO;
import br.com.foodhub.foodhub.dtos.UsuarioResponseDTO;
import br.com.foodhub.foodhub.dtos.UsuarioResquestDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<UsuarioResponseDTO>> buscarNome(@RequestParam String nome) {
        logger.info("Buscado cleintes por nome " + nome);
        return ResponseEntity.ok(clienteService.buscarPorNome(nome));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> saveCliente(@Valid @RequestBody UsuarioResquestDTO dto) {
        logger.info("Recebendo requisição para criar cliente: " + dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.saveCliente(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarCliente(
            @PathVariable("id") long id,
            @Valid @RequestBody UsuarioResquestDTO dto
    ) {
        logger.info("Recebendo requisição para atualizar cliente: " + dto);
        return ResponseEntity.ok(clienteService.atualizarCliente(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable("id") Long id) {
        logger.info("Delete cliente +" + id);
        clienteService.deletarCliente(id);
        return  ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/senha")
    public ResponseEntity<Void> alterarSenha(
            @PathVariable Long id,
            @Valid @RequestBody AlterarSenhaDTO dto
            ){
        clienteService.alterarSenha(id, dto);
        logger.info("PATCH -> /clientes/{}/senha", id);
        return ResponseEntity.noContent().build();
    }




}
