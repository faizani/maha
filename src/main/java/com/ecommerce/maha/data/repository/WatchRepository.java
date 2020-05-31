package com.ecommerce.maha.data.repository;

import com.ecommerce.maha.core.entity.Watch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface WatchRepository extends JpaRepository<Watch, Integer> {

    List<Watch> findByWatchCodeIn(Collection<String> watchCodes);
}
