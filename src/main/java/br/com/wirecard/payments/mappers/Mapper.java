package br.com.wirecard.payments.mappers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface Mapper<D, T> {

    T convertFromDtoToObject(D dto);

    D convertFromObjectToDto(T object);

    default List<D> convertFromObjectsToDtos(List<T> objects) {
        return Optional.ofNullable(objects).map(o ->
                o.stream().map(this::convertFromObjectToDto)
                    .collect(Collectors.toList())).orElse(Collections.emptyList());
    }
}
