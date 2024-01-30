package com.colak.springquerydsltutorial.repository;

import com.colak.springquerydsltutorial.jpa.Actor;
import com.colak.springquerydsltutorial.jpa.Film;
import com.colak.springquerydsltutorial.jpa.QActor;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ActorRepositoryTest {

    @Autowired
    private ActorRepository actorRepository;

    @Test
    void testFindAllSorted() {
        Actor actor = new Actor();
        actor.setName("actor1");

        Film film = new Film();
        film.setTitle("film1");
        film.getActors().add(actor);

        actor.getFilms().add(film);

        Actor savedActor = actorRepository.save(actor);
        assertNotNull(savedActor.getId());

        BooleanExpression booleanExpression = QActor.actor.id.goe(1);
        OrderSpecifier<String> orderSpecifier = QActor.actor.name.asc();
        Iterable<Actor> iterable = actorRepository.findAll(booleanExpression, orderSpecifier);

        List<Actor> list = StreamSupport
                .stream(iterable.spliterator(), false)
                .toList();

        assertEquals("actor1", list.getFirst().getName());

        List<Actor> allWithFilms = actorRepository.findAllWithFilms();
        assertEquals(1, allWithFilms.getFirst().getFilms().size());

    }
}
