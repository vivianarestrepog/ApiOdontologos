package com.dh.RESTREPO_VIVIANA_INTEGRADOR.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private UsuarioRepository usuarioRepository;

    @Autowired
    public DataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String hash_user = passwordEncoder.encode("123");
        Usuario usuario = new Usuario("Ernesto", "frailejon", "ernestoperez@hotmail.com", hash_user, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario);

        String hash_admin = passwordEncoder.encode("123");
        Usuario administrador = new Usuario("Viviana", "vivi", "vivianarestrepo@hotmail.com", hash_admin, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(administrador);
    }
}
