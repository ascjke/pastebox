package ru.borisov.pastebox.repository;

import ru.borisov.pastebox.model.PasteBoxEntity;

import java.util.List;

public interface PasteboxRepository {

    PasteBoxEntity getByHash(String hash);

    List<PasteBoxEntity> getListOfPublicAndAlive(int amount);

    void add(PasteBoxEntity pasteBoxEntity);
}
