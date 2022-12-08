package ru.borisov.pastebox.controller;

import org.springframework.web.bind.annotation.*;
import ru.borisov.pastebox.api.request.PasteBoxRequest;

import java.util.Collection;
import java.util.Collections;

@RestController
public class PasteBoxController {

    @GetMapping("/{hash}")
    public String getByHash(@PathVariable(value = "hash") String hash) {
        return hash;
    }

    @GetMapping("/")
    public Collection<String> getPublicPasteList() {
        return Collections.emptyList();
    }

    @PostMapping("/")
    public String add(@RequestBody PasteBoxRequest request) {

        return request.getData();
    }
}
