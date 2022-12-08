package ru.borisov.pastebox;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.borisov.pastebox.api.response.PasteboxResponse;
import ru.borisov.pastebox.exception.NotFoundEntityException;
import ru.borisov.pastebox.model.PasteBoxEntity;
import ru.borisov.pastebox.repository.PasteboxRepository;
import ru.borisov.pastebox.service.PasteBoxService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PasteboxServiceTest {

    @Autowired
    private PasteBoxService pasteBoxService;

    @MockBean
    private PasteboxRepository pasteboxRepository;

    @Test
    public void notExistHash() {
        when(pasteboxRepository.getByHash(anyString())).thenThrow(NotFoundEntityException.class);
        assertThrows(NotFoundEntityException.class,
                () -> pasteBoxService.getByHash("lashgaiusgh"));
    }

    @Test
    public void getExistHash() {
        PasteBoxEntity entity = new PasteBoxEntity();
        entity.setHash("1");
        entity.setData("abcde");
        entity.setPublic(true);

        when(pasteboxRepository.getByHash("1")).thenReturn(entity);

        PasteboxResponse expected = new PasteboxResponse("abcde", true);
        PasteboxResponse actual = pasteBoxService.getByHash("1");

        assertEquals(expected,actual);
    }
}
