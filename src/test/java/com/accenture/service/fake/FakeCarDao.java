package com.accenture.service.fake;

import com.accenture.model.Car;
import com.accenture.repository.CarDao;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class FakeCarDao implements CarDao {

    public final Map<Integer,Car> store = new HashMap<>();


    @Override
    public <S extends Car> S save(S entity) {
        store.put(entity.getId(),entity);
        return entity;
    }

    @Override
    public Optional<Car> findById(Integer integer) {
        return Optional.ofNullable(store.get(integer));
    }

    @Override
    public void deleteById(Integer integer) {
        store.remove(integer);
    }



    @Override
    public void flush() {}

    @Override
    public <S extends Car> S saveAndFlush(S entity) {return null;}

    @Override
    public <S extends Car> List<S> saveAllAndFlush(Iterable<S> entities) {return List.of();}

    @Override
    public void deleteAllInBatch(Iterable<Car> entities) {}

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {}

    @Override
    public void deleteAllInBatch() {}

    @Override
    public Car getOne(Integer integer) {return null;}

    @Override
    public Car getById(Integer integer) {return null;}

    @Override
    public Car getReferenceById(Integer integer) {return null;}

    @Override
    public <S extends Car> Optional<S> findOne(Example<S> example) {return Optional.empty();}

    @Override
    public <S extends Car> List<S> findAll(Example<S> example) {return List.of();}

    @Override
    public <S extends Car> List<S> findAll(Example<S> example, Sort sort) {return List.of();}

    @Override
    public <S extends Car> Page<S> findAll(Example<S> example, Pageable pageable) {return null;}

    @Override
    public <S extends Car> long count(Example<S> example) {return 0;}

    @Override
    public <S extends Car> boolean exists(Example<S> example) {return false;}

    @Override
    public <S extends Car, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {return null;}

    @Override
    public <S extends Car> List<S> saveAll(Iterable<S> entities) {return List.of();}

    @Override
    public boolean existsById(Integer integer) {return false;}

    @Override
    public List<Car> findAll() {return List.of();}

    @Override
    public List<Car> findAllById(Iterable<Integer> integers) {return List.of();}

    @Override
    public long count() {return 0;}

    @Override
    public void delete(Car entity) {}

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {}

    @Override
    public void deleteAll(Iterable<? extends Car> entities) {}

    @Override
    public void deleteAll() {}

    @Override
    public List<Car> findAll(Sort sort) {return List.of();}

    @Override
    public Page<Car> findAll(Pageable pageable) {return null;}
}
