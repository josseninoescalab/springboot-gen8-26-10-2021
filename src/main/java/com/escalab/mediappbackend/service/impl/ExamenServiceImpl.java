package com.escalab.mediappbackend.service.impl;

import com.escalab.mediappbackend.exception.ModeloNotFoundException;
import com.escalab.mediappbackend.model.Examen;
import com.escalab.mediappbackend.repository.ExamenRepository;
import com.escalab.mediappbackend.service.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamenServiceImpl implements ExamenService {
	
	@Autowired
	private ExamenRepository examenRepository;
	
	@Override
	public Examen save(Examen obj) {
		return examenRepository.save(obj);
	}
	
	@Override
	public Examen update(Examen obj) {
		return examenRepository.save(obj);
	}
	
	@Override
	public List<Examen> findAll() {
		return examenRepository.findAll();
	}
	
	@Override
	public Examen findById(Integer id)  {
		Optional<Examen> op = examenRepository.findById(id);
		if (!op.isPresent()) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		return op.get();
	}
	
	@Override
	public boolean delete(Integer id) {
		Optional<Examen> op = examenRepository.findById(id);
		if (!op.isPresent()) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO" + id);
		}
		examenRepository.deleteById(id);
		return true;
	}
}
