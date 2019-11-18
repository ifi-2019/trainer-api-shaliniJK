package com.ifi.trainer_api.bo;

import org.junit.jupiter.api.Test;

import javax.persistence.*;

import static org.junit.jupiter.api.Assertions.*;

public class PokemonTest {
    @Test
    void pokemon_shouldBeAnEmbeddable(){
        assertNotNull(Pokemon.class.getAnnotation(Embeddable.class));
    }

}
