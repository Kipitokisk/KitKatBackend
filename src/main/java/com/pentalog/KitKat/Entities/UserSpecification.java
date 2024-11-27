package com.pentalog.KitKat.Entities;

import com.pentalog.KitKat.Entities.City;
import com.pentalog.KitKat.Entities.Language;
import com.pentalog.KitKat.Entities.Position;
import com.pentalog.KitKat.Entities.Seniority;
import com.pentalog.KitKat.Entities.User.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification {

    public static Specification<User> filterByPositionNames(List<String> positionNames) {
        return (root, query, criteriaBuilder) -> {
            if (positionNames == null || positionNames.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<User, Position> positionJoin = root.join("position");
            CriteriaBuilder.In<String> inClause = criteriaBuilder.in(positionJoin.get("name")); // Use IN clause
            for (String positionName : positionNames) {
                inClause.value(positionName);
            }
            return inClause; // Return IN clause for positions
        };
    }

    public static Specification<User> filterBySeniorityNames(List<String> seniorityNames) {
        return (root, query, criteriaBuilder) -> {
            if (seniorityNames == null || seniorityNames.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<User, Seniority> seniorityJoin = root.join("seniority");
            CriteriaBuilder.In<String> inClause = criteriaBuilder.in(seniorityJoin.get("name")); // Use IN clause
            for (String seniorityName : seniorityNames) {
                inClause.value(seniorityName);
            }
            return inClause; // Return IN clause for seniority
        };
    }

    public static Specification<User> filterByCountryNames(List<String> countryNames) {
        return (root, query, criteriaBuilder) -> {
            if (countryNames == null || countryNames.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<User, City> cityJoin = root.join("city");
            CriteriaBuilder.In<String> inClause = criteriaBuilder.in(cityJoin.get("country").get("countryName")); // Use IN clause
            for (String countryName : countryNames) {
                inClause.value(countryName);
            }
            return inClause; // Return IN clause for countries
        };
    }

    public static Specification<User> filterBySkillNames(List<String> skillNames) {
        return (root, query, criteriaBuilder) -> {
            if (skillNames == null || skillNames.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Join<User, SkillRating> skillRatingJoin = root.join("skillRating");
            Join<SkillRating, Skill> skillJoin = skillRatingJoin.join("skill");
            CriteriaBuilder.In<String> inClause = criteriaBuilder.in(skillJoin.get("name")); // Use IN clause
            for (String skillName : skillNames) {
                inClause.value(skillName);
            }
            return inClause; // Return IN clause for skills
        };
    }

    public static Specification<User> filterByLanguageNames(List<String> languageNames) {
        return (root, query, criteriaBuilder) -> {
            if (languageNames == null || languageNames.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            // Create a subquery to find users who have all specified languages
            Subquery<Long> subquery = query.subquery(Long.class);
            Root<User> subqueryRoot = subquery.from(User.class);
            Join<User, Language> languageJoin = subqueryRoot.join("languages");

            // Ensure the user has all the specified languages
            CriteriaBuilder.In<String> inClause = criteriaBuilder.in(languageJoin.get("languageName"));
            for (String languageName : languageNames) {
                inClause.value(languageName);
            }

            // Group by user id and ensure that the count of languages matches the input
            subquery.select(subqueryRoot.get("id"))
                    .where(inClause)
                    .groupBy(subqueryRoot.get("id"))
                    .having(criteriaBuilder.equal(criteriaBuilder.count(languageJoin), languageNames.size())); // Ensure the count of matched languages equals the size of the input list

            return root.get("id").in(subquery); // Only return users that are in the subquery
        };
    }


    public static Specification<User> combinedFilter(
            List<String> positionNames,
            List<String> seniorityNames,
            List<String> countryNames,
            List<String> skillNames,
            List<String> languageNames) {
        return Specification.where(filterByPositionNames(positionNames))
                .and(filterBySeniorityNames(seniorityNames))
                .and(filterByCountryNames(countryNames))
                .and(filterBySkillNames(skillNames))
                .and(filterByLanguageNames(languageNames));
    }
}
