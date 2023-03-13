package com.persoanltoy.backend.domains.event.controller;

import com.persoanltoy.backend.domains.event.api.EventEntry;
import com.persoanltoy.backend.domains.event.api.EventStore;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/event")
@RestController
public class EventController {

    private final EventStore eventStore;

    @GetMapping
    public List<EventEntry> list(@RequestParam("offset") Long offset, @RequestParam("limit") Long limit) {
        return eventStore.get(offset, limit);
    }

}
