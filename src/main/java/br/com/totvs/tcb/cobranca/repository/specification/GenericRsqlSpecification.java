package br.com.totvs.tcb.cobranca.repository.specification;

import br.com.totvs.tcb.cobranca.domain.DominioRecurso;
import br.com.totvs.tcb.cobranca.repository.specification.enums.RsqlSearchOperation;
import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GenericRsqlSpecification<T> implements Specification<T> {

    private String property;
    private ComparisonOperator operator;
    private List<String> arguments;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Object> args = castArguments(root);
        Object argument = args.get(0);
        switch (RsqlSearchOperation.getSimpleOperator(operator)) {
            case EQUAL: {
                if (argument instanceof String) {
                    return builder.like(root.get(property), argument.toString().replace('*', '%'));
                } else if (argument == null) {
                    return builder.isNull(root.get(property));
                } else {
                    return builder.equal(root.get(property), argument);
                }
            }
            case NOT_EQUAL: {
                if (argument instanceof String) {
                    return builder.notLike(root.<String>get(property), argument.toString().replace('*', '%'));
                } else if (argument == null) {
                    return builder.isNotNull(root.get(property));
                } else {
                    return builder.notEqual(root.get(property), argument);
                }
            }
            case GREATER_THAN: {
                return builder.greaterThan(root.get(property), argument.toString());
            }
            case GREATER_THAN_OR_EQUAL: {
                return builder.greaterThanOrEqualTo(root.<String>get(property), argument.toString());
            }
            case LESS_THAN: {
                return builder.lessThan(root.<String>get(property), argument.toString());
            }
            case LESS_THAN_OR_EQUAL: {
                return builder.lessThanOrEqualTo(root.<String>get(property), argument.toString());
            }
            case IN:
                return root.get(property).in(args);
            case NOT_IN:
                return builder.not(root.get(property).in(args));
        }
        return null;
    }

    private List<Object> castArguments(final Root<T> root) {

        Class<? extends Object> type = root.get(property).getJavaType();

        return arguments.stream().map(arg -> {
            if (type.equals(Integer.class)) {
                return Integer.parseInt(arg);
            } else if (type.equals(Long.class)) {
                return Long.parseLong(arg);
            } else if (type.equals(DominioRecurso.class)) {
                return DominioRecurso.valueOf(arg);
            } else if (type.equals(Date.class)) {
                return new Date(Long.parseLong(arg));
            } else {
                return arg;
            }
        }).collect(Collectors.toList());
    }
}
