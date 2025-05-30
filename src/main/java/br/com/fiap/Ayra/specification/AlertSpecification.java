package br.com.fiap.Ayra.specification;

import br.com.fiap.Ayra.controller.AlertController.AlertFilter;
import br.com.fiap.Ayra.model.Alert;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.ArrayList;
import java.util.List;

public class AlertSpecification {

    public static Specification<Alert> withFilters(AlertFilter filter) {
        return (Root<Alert> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter != null) {
                if (filter.intensity() != null && !filter.intensity().isBlank()) {
                    predicates.add(cb.equal(root.get("intensity"), filter.intensity()));
                }

                if (filter.latitude() != null && filter.longitude() != null) {
                    Predicate latPredicate = cb.between(
                            root.get("coordinates").get("latitude"),
                            filter.latitude() - 0.01,
                            filter.latitude() + 0.01);

                    Predicate lonPredicate = cb.between(
                            root.get("coordinates").get("longitude"),
                            filter.longitude() - 0.01,
                            filter.longitude() + 0.01);

                    predicates.add(cb.and(latPredicate, lonPredicate));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}