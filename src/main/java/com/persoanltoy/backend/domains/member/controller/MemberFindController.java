package com.persoanltoy.backend.domains.member.controller;

import com.persoanltoy.backend.domains.member.dto.request.MemberQueryDto;
import com.persoanltoy.backend.domains.member.dto.response.MemberDto;
import com.persoanltoy.backend.domains.member.service.MemberFindService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequiredArgsConstructor
@RequestMapping("/member")
@RestController
public class MemberFindController {

    private final MemberFindService memberFindService;

    @GetMapping("/{id}")
    public ResponseEntity<?> find(@PathVariable UUID id) {
        MemberDto find = MemberDto.of(memberFindService.find(id));

        WebMvcLinkBuilder webMvcLinkBuilder = linkTo(MemberCreateController.class).slash(find.getId());
        MemberResource memberResource = new MemberResource(find);
        memberResource.add(Link.of("/docs/index.html#resources-member-find", "profile"));
        memberResource.add(linkTo(MemberCreateController.class).withRel("query-events"));
        memberResource.add(webMvcLinkBuilder.withRel("update-events"));

        return ResponseEntity.ok().body(memberResource);
    }

    @GetMapping
    public ResponseEntity<?> find(@PageableDefault(sort = "rgsDt", direction = Sort.Direction.DESC) Pageable pageable, MemberQueryDto memberQueryDto) {
        return ResponseEntity.ok().body(memberFindService.query(pageable, memberQueryDto));
    }

}
