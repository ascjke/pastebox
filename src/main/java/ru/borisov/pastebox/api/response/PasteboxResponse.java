package ru.borisov.pastebox.api.response;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.borisov.pastebox.api.request.PublicStatus;

@Data
@RequiredArgsConstructor
public class PasteboxResponse {

    private final String data;
    private final boolean isPublic;
}
