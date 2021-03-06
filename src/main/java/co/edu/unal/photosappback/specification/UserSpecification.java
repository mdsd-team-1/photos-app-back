package co.edu.unal.photosappback.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import co.edu.unal.photosappback.model.User;
import co.edu.unal.photosappback.specification.criteria.SearchCriteria;

public class UserSpecification implements Specification<User> {

	private SearchCriteria criteria;

	public UserSpecification(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	@Override
	public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

		if (criteria.getOperation().equalsIgnoreCase(">")) {
			return criteriaBuilder.greaterThanOrEqualTo(
					root.<String> get(criteria.getKey()), criteria.getValue().toString());
		} 

		else if (criteria.getOperation().equalsIgnoreCase("<")) {
			return criteriaBuilder.lessThanOrEqualTo(
					root.<String> get(criteria.getKey()), criteria.getValue().toString());
		}

		else if (criteria.getOperation().equalsIgnoreCase(":")) {
			if (root.get(criteria.getKey()).getJavaType() == String.class) {
				return criteriaBuilder.like(
						root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
			} else {
				return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
			}
		}
		return null;
	}
}