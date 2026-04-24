package com.bookStore.bookStore.controller;

import com.bookStore.bookStore.dto.AuthResponse;
import com.bookStore.bookStore.dto.LoginRequest;
import com.bookStore.bookStore.dto.RegisterRequest;
import com.bookStore.bookStore.entity.Role;
import com.bookStore.bookStore.entity.User;
import com.bookStore.bookStore.repository.RoleRepository;
import com.bookStore.bookStore.repository.UserRepository;
import com.bookStore.bookStore.security.JwtService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthFormController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthFormController(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login/submit")
    public String login(@ModelAttribute LoginRequest request, Model model, HttpSession session) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            User user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new BadCredentialsException("User not found"));

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = jwtService.generateToken(userDetails);

            session.setAttribute("token", token);
            session.setAttribute("username", user.getUsername());
            session.setAttribute("userId", user.getId());

            return "redirect:/available_books";
        } catch (BadCredentialsException e) {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }

    @PostMapping("/register/submit")
    public String register(@ModelAttribute RegisterRequest request, Model model) {
        if (userRepository.existsByUsername(request.getUsername())) {
            model.addAttribute("error", "El nombre de usuario ya está en uso");
            return "register";
        }

        if (userRepository.existsByEmail(request.getEmail())) {
            model.addAttribute("error", "El correo electrónico ya está registrado");
            return "register";
        }

        Role userRole = roleRepository.findByName("USER")
                .orElseGet(() -> {
                    Role role = new Role("USER");
                    role.setDescription("Regular user");
                    return roleRepository.save(role);
                });

        User user = new User(request.getUsername(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setRole(userRole);

        userRepository.save(user);

        model.addAttribute("success", "¡Cuenta creada exitosamente! Ahora puedes iniciar sesión.");
        return "redirect:/login?registered";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout";
    }
}