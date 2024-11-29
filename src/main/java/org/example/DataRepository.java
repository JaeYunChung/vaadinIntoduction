package org.example;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Repository
class DataRepository{
    private TreeMap<Integer, Person> personTreeMap = new TreeMap<>();
    private int id = 0;

    public Optional<Person> save(Person person)
    {
        person.setId(id);
        if(personTreeMap.values().contains(person))
            return Optional.empty();
        personTreeMap.put(id, person);
        id++;
        return Optional.of(person);
    }
    public Person find(Integer id)
    {
        return personTreeMap.get(id);
    }
    public List<Person> findAll()
    {
        return personTreeMap.values().stream().collect(Collectors.toList());
    }
}
