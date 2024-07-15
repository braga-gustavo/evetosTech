package com.eventostech.api.domain.repository;

import com.eventostech.api.domain.entity.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository  extends JpaRepository<Address, UUID> {
}
