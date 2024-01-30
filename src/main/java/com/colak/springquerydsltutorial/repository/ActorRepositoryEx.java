package com.colak.springquerydsltutorial.repository;

import com.colak.springquerydsltutorial.jpa.Actor;

import java.util.List;

public interface ActorRepositoryEx {

    List<Actor> findAllWithFilms();
}
