package ru.borisov.pastebox.service;

import ru.borisov.pastebox.api.request.PasteboxRequest;
import ru.borisov.pastebox.api.response.PasteboxResponse;
import ru.borisov.pastebox.api.response.PasteboxUrlResponse;

import java.util.List;

public interface PasteBoxService {

    PasteboxResponse getByHash(String hash);

    List<PasteboxResponse> getFirstPublicPasteboxes();

    PasteboxUrlResponse create(PasteboxRequest request);
}
