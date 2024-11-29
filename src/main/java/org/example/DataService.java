package org.example;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataService {
    private static DataRepository dataRepository;
    public DataService(DataRepository dataRepository)
    {
        this.dataRepository = dataRepository;
    }
    public static List<Person> getPeople()
    {
        return dataRepository.findAll();
    }
    public static Optional<Person> savePerson(Person person)
    {
        return dataRepository.save(person);
    }
}
