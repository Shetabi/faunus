package com.faunus.api.adapter.controller;

import com.faunus.api.core.owner.Owner;
import com.faunus.api.core.owner.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final OwnerRepository ownerRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        Optional<Owner> owner = ownerRepository.findByName(loginRequest.userName());

        return owner.map(o -> ResponseEntity.ok(new LoginResponse(o.getId())))
                .orElse(ResponseEntity.status(401).build());
    }

    public record LoginRequest(String userName) { }

    public record LoginResponse(Long ownerId) { }

}
