package dev.omedia.scheduler;

import dev.omedia.domain.Item;
import dev.omedia.domain.ItemStatus;
import dev.omedia.domain.Transaction;
import dev.omedia.repository.ItemRepository;
import dev.omedia.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Component
public class Scheduler {

    private final ItemRepository<Item> itemRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public Scheduler(ItemRepository<Item> itemRepository, TransactionRepository transactionRepository) {
        this.itemRepository = itemRepository;
        this.transactionRepository = transactionRepository;
    }

    @Scheduled(cron = "${scheduling.start-of-the-day}")
    @Transactional
    public void checkPawnedItems(){
        List<Item> itemList = itemRepository.getItemsToBeChecked(LocalDate.now(), ItemStatus.ACTIVE);
        itemList.forEach(item ->{
            double totalThisMonthPayment = getThisMonthTransactions(item);
            if(totalThisMonthPayment <= item.getMonthlyPayment()){
                item.setItemStatus(ItemStatus.CONFISCATED);
                itemRepository.save(item);
            }else{
                if(item.getTotalPayed()+totalThisMonthPayment >= item.getItemPrice()){
                    item.setItemStatus(ItemStatus.PAID);
                    itemRepository.save(item);
                }else{
                    item.setPayedUpfront(totalThisMonthPayment-item.getMonthlyPayment());
                    item.setTotalPayed(item.getTotalPayed()+item.getMonthlyPayment());
                    itemRepository.save(item);
                }
            }
        });
    }

    private double getThisMonthTransactions(Item item) {
        return  transactionRepository
                .findByItemAndTransactionDateBetween(item, LocalDate.now().minusMonths(1), LocalDate.now())
                .stream().mapToDouble(Transaction::getAmount).sum()+ item.getPayedUpfront();
    }
}
