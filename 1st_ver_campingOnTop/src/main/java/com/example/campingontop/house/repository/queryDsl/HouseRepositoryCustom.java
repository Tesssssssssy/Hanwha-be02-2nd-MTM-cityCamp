package com.example.campingontop.house.repository.queryDsl;

import com.example.campingontop.house.model.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface HouseRepositoryCustom {
    Page<House> findList(Pageable pageable);
    Optional<House> findActiveHouse(Long id);
    Page<House> findByPriceDesc(Pageable pageable);

    Page<House> findByPriceAsc(Pageable pageable);
    Page<House> findByName(Pageable pageable, String name);


    Page<House> getNearestHouseList(Pageable pageable, Double latitude, Double longitude);

    Page<House> findByAddress(Pageable pageable, String address);

    // List<House> findHousesWithinDistance(Double latitude, Double longitude);


}
