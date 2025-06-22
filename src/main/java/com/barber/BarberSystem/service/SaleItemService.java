package com.barber.BarberSystem.service;

import com.barber.BarberSystem.repository.SaleItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleItemService {

    private final SaleItemRepository saleItemRepository;

}
