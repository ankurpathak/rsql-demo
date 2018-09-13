package com.github.ankurpathak.rsql.rsqldemo;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.LogicalNode;
import cz.jirutka.rsql.parser.ast.LogicalOperator;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class GenericRsqlCriteriaBuilder {

    public Criteria createSpecification(final Node node, String resource) {
        if (node instanceof LogicalNode) {
            return createSpecification((LogicalNode) node, resource);
        }
        if (node instanceof ComparisonNode) {
            return createSpecification((ComparisonNode) node, resource);
        }
        return null;
    }

    public Criteria createSpecification(final LogicalNode logicalNode, String resource) {
        final List<Criteria> specs = new ArrayList <>();
        Criteria temp = null;
        for (final Node node : logicalNode.getChildren()) {
            temp = createSpecification(node, resource);
            if (temp != null) {
                specs.add(temp);
            }
        }

        Criteria result = new Criteria();

        if(!CollectionUtils.isEmpty(specs)) {

            //result = specs.get(0);

            if (logicalNode.getOperator() == LogicalOperator.AND) {
                for (int i = 0; i < specs.size(); i++) {
                    result = result.andOperator(specs.get(i));
                }
            } else if (logicalNode.getOperator() == LogicalOperator.OR) {
                for (int i = 0; i < specs.size(); i++) {
                    result = result.orOperator(specs.get(i));
                }
            }

        }

        return result;
    }

    public Criteria createSpecification(final ComparisonNode comparisonNode, String resource) {
        return CriteriaFactory.create(comparisonNode, resource);
    }

}