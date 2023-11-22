package dev.omedia.controller;

import dev.omedia.exceprion.ResourceNotFoundException;
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
    public CourierController(CourierService service) {
        this.courierService = service;
    }

    @GetMapping
    public Collection<Courier> getCouriers(
            @RequestParam(required = false, defaultValue = "${page}", name = "p") int page
            , @RequestParam(required = false, defaultValue = "${pageSize}", name = "s") int pageSize) {
        return courierService.getCouriers(page, pageSize);
    }


    @GetMapping("{identifier}")
    public Courier getCourier(@PathVariable(name = "identifier") long id) {
        Optional<Courier> courierOptional = courierService.getCourier(id);
        return courierOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Courier Not Found with id " + id));
    }

    @DeleteMapping("{id}")
    public Courier removeCourier(@PathVariable long id) {
        return courierService.removeCourier(id).orElseThrow(ResourceNotFoundException::new);
    }

    @PostMapping
    public boolean addCourier(@RequestBody Courier courier) {
        return courierService.addCourier(courier);
    }

    @PutMapping("{id}")
    public boolean updateCourier(@PathVariable long id, @RequestBody Courier courier) {
        return courierService.updateCourier(id, courier);
    }
}
