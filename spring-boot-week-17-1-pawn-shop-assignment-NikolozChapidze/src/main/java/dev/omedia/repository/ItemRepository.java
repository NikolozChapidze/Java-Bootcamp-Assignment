package dev.omedia.repository;

import dev.omedia.domain.Item;
import dev.omedia.domain.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ItemRepository<T extends Item> extends JpaRepository<T, Long> {

    @Query("select I from Item I where I.deadline = :date AND I.itemStatus = :status")
    List<Item> getItemsToBeChecked(@Param("date") LocalDate date, @Param("status") ItemStatus itemStatus);
}
