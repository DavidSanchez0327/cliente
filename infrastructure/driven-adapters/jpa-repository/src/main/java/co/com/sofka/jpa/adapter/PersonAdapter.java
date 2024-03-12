package co.com.sofka.jpa.adapter;

import co.com.sofka.jpa.entity.PersonEntity;
import co.com.sofka.jpa.gateway.PersonAdapterRepository;
import co.com.sofka.jpa.mapper.Mapper;
import co.com.sofka.model.Person;
import co.com.sofka.model.exception.ErrorEnum;
import co.com.sofka.model.exception.TechnicalException;
import co.com.sofka.model.gateway.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;


@RequiredArgsConstructor
@Service
public class PersonAdapter implements PersonRepository, Mapper {

    private final PersonAdapterRepository personAdapterRepository;


    @Override
    public Mono<Person> findPerson(String id) {
        return Mono.justOrEmpty(personAdapterRepository.findById(id))
                .map(this::createPerson);
    }

    @Override
    public Mono<Void> deletePerson(String id) {
        personAdapterRepository.deleteById(id);
        return Mono.empty();
    }

    @Override
    public Mono<Person> savePerson(Person person) {
        return Mono.justOrEmpty(personAdapterRepository.findById(person.identityId()).orElse(PersonEntity.builder().build()))
                .flatMap(personEntity -> Objects.nonNull(personEntity.getId())
                        ? Mono.just(createPerson(personEntity))
                        : createPerson(person));
    }


    private Mono<Person> createPerson(Person person) {
        return Mono.just(personAdapterRepository.save(createPersonEntity(person)))
                .switchIfEmpty(Mono.error(new TechnicalException(ErrorEnum.ERROR_CREATE_PERSON.getMessage())))
                .map(this::createPerson);
    }

    @Override
    public Mono<Person> updatePerson(Person person) {
        return Mono.justOrEmpty(personAdapterRepository.findById(person.identityId()))
                .switchIfEmpty(Mono.error(new TechnicalException(ErrorEnum.DONT_EXIST_PERSON.getMessage())))
                .map(personEntity -> personAdapterRepository.save(createPersonEntity(person)))
                .map(this::createPerson);
    }

}
