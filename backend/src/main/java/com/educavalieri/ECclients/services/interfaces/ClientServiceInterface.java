package com.educavalieri.ECclients.services.interfaces;

import com.educavalieri.ECclients.dto.ClientDTO;
import com.educavalieri.ECclients.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface ClientServiceInterface {

    Page<ClientDTO> findAllService(PageRequest pageRequest);

    ClientDTO findByIdService(Long id);

    ClientDTO saveService(ClientDTO dto);

    ClientDTO updateService(Long id, ClientDTO dto);

    void deleteService(Long id);

}
