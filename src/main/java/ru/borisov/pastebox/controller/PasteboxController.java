package ru.borisov.pastebox.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.borisov.pastebox.api.request.PasteboxRequest;
import ru.borisov.pastebox.api.response.PasteboxResponse;
import ru.borisov.pastebox.api.response.PasteboxUrlResponse;
import ru.borisov.pastebox.service.PasteBoxService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PasteboxController {

    private final PasteBoxService pasteBoxService;

    @GetMapping("/{hash}")
    public PasteboxResponse getByHash(@PathVariable(value = "hash") String hash) {
        return pasteBoxService.getByHash(hash);
    }

    @GetMapping("/")
    public List<PasteboxResponse> getPublicPasteList() {
        return pasteBoxService.getFirstPublicPasteboxes();
    }

    @PostMapping("/")
    public PasteboxUrlResponse add(@RequestBody PasteboxRequest request) {
        return pasteBoxService.create(request);
    }
}
