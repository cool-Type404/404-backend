package com.type404.backend.domain.store.repository;

import com.type404.backend.domain.auth.entity.enumtype.EatingLevel;
import com.type404.backend.domain.store.entity.StoreInfoEntity;
import com.type404.backend.domain.store.entity.StoreMenuEntity;
import com.type404.backend.domain.store.entity.StoreSeatEntity;
import com.type404.backend.domain.store.entity.enumtype.StoreCategory;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class StoreSpecifications {

    // 카테고리 필터
    public static Specification<StoreInfoEntity> hasCategories(List<StoreCategory> types) {
        return (root, query, cb) -> (types == null || types.isEmpty())
                ? null : root.get("storeCategory").in(types);
    }


    // 혼밥 레벨 필터
    public static Specification<StoreInfoEntity> hasEatingLevels(List<EatingLevel> levels) {
        return (root, query, cb) -> (levels == null || levels.isEmpty())
                ? null : root.get("eatingLevel").in(levels);
    }


    // 좌석 조건 필터
    public static Specification<StoreInfoEntity> hasSeats(List<Integer> seats) {
        return (root, query, cb) -> {
            if (seats == null || seats.isEmpty()) return null;

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<StoreSeatEntity> seatRoot = subquery.from(StoreSeatEntity.class);

            List<Predicate> seatPredicates = new ArrayList<>();

            if (seats.contains(1)) seatPredicates.add(cb.isTrue(seatRoot.get("singleSeat")));
            if (seats.contains(2)) seatPredicates.add(cb.isTrue(seatRoot.get("doubleSeat")));
            if (seats.contains(3)) seatPredicates.add(cb.isTrue(seatRoot.get("tripleSeat")));

            subquery.select(seatRoot.get("storeId").get("storeInfoPK"))
                    .where(cb.or(seatPredicates.toArray(new Predicate[0])));

            return cb.in(root.get("storeInfoPK")).value(subquery);
        };
    }


    // 상호명 검색
    public static Specification<StoreInfoEntity> containsStoreName(String storeName) {
        return (root, query, cb) -> (storeName == null || storeName.isBlank())
                ? null : cb.like(root.get("storeName"), "%" + storeName + "%");
    }


    // 음식명 검색
    public static Specification<StoreInfoEntity> containsFoodName(String food) {
        return (root, query, cb) -> {
            if (food == null || food.isBlank()) return null;

            Subquery<Long> subquery = query.subquery(Long.class);
            Root<StoreMenuEntity> menuRoot = subquery.from(StoreMenuEntity.class);

            subquery.select(menuRoot.get("storeId").get("storeInfoPK"))
                    .where(cb.like(menuRoot.get("menuName"), "%" + food + "%"));

            return cb.in(root.get("storeInfoPK")).value(subquery);
        };
    }
}