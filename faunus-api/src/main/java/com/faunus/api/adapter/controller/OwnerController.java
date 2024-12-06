package com.faunus.api.adapter.controller;

import com.faunus.api.core.owner.Owner;
import com.faunus.api.core.owner.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerRepository ownerRepository;

    @GetMapping("/{name}")
    public ResponseEntity<OwnerInfo> getOwnerInfo(@PathVariable String name) {
        Optional<Owner> optionalOwner = ownerRepository.findByName(name);

        return optionalOwner
                .map(owner -> new OwnerInfo(owner.getId(), owner.getName()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    public record OwnerInfo(Long id, String name){ }
}
