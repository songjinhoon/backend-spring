package com.persoanltoy.backend.domains.member.controller;

import com.persoanltoy.backend.domains.member.dto.response.MemberDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import java.util.Arrays;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class MemberResource extends EntityModel<MemberDto> {

    public MemberResource(MemberDto member, Link... links) {
        super(member, Arrays.asList(links));
        add(linkTo(MemberCreateController.class).slash(member.getId()).withSelfRel());
    }

}
