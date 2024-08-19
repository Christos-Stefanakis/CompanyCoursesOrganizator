package org.example.service;

import org.example.model.Manufacturer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ManufacturerServiceTest {
    ManufacturerService manufacturerService = new ManufacturerService();

    @Test
    void addManufacturer() {
        manufacturerService.addManufacturer("Coursera","onLine", "");
        manufacturerService.addManufacturer("AWS Training","onLine", "");

        Manufacturer coursera = manufacturerService.getManufacturerList().stream().filter(m->m.getName().equals("Coursera")).findAny().get();
        Manufacturer aws = manufacturerService.getManufacturerList().stream().filter(m->m.getId() == 2).findAny().get();

        assertEquals(coursera, manufacturerService.findManufacturer("coursera").stream().findAny().get());
        assertEquals(coursera, manufacturerService.findManufacturer(1).stream().findAny().get());
        assertEquals(aws, manufacturerService.findManufacturer("aws").stream().findAny().get());
        assertEquals(aws, manufacturerService.findManufacturer(2).stream().findAny().get());
    }
}