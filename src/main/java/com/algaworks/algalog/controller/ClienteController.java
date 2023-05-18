package com.algaworks.algalog.controller;

import java.util.List;

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

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

// 4
@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	// 1
//	@PersistenceContext
//	private EntityManager em;
	
	// 2
//	@Autowired
	private ClienteRepository clienteRepository;
	
	// 3
//	public ClienteController(ClienteRepository clienteRepository) {
//		super();
//		this.clienteRepository = clienteRepository;
//	}

	@GetMapping
	public List<Cliente> findAll() {
//		return em.createQuery("from Cliente", Cliente.class)
//				.getResultList();
		
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
//		Optional<Cliente> result = clienteRepository.findById(clienteId);
//		
//		if (result.isPresent()) {
//			return ResponseEntity.ok(result.get());
//		} else {
//			return ResponseEntity.notFound().build();
//		}
		
		return clienteRepository.findById(clienteId)
//			.map(cliente -> ResponseEntity.ok(cliente))
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adcionar(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		
		return ResponseEntity.ok(clienteRepository.save(cliente));
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteRepository.deleteById(clienteId);
		
		return ResponseEntity.noContent().build();
	}
	
	
}
