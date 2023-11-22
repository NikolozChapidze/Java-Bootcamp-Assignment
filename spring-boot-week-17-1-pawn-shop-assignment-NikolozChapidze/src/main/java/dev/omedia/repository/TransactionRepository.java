package dev.omedia.repository;

import dev.omedia.domain.Item;
import dev.omedia.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends GenericRepository<Transaction> {
    List<Transaction> findByItemAndTransactionDateBetween(Item item,
                                                          @NotNull @Past LocalDate transactionDateStart,
                                                          @NotNull @PastOrPresent LocalDate transactionDateEnd);
}
