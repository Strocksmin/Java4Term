package com.example.lab14;

import com.example.lab14.model.Card;
import com.example.lab14.repository.CardRepository;
import com.example.lab14.service.CardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class CardServiceImplTest {
    @Mock
    private CardRepository cardRepository;
    @Captor
    ArgumentCaptor<Card> captor;

    @Test
    void getCards(){
        cardRepository = mock(CardRepository.class);
        Card card = new Card();
        card.setCode(252);
        Card card2 = new Card();
        card2.setCode(353);
        Mockito.when(cardRepository.findAll()).thenReturn(List.of(card, card2));
        CardService cardService = new CardService(cardRepository);
        Assertions.assertEquals(2, cardService.getCards().size());
        Assertions.assertEquals(252, cardService.getCards().get(0).getCode());
    }

    @Test
    void addNewCard(){
        Card card = new Card();
        card.setCode(151);
        CardService cardService = new CardService(cardRepository);
        cardService.addCard(card);
        Mockito.verify(cardRepository).save(captor.capture());
        Card captured = captor.getValue();
        Assertions.assertEquals(151, captured.getCode());
    }
}