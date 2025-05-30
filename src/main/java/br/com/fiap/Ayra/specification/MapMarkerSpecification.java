package br.com.fiap.Ayra.specification;

import br.com.fiap.Ayra.controller.MapMarkerController.MapMarkerFilter;
import br.com.fiap.Ayra.model.MapMarker;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;

public class MapMarkerSpecification {

    public static Specification<MapMarker> withFilters(MapMarkerFilter filter) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(filter.intensity()!=null && !filter.intensity().isBlank()){
                predicates.add(cb.equal(root.get("intensity"), filter.intensity()));
            }
            

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}