package br.com.fiap.Ayra.specification;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import br.com.fiap.Ayra.controller.CoordinatesController.CoordinatesFilter;
import br.com.fiap.Ayra.model.Coordinates;
import jakarta.persistence.criteria.Predicate;
public class CoordinatesSpecification {

    public static Specification<Coordinates> withFilters(CoordinatesFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.latitude() != null) {
                // Filtra por proximidade (ex: ±0.01 graus)
                predicates.add(cb.between(root.get("latitude"), filter.latitude() - 0.01, filter.latitude() + 0.01));
            }

            if (filter.longitude() != null) {
                predicates.add(cb.between(root.get("longitude"), filter.longitude() - 0.01, filter.longitude() + 0.01));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}