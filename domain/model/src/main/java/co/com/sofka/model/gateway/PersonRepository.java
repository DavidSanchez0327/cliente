package co.com.sofka.model.gateway;

import co.com.sofka.model.Person;
import reactor.core.publisher.Mono;

public interface PersonRepository {

    Mono<Person> findPerson(String id);
    Mono<Void> deletePerson(String id);
    Mono<Person> savePerson(Person person);
    Mono<Person> updatePerson(Person person);
}
