package ru.borisov.pastebox.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import ru.borisov.pastebox.api.request.PasteboxRequest;
import ru.borisov.pastebox.api.request.PublicStatus;
import ru.borisov.pastebox.api.response.PasteboxResponse;
import ru.borisov.pastebox.api.response.PasteboxUrlResponse;
import ru.borisov.pastebox.model.PasteBoxEntity;
import ru.borisov.pastebox.repository.PasteboxRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "app")
public class PasteboxServiceImpl implements PasteBoxService {

    private final PasteboxRepository repository;
    private AtomicInteger idGenerator = new AtomicInteger(0);
    private String host = "http://pastebox.sendel.ru";
    private int publicListSize = 10;

    @Override
    public PasteboxResponse getByHash(String hash) {

        PasteBoxEntity pasteBoxEntity = repository.getByHash(hash);
        return new PasteboxResponse(pasteBoxEntity.getData(), pasteBoxEntity.isPublic());
    }

    @Override
    public List<PasteboxResponse> getFirstPublicPasteboxes() {

        List<PasteBoxEntity> list = repository.getListOfPublicAndAlive(publicListSize);
        return list.stream()
                .map(pasteBoxEntity ->
                        new PasteboxResponse(pasteBoxEntity.getData(), pasteBoxEntity.isPublic())
                )
                .collect(Collectors.toList());
    }

    @Override
    public PasteboxUrlResponse create(PasteboxRequest request) {

        int hash = generateId();
        PasteBoxEntity pasteBoxEntity = new PasteBoxEntity();
        pasteBoxEntity.setId(hash);
        pasteBoxEntity.setData(request.getData());
        pasteBoxEntity.setHash(Integer.toHexString(hash));
        pasteBoxEntity.setPublic(request.getPublicStatus() == PublicStatus.PUBLIC);
        pasteBoxEntity.setLifeTime(LocalDateTime.now().plusSeconds(request.getExpirationTimeSeconds()));
        repository.add(pasteBoxEntity);

        return new PasteboxUrlResponse(host + "/" + pasteBoxEntity.getHash());
    }

    private int generateId() {
        return idGenerator.getAndIncrement();
    }
}
