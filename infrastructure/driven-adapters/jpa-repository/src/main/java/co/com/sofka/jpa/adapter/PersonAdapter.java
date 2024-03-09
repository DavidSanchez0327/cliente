package co.com.sofka.jpa.adapter;

import co.com.sofka.jpa.entity.PersonEntity;
import co.com.sofka.jpa.gateway.PersonRepositoryAdapter;
import co.com.sofka.jpa.mapper.Mapper;
import co.com.sofka.model.Person;
import co.com.sofka.model.exception.TechnicalException;
import co.com.sofka.model.gateway.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;


@Component
public class PersonAdapter implements PersonRepository, Mapper {

    private final PersonRepositoryAdapter personRepositoryAdapter;
    private static final String ERROR_VALIDATE_PERSON = "Ya existe esta persona";
    private static final String DONT_EXIST_PERSON = "No existe la persona";
    private static final String ERROR_CREATE_PERSON = "Fallo la creacion en la tabla persona";
    private static final String ERROR_UPDATE_PERSON = "Fallo la actualizacion en la tabla persona";

    @Autowired
    public PersonAdapter(PersonRepositoryAdapter personRepositoryAdapter) {
        this.personRepositoryAdapter = personRepositoryAdapter;
    }

    @Override
    public Mono<Person> findPerson(String id) {
        return personRepositoryAdapter.findById(id)
                .flatMap(personEntity -> Objects.isNull(personEntity) || Objects.isNull(personEntity.getIdentificacion())
                        ? Mono.error(new TechnicalException(DONT_EXIST_PERSON))
                        : Mono.just(personEntity))
                .map(this::mapToPerson);
    }

    @Override
    public Mono<Void> deletePerson(String id) {
        return personRepositoryAdapter.deleteById(id);
    }

    @Override
    public Mono<Person> savePerson(Person person) {
        return personRepositoryAdapter.findById(person.getIdentificacion())
                .flatMap(personEntity -> Objects.nonNull(personEntity)
                        ? Mono.error(new TechnicalException(ERROR_VALIDATE_PERSON))
                        : createPerson(person, personEntity));
    }

    private Mono<Person> createPerson(Person person, PersonEntity personEntity) {
        return personRepositoryAdapter.save(mapToPersonEntity(person))
                .flatMap(personEntity1 -> Objects.isNull(personEntity1) || Objects.isNull(personEntity1.getIdentificacion())
                        ? Mono.error(new TechnicalException(ERROR_CREATE_PERSON))
                        : Mono.just(personEntity))
                .map(this::mapToPerson);
    }

    @Override
    public Mono<Person> updatePerson(Person person) {
        return personRepositoryAdapter.findById(person.getIdentificacion())
                .flatMap(personEntity -> Objects.isNull(personEntity) || Objects.isNull(personEntity.getIdentificacion())
                        ? Mono.error(new TechnicalException(DONT_EXIST_PERSON))
                        : validateUpdatePerson(person, personEntity));
    }

    private Mono<Person> validateUpdatePerson(Person person, PersonEntity personEntity) {
        return personRepositoryAdapter.save(mapToPersonEntity(person))
                .flatMap(personEntity1 -> Objects.isNull(personEntity1) || Objects.isNull(personEntity1.getIdentificacion())
                        ? Mono.error(new TechnicalException(ERROR_UPDATE_PERSON))
                        : Mono.just(personEntity))
                .map(this::mapToPerson);
    }
}
