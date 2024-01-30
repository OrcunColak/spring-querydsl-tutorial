package com.colak.springquerydsltutorial.repository;

import com.colak.springquerydsltutorial.jpa.Actor;
import com.colak.springquerydsltutorial.jpa.QActor;
import com.colak.springquerydsltutorial.jpa.QCategory;
import com.colak.springquerydsltutorial.jpa.QFilm;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PureActorRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public PureActorRepository(EntityManager entityManager) {
        this.jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    public List<Actor> findAll() {
        QActor actor = QActor.actor;
        return jpaQueryFactory.select(actor).from(actor).fetch();
    }

    public Actor findById(Integer id) {
        QActor actor = QActor.actor;
        return jpaQueryFactory.select(actor).from(actor)
                .where(actor.id.eq(id)).fetchOne();
    }

    public List<Actor> findAllByCategory(String category) {
        QActor actor = QActor.actor;
        QFilm film = QFilm.film;
        QCategory qcategory = QCategory.category;
        return jpaQueryFactory.select(actor).from(actor)
                .join(actor.films,film)
                .join(film.category, qcategory).on(qcategory.name.eq(category))
                .fetch();

    }

    public List<Actor> findAllByFilmReleaseYear(Integer year) {
        QActor actor = QActor.actor;
        QFilm film = QFilm.film;
        return jpaQueryFactory.select(actor).from(actor)
                .innerJoin(actor.films, film)
                .where(film.releaseYear.eq(year)).fetch();
    }
}
