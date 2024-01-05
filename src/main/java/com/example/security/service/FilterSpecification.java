package com.example.security.service;

import com.example.security.component.ConvertDateTime;
import com.example.security.dto.request.FilterPaging;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class FilterSpecification<T> {

    public Specification<T> getSearchSpecification(List<FilterPaging> filterPagingList, String filterType){

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            for (FilterPaging request : filterPagingList){
                switch (request.getOperation().toUpperCase()){
                    case "=":
                        Predicate equal = criteriaBuilder.equal(root.get(request.getColumn()), request.getValue());
                        predicates.add(equal);
                        break;

                    case "!=":
                        Predicate not_equal = criteriaBuilder.notEqual(root.get(request.getColumn()), request.getValue());
                        predicates.add(not_equal);
                        break;

                    case "LIKE":
                        Predicate like = criteriaBuilder.like(root.get(request.getColumn()), "%" + request.getValue() + "%");
                        predicates.add(like);
                        break;

                    case "NOT_LIKE":
                        Predicate not_like = criteriaBuilder.notLike(root.get(request.getColumn()), "%" + request.getValue() + "%");
                        predicates.add(not_like);
                        break;

                    case "IN":
                        ArrayList<Object> arrDataInclude = (ArrayList<Object>) request.getValue();
                        Predicate in = root.get(request.getColumn()).in(arrDataInclude);
                        predicates.add(in);
                        break;

                    case "NOT_IN":
                        ArrayList<Object> arrDataExclude = (ArrayList<Object>) request.getValue();
                        Predicate not_in = criteriaBuilder.not(root.get(request.getColumn()).in(arrDataExclude));
                        predicates.add(not_in);
                        break;

                    case ">":
                        Predicate greaterThan = criteriaBuilder.greaterThan(root.get(request.getColumn()), request.getValue().toString());
                        predicates.add(greaterThan);
                        break;

                    case ">=":
                        Predicate greaterThanOrEqualTo = criteriaBuilder.greaterThanOrEqualTo(root.get(request.getColumn()), request.getValue().toString());
                        predicates.add(greaterThanOrEqualTo);
                        break;

                    case "<":
                        Predicate lessThan = criteriaBuilder.lessThan(root.get(request.getColumn()), request.getValue().toString());
                        predicates.add(lessThan);
                        break;

                    case "<=":
                        Predicate lessThanOrEqualTo = criteriaBuilder.lessThanOrEqualTo(root.get(request.getColumn()), request.getValue().toString());
                        predicates.add(lessThanOrEqualTo);
                        break;

                    case "DATE>":
                        LocalDateTime ConvertDateGreaterThan = ConvertDateTime.convertToLocalDateTime(request.getValue().toString());
                        Predicate dateGreaterThan = criteriaBuilder.greaterThan(root.get(request.getColumn()), ConvertDateGreaterThan);
                        predicates.add(dateGreaterThan);
                        break;

                    case "DATE>=":
                        LocalDateTime ConvertDateGreaterThanOrEqualTo = ConvertDateTime.convertToLocalDateTime(request.getValue().toString());
                        Predicate dateGreaterThanOrEqualTo = criteriaBuilder.greaterThan(root.get(request.getColumn()), ConvertDateGreaterThanOrEqualTo);
                        predicates.add(dateGreaterThanOrEqualTo);
                        break;

                    case "DATE<":
                        LocalDateTime ConvertDateLessThan = ConvertDateTime.convertToLocalDateTime(request.getValue().toString());
                        Predicate dateLessThan = criteriaBuilder.lessThan(root.get(request.getColumn()), ConvertDateLessThan);
                        predicates.add(dateLessThan);
                        break;

                    case "DATE<=":
                        LocalDateTime ConvertDateLessThanOrEqualTo = ConvertDateTime.convertToLocalDateTime(request.getValue().toString());
                        Predicate dateLessThanOrEqualTo = criteriaBuilder.lessThan(root.get(request.getColumn()), ConvertDateLessThanOrEqualTo);
                        predicates.add(dateLessThanOrEqualTo);
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: ");
                }
            }
            if (Objects.equals(filterType.toUpperCase(), "OR")){
                return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
            } else{
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            }
        };
    }

}
