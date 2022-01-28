package com.educavalieri.ECclients.repositories;

import com.educavalieri.ECclients.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
