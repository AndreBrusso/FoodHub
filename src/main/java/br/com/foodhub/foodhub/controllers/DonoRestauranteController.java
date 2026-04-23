package br.com.foodhub.foodhub.controllers;

import br.com.foodhub.foodhub.Services.DonoRestauranteService;
import br.com.foodhub.foodhub.dtos.AlterarSenhaDTO;
import br.com.foodhub.foodhub.dtos.UsuarioResponseDTO;
import br.com.foodhub.foodhub.dtos.UsuarioResquestDTO;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donos")
public class DonoRestauranteController {

    private static final Logger logger = LoggerFactory.getLogger(DonoRestauranteController.class);

    private DonoRestauranteService donoRestauranteService;

    public DonoRestauranteController(DonoRestauranteService donoRestauranteService) {
        this.donoRestauranteService = donoRestauranteService;
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<UsuarioResponseDTO>> buscarPorNome(@RequestParam String nome) {
        logger.info("Buscando donos por nome:" + nome);
        return ResponseEntity.ok(donoRestauranteService.buscarPorNome(nome));
    }



    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> saveDono(@Valid @RequestBody UsuarioResquestDTO dto) {
        logger.info("Recebendo requisição para criar Dono: " + dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(donoRestauranteService.saveDono(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizarDono(
            @PathVariable("id") long id,
            @Valid @RequestBody UsuarioResquestDTO dto
    ) {
        logger.info("Recebendo requisição para atualizar cliente: " + dto);
        return ResponseEntity.ok(donoRestauranteService.atualizarDono(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDono(@PathVariable("id") Long id) {
        logger.info("Delete dono +" + id);
        donoRestauranteService.deletarDono(id);
        return  ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/senha")
    public ResponseEntity<Void> alterarSenha(
            @PathVariable Long id,
            @Valid @RequestBody AlterarSenhaDTO dto
    ){
        donoRestauranteService.alterarSenha(id, dto);
        logger.info("PATCH -> /dono/{}/senha", id);
        return ResponseEntity.noContent().build();
    }


}
