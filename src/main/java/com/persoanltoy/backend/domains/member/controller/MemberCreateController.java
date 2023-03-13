package com.persoanltoy.backend.domains.member.controller;

import com.persoanltoy.backend.domains.member.dto.request.SignUpDto;
import com.persoanltoy.backend.domains.member.dto.response.MemberDto;
import com.persoanltoy.backend.domains.member.service.MemberCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberCreateController {

    private final MemberCreateService memberCreateService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUpDto signUpDto, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        MemberDto memberDto = MemberDto.of(memberCreateService.save(signUpDto));

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(MemberCreateController.class).slash(memberDto.getId());
        MemberResource memberResource = new MemberResource(memberDto);
        memberResource.add(Link.of("/docs/index.html#resources-sign-up", "profile"));
        memberResource.add(linkTo(MemberCreateController.class).withRel("query"));
        memberResource.add(webMvcLinkBuilder.withRel("update"));

        return ResponseEntity.created(webMvcLinkBuilder.toUri()).body(memberResource);
    }

}
