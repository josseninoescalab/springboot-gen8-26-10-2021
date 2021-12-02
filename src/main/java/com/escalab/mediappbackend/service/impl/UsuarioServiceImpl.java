package com.escalab.mediappbackend.service.impl;

import com.escalab.mediappbackend.model.Usuario;
import com.escalab.mediappbackend.repository.UsuarioRepository;
import com.escalab.mediappbackend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl  implements UsuarioService, UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public Usuario save(Usuario obj) {
		return usuarioRepository.save(obj);
	}

	@Override
	public Usuario update(Usuario obj) {
		return usuarioRepository.save(obj);
	}

	@Override
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario findById(Integer id) {
		Optional<Usuario> op = usuarioRepository.findById(id);
		return op.isPresent() ? op.get() : new Usuario(); 
	}

	@Override
	public boolean delete(Integer id) {
		usuarioRepository.deleteById(id);
		return true;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findOneByNombre(username);
		if(usuario == null) {
			throw new UsernameNotFoundException(String.format("Usuario no existe", username));
		}
		List<GrantedAuthority> roles = new ArrayList<>();
		usuario.getRoles().forEach(rol -> {
			roles.add(new SimpleGrantedAuthority(rol.getNombre()));
		});
		UserDetails ud = new User(usuario.getNombre(), usuario.getPassword(), roles);
		return ud;
	}
	
}

