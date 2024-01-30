package com.colak.springquerydsltutorial.repository.impl;

import com.colak.springquerydsltutorial.jpa.Actor;
import com.colak.springquerydsltutorial.jpa.QActor;
import com.colak.springquerydsltutorial.jpa.QFilm;
import com.colak.springquerydsltutorial.repository.ActorRepositoryEx;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ActorRepositoryExImpl implements ActorRepositoryEx {

    private final JPAQueryFactory jpaQueryFactory;

    public ActorRepositoryExImpl(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Actor> findAllWithFilms() {
        QActor actor = QActor.actor;
        QFilm film = QFilm.film;
        return jpaQueryFactory.select(actor).from(actor)
                .innerJoin(actor.films, film)
                .fetchJoin()
                .fetch();
    }
}
