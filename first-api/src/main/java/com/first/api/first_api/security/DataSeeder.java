package com.first.api.first_api.security;

import com.first.api.first_api.models.Rol;
import com.first.api.first_api.models.Usuario;
import com.first.api.first_api.repositories.RolRepository;
import com.first.api.first_api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Si no hay roles en la DB, creamos el inicial
        if (rolRepository.count() == 0) {
            Rol rolAdmin = new Rol();
            rolAdmin.setNombre("ROLE_ADMIN"); // Spring exige el prefijo ROLE_
            rolAdmin = rolRepository.save(rolAdmin);

            Usuario admin = new Usuario();
            admin.setNombre("Admin Principal");
            admin.setEmail("admin@mail.com");
            admin.setPassword(passwordEncoder.encode("admin123")); // Contraseña encriptada
            admin.setRoles(Set.of(rolAdmin));
            usuarioRepository.save(admin);
            
            System.out.println("--- USUARIO ADMIN CREADO: admin@mail.com / admin123 ---");
        }
    }
}