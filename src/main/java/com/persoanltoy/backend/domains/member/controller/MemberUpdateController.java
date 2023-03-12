package com.persoanltoy.backend.domains.member.controller;

import com.persoanltoy.backend.domains.member.domain.entity.Member;
import com.persoanltoy.backend.domains.member.dto.request.MemberUpdateDto;
import com.persoanltoy.backend.domains.member.dto.response.MemberDto;
import com.persoanltoy.backend.domains.member.service.MemberUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberUpdateController {

    private final MemberUpdateService memberUpdateService;

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable UUID id, @RequestBody @Valid MemberUpdateDto memberUpdateDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        Member update = memberUpdateService.update(id, memberUpdateDto);
        MemberResource memberResource = new MemberResource(MemberDto.of(update));
        memberResource.add(Link.of("/docs/index.html#resources-events-update", "profile"));
        return ResponseEntity.ok().body(memberResource);
    }

}
