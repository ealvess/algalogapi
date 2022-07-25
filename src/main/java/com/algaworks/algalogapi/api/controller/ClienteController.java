package com.algaworks.algalogapi.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalogapi.domain.model.Cliente;
import com.algaworks.algalogapi.domain.repository.ClienteRepository;
import com.algaworks.algalogapi.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	
	private ClienteRepository clienteRepository;
	
	private CatalogoClienteService catalagoClienteService;
	
	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long codigo) {
		return clienteRepository.findById(codigo)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return catalagoClienteService.savar(cliente);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long codigo, @Valid @RequestBody Cliente cliente){
		if (!clienteRepository.existsById(codigo)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(codigo);
		cliente = catalagoClienteService.savar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> remover(@PathVariable Long codigo){
		if (!clienteRepository.existsById(codigo)) {
			return ResponseEntity.notFound().build();
		}
		
		catalagoClienteService.excluir(codigo);
		
		return ResponseEntity.noContent().build();
	}
}
