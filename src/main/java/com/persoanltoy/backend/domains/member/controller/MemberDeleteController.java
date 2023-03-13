package com.persoanltoy.backend.domains.member.controller;

import com.persoanltoy.backend.domains.member.service.MemberDeleteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberDeleteController {

    private final MemberDeleteService memberDeleteService;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        memberDeleteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete/{ids}")
    public ResponseEntity<?> delete(@PathVariable List<String> ids) {
        memberDeleteService.delete(ids);
        return ResponseEntity.noContent().build();
    }

}
