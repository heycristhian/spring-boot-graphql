package br.com.work.fitness.controller;

import br.com.work.fitness.config.security.TokenService;
import br.com.work.fitness.controller.dto.TokenDTO;
import br.com.work.fitness.controller.form.LoginForm;
import br.com.work.fitness.model.User;
import br.com.work.fitness.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<TokenDTO> auth(@RequestBody LoginForm form) {
        UsernamePasswordAuthenticationToken login = form.converter();
        try {
            Authentication authentication = authManager.authenticate(login);
            String token = tokenService.generateToken(authentication);
            User user = userService.findByUsername(login.getName());
            return ResponseEntity.ok(new TokenDTO(token, "Bearer", user.getId()));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
