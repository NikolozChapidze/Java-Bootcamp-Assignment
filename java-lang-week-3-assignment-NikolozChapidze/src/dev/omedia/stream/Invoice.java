package dev.omedia.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

public class Invoice {
    private final String partNumber;
    private final String partDescription;
    private final Integer quantityOfItem;
    private final Double pricePerltem;

    public Invoice(String partNumber, String partDescription, Integer quantityOfItem, Double pricePerltem) {
        this.partNumber = partNumber;
        this.partDescription = partDescription;
        this.quantityOfItem = quantityOfItem;
        this.pricePerltem = pricePerltem;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public String getPartDescription() {
        return partDescription;
    }

    public Integer getQuantityOfItem() {
        return quantityOfItem;
    }

    public Double getPricePerltem() {
        return pricePerltem;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "partNumber='" + partNumber + '\'' +
                ", partDescription='" + partDescription + '\'' +
                ", quantityOfItem=" + quantityOfItem +
                ", pricePerltem=" + pricePerltem +
                '}';
    }

    public static List<Invoice> sortI(ArrayList<Invoice> invoices){
        invoices.sort((o1,o2) ->{
            if (Objects.nonNull(o1) && Objects.nonNull(o2)) {
                if(Objects.isNull(o1.getPartDescription())){
                    return -1;
                }
                if(Objects.isNull(o2.getPartDescription())){
                    return 1;
                }
                return o1.getPartDescription().compareTo(o2.getPartDescription());
            }

            if (o1 == null && o2 == null) {
                return 0;
            }

            return Objects.isNull(o1) ? 1 : -1;
        });
        invoices.forEach(System.out::println);
        return invoices;
    }

    public static List<Invoice> sortII(ArrayList<Invoice> invoices){
        invoices.sort((o1,o2) ->{
            if (Objects.nonNull(o1) && Objects.nonNull(o2)) {
                if(Objects.isNull(o1.getPricePerltem())){
                    return -1;
                }
                if(Objects.isNull(o2.getPricePerltem())){
                    return 1;
                }
                return Double.compare(o1.getPricePerltem(), o2.getPricePerltem());
            }

            if (o1 == null && o2 == null) {
                return 0;
            }

            return Objects.isNull(o1) ? 1 : -1;
        });
        invoices.forEach(System.out::println);
        return invoices;
    }

    public static void exIII(ArrayList<Invoice> invoices){
        invoices.sort((o1,o2) ->{
            if (Objects.nonNull(o1) && Objects.nonNull(o2)) {
                if(Objects.isNull(o1.getQuantityOfItem())){
                    return -1;
                }
                if(Objects.isNull(o2.getQuantityOfItem())){
                    return 1;
                }
                return Integer.compare(o1.getQuantityOfItem(), o2.getQuantityOfItem());
            }

            if (o1 == null && o2 == null) {
                return 0;
            }

            return Objects.isNull(o1) ? 1 : -1;
        });

        invoices.stream()
                .map(invoice -> "description: " + invoice.getPartDescription() +"; quantity: " +invoice.getQuantityOfItem())
                .forEach(System.out::println);
    }

    public static void exIV(ArrayList<Invoice> invoices){
        invoices.sort((o1,o2) ->{
            if (Objects.nonNull(o1) && Objects.nonNull(o2)) {
                return Double.compare(o1.getQuantityOfItem() * o1.getPricePerltem(), o2.getQuantityOfItem() * o2.getPricePerltem());
            }

            if (o1 == null && o2 == null) {
                return 0;
            }

            return Objects.isNull(o1) ? 1 : -1;
        });

        invoices.stream()
                .map(invoice -> "description: " + invoice.getPartDescription() +"; invoice value: "
                        + (invoice.getQuantityOfItem() * invoice.getPricePerltem()))
                .forEach(System.out::println);
    }

    public static void exV(ArrayList<Invoice> invoices){
        invoices.sort((o1,o2) ->{
            if (Objects.nonNull(o1) && Objects.nonNull(o2)) {
                return Double.compare(o1.getQuantityOfItem() * o1.getPricePerltem(), o2.getQuantityOfItem() * o2.getPricePerltem());
            }

            if (o1 == null && o2 == null) {
                return 0;
            }

            return Objects.isNull(o1) ? 1 : -1;
        });

        invoices.stream()
                .filter(invoice -> (invoice.getQuantityOfItem() * invoice.getPricePerltem() > 200
                        && invoice.getQuantityOfItem() * invoice.getPricePerltem() < 500))
                .map(invoice -> "description: " + invoice.getPartDescription() +"; invoice value: "
                        + (invoice.getQuantityOfItem() * invoice.getPricePerltem()))
                .forEach(System.out::println);
    }

    public static void exVI(ArrayList<Invoice> invoices){
        invoices.stream().filter(invoice -> invoice.partDescription.contains("saw"))
                .forEach(System.out::println);
    }
}
