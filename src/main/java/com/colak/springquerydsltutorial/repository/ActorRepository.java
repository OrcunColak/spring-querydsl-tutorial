package com.colak.springquerydsltutorial.repository;

import com.colak.springquerydsltutorial.jpa.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

/**
 * TheQuerydslPredicateExecutor interface provides several overloaded methods that lets us allow executing Querydsl predicates.
 */
public interface ActorRepository extends JpaRepository<Actor, Integer>, QuerydslPredicateExecutor<Actor>, ActorRepositoryEx {

}
