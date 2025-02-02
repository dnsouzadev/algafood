package com.dnsouzadev.algafood.api.controller;

import com.dnsouzadev.algafood.api.assembler.RestauranteModelAssembler;
import com.dnsouzadev.algafood.api.model.RestauranteModel;
import com.dnsouzadev.algafood.api.model.input.RestauranteInput;
import com.dnsouzadev.algafood.core.validation.ValidacaoException;
import com.dnsouzadev.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.dnsouzadev.algafood.domain.exception.NegocioException;
import com.dnsouzadev.algafood.domain.model.Cozinha;
import com.dnsouzadev.algafood.domain.model.Restaurante;
import com.dnsouzadev.algafood.domain.repository.RestauranteRepository;
import com.dnsouzadev.algafood.domain.service.RestauranteService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
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
    private SmartValidator validator;

    @Autowired
    private RestauranteModelAssembler restauranteModelAssembler;

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
            Restaurante restauranteDomain = toDomainObject(restaurante);
            return restauranteModelAssembler.toModel(restauranteService.salvar(restauranteDomain));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteModel atualizar(@PathVariable Long restauranteId,
                                 @RequestBody RestauranteInput restaurante) {
        try {
            Restaurante restauranteDomain = toDomainObject(restaurante);
            Restaurante restauranteAtual = restauranteService.buscarOuFalhar(restauranteId);

            BeanUtils.copyProperties(restauranteDomain, restauranteAtual,
                    "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

            return restauranteModelAssembler.toModel(restauranteService.salvar(restauranteAtual));
        } catch (CozinhaNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }


    private void validate(Restaurante restaurante, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
        validator.validate(restaurante, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidacaoException(bindingResult);
        }
    }

    private Restaurante toDomainObject(RestauranteInput restauranteInput) {
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteInput.getNome());
        restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());

        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteInput.getCozinha().getId());

        restaurante.setCozinha(cozinha);

        return restaurante;
    }
}
