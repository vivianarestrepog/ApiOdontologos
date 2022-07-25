package com.dh.RESTREPO_VIVIANA_INTEGRADOR.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UsuarioServiceImpl implements UserDetailsService {
    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBuscado=usuarioRepository.findByUserName(username);
        if (usuarioBuscado.isPresent()){
            return usuarioBuscado.get();
        }
        else {
            throw new UsernameNotFoundException("Error: El username ingresado no existe en la base de datos.");
        }
    }
}
