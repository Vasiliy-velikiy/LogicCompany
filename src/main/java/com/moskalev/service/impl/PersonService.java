package com.moskalev.service.impl;

import com.moskalev.domain.dto.impl.personDto.PersonDto;
import com.moskalev.domain.dto.impl.personDto.PersonToCreateDto;
import com.moskalev.domain.entity.Person;
import com.moskalev.domain.mapper.PersonMapper;
import com.moskalev.domain.mapper.mupstruct.MergePersonMapper;
import com.moskalev.exception.CustomException;
import com.moskalev.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

/**
 * @author Vasiliy Moskalev
 * @version 1.1
 * @since 04.03.22
 * Class service for person which provides interaction with personRepository
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PersonService {
    private final PersonRepository personRepository;

    /**
     * filed describes object for convert
     */
    private final MergePersonMapper objectMapper;

    /**
     * filed describes object for encoding
     */
    private final PasswordEncryptionService passwordEncryptionService;

    /**
     * filed describes final variable for encoding-this is salt for byte shift
     */
    private static final String SALT_FOR_ENCRYPTING_PASSWORD = "mySalt";

    private final PersonMapper personMapper;

    /**
     * @return page of all Person in table person. We used initialize() for fetch List
     */
//    public Page<PersonDto> readAll() {
//        List<PersonDto> listPersons = personMapper.convertListToDto(personRepository.findAll());
//        Pageable firstPageWithTwoElements = PageRequest.of(0, listPersons.size());
//        return new PageImpl<>(listPersons, firstPageWithTwoElements, listPersons.size());
//    }

    public List<PersonDto> readAll() {
        List<PersonDto> listPersons = personMapper.convertListToDto(personRepository.findAll());
        return listPersons;
    }


    /**
     * @param email certain email that is unique
     * @return certain Person by email
     * @throws CustomException if  User not found
     *                         initialize()-method for fetch List
     */
    public PersonDto read(String email) {
        Optional<Person> personOptional = personRepository.findByEmail(email);
        if (personOptional.isPresent()) {
            return personMapper.toDto(personOptional.get());
        } else {
            throw new CustomException(String.format("User with email %s not found", email));
        }
    }

    /**
     * @param newPersonToCreateDto -class of Person.
     * @throws CustomException if User already exists
     *                         else-create new User. We encrypt passing password and set new password to Database
     */
    public void create(PersonToCreateDto newPersonToCreateDto) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Optional<Person> templatePerson = personRepository.findByEmail(newPersonToCreateDto.getEmail());
        if (!templatePerson.isPresent()) {
            newPersonToCreateDto.setPassword(passwordEncryptionService.hashToHex(newPersonToCreateDto.getPassword(), Optional.of(SALT_FOR_ENCRYPTING_PASSWORD)));
            Person newPerson = personMapper.fromCreateDto(newPersonToCreateDto);
            personRepository.save(newPerson);
        } else {
            throw new CustomException(String.format("User with email %s already exists", newPersonToCreateDto.getEmail()));
        }
    }

    /**
     * @param email -certain email
     * @throws CustomException if  Person not found
     */
    public void delete(String email) {
        Optional<Person> optionalPerson = personRepository.findByEmail(email);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            personRepository.delete(person);
        } else {
            throw new CustomException("Person not found");
        }
    }

    /**
     * @param id        -certain email
     * @param newPerson -new Person that we want to put in database
     * @throws CustomException if Person not found
     */
    public void update(String email, PersonDto newPerson) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Optional<Person> optionalPerson = personRepository.findByEmail(email);
        if (optionalPerson.isPresent()) {
            Person target = optionalPerson.get();
            Person source = personMapper.fromDto(newPerson);
            if (source.getPassword() != null) {
                source.setPassword(passwordEncryptionService.hashToHex(source.getPassword(), Optional.of(SALT_FOR_ENCRYPTING_PASSWORD)));
            }
            personRepository.save(objectMapper.merge(target, source));
        } else {
            throw new CustomException("Person not found");
        }
    }
}
