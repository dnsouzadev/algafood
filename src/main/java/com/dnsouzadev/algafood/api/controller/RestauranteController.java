package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.api.assembler.RestauranteInputDisassemble;
import com.dnsouzadev.algafood.api.assembler.RestauranteModelAssembler;
import com.dnsouzadev.algafood.api.model.RestauranteModel;
import com.dnsouzadev.algafood.api.model.input.RestauranteInput;
import com.dnsouzadev.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.dnsouzadev.algafood.domain.exception.NegocioException;
import com.dnsouzadev.algafood.domain.model.Restaurante;
import com.dnsouzadev.algafood.domain.repository.RestauranteRepository;
import com.dnsouzadev.algafood.domain.service.RestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private RestauranteService restauranteService;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

    @Autowired
    private RestauranteInputDisassemble restauranteInputDisassemble;

    @GetMapping
    public List<RestauranteModel> listar() {
        return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
    }

    @GetMapping("/{restauranteId}")
    public RestauranteModel buscar(@PathVariable Long restauranteId) {
        Restaurante restaurante =  restauranteService.buscarOuFalhar(restauranteId);
        return restauranteModelAssembler.toModel(restaurante);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restaurante) {
        try {
            Restaurante restauranteDomain = restauranteInputDisassemble.toDomainObject(restaurante);
            return restauranteModelAssembler.toModel(restauranteService.salvar(restauranteDomain));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable Long restauranteId,
                                 @RequestBody RestauranteInput restaurante) {
        try {
            Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);

            restauranteInputDisassemble.copyToDomainObject(restaurante, restauranteAtual);

            return restauranteModelAssembler.toModel(restauranteService.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
