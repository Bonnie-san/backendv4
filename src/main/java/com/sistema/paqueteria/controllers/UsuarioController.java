package com.sistema.paqueteria.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistema.paqueteria.modelo.services.UsuarioService;
import com.sistema.paqueteria.models.entity.Rol;
import com.sistema.paqueteria.models.entity.Usuario;
import com.sistema.paqueteria.models.entity.UsuarioRol;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/usuario")
	public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
		usuario.setPerfil("default.png");
		Set<UsuarioRol> usuarioRoles = new HashSet<>();
		
		Rol rol=new Rol();
		rol.setRolId(1L);
		rol.setNombre("NORMAL");
		
	    UsuarioRol usuarioRol = new UsuarioRol();
	    usuarioRol.setUsuario(usuario);
	    usuarioRol.setRol(rol);
		
	    usuarioRoles.add(usuarioRol);
		return usuarioService.guardarusuario(usuario, usuarioRoles);
	}
	
	@GetMapping("/usuario/{username}")
	public Usuario obtenerUsuario(@PathVariable("username") String username) {
		return usuarioService.obtenerUsuario(username);
	}
	
	@DeleteMapping("/usuario/{usuarioId}")
	public void eliminarUsuarios(@PathVariable("usuarioId") Long usernameId) {
		usuarioService.eliminarUsuario(usernameId);
	}
	
}
