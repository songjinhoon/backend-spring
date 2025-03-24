package com.personaltoy.backend.domains.member.controller;

import com.personaltoy.backend.domains.member.dto.request.MemberUpdateDto;
import com.personaltoy.backend.domains.member.dto.response.MemberDto;
import com.personaltoy.backend.domains.member.service.MemberUpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberUpdateController {

    private final MemberUpdateService memberUpdateService;

    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody @Valid MemberUpdateDto memberUpdateDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }
        MemberDto of = MemberDto.of(memberUpdateService.update(id, memberUpdateDto));
        MemberResource memberResource = new MemberResource(of);
        memberResource.add(Link.of("/docs/index.html#resources-member-update", "profile"));
        return ResponseEntity.ok().body(memberResource);
    }

}
