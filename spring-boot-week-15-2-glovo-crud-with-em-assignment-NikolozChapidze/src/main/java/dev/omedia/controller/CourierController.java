package dev.omedia.controller;

import dev.omedia.model.Courier;
import dev.omedia.service.CourierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("couriers")
@Slf4j
public class CourierController {
    private final CourierService courierService;

    @Autowired
    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    @GetMapping
    public Collection<Courier> getCouriers(
            @RequestParam(required = false, defaultValue = "${page}", name = "p") int page
            , @RequestParam(required = false, defaultValue = "${pageSize}", name = "s") int pageSize) {
        return courierService.getCouriers(page, pageSize);
    }


    @GetMapping("{identifier}")
    public Courier getCourier(@PathVariable(name = "identifier") long id) {
        Optional<Courier> sportOptional = courierService.getCourier(id);
        return sportOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Courier Not Found with id " + id));
    }

    @DeleteMapping("{id}")
    public int removeCourier(@PathVariable long id) {
        return courierService.removeCourier(id);
    }

    @PostMapping
    public void addCourier(@RequestBody Courier sport) {
        courierService.addCourier(sport);
    }

    @PutMapping("{id}")
    public Courier updateCourier(@PathVariable long id, @RequestBody Courier sport) {
        return courierService.updateCourier(id, sport);
    }
}
