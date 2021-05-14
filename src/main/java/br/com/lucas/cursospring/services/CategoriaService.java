package br.com.lucas.cursospring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.lucas.cursospring.domain.Categoria;
import br.com.lucas.cursospring.repository.CategoriaRepository;
import br.com.lucas.cursospring.services.exceptions.DataIntegrityException;
import br.com.lucas.cursospring.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Optional<Categoria> find(Integer id) {
		Optional<Categoria> categoria = categoriaRepository.findById(id);
		if (categoria.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado. | ID: " + id + " | Tipo: " + Categoria.class.getName());
		}
		
		return categoria;
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categoriaRepository.save(categoria);
	}

	public Categoria update(Categoria categoria) {	
		find(categoria.getId());
		return categoriaRepository.save(categoria);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			categoriaRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos.");
		}
	}

	public List<Categoria> findAll() {
		return categoriaRepository.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);
	}
	
}
