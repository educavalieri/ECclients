package com.educavalieri.ECclients.services.implement;

import com.educavalieri.ECclients.dto.ClientDTO;
import com.educavalieri.ECclients.entities.Client;
import com.educavalieri.ECclients.repositories.ClientRepository;
import com.educavalieri.ECclients.services.exceptions.DataBaseException;
import com.educavalieri.ECclients.services.exceptions.ResourceNotFoundException;
import com.educavalieri.ECclients.services.interfaces.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ClientServiceIMP implements ClientServiceInterface {

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public Page<ClientDTO> findAllService(PageRequest pageRequest) {
        Page<Client> entity = clientRepository.findAll(pageRequest);
        return entity.map(x -> new ClientDTO(x));
    }

    @Override
    public ClientDTO findByIdService(Long id) {
        Optional<Client> entity = clientRepository.findById(id);
        Client dto = entity.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return null;
    }

    @Override
    public ClientDTO saveService(ClientDTO dto) {
        Client entity = new Client();
        insertEntity(entity, dto);
        entity = clientRepository.save(entity);
        return new ClientDTO(entity);
    }

    @Override
    public ClientDTO updateService(Long id, ClientDTO dto) {
        try {
            Client entity = clientRepository.findById(id).get();
            insertEntity(entity, dto);
            entity = clientRepository.save(entity);
            return new ClientDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found" +id);
        }
    }

    @Override
    public void deleteService(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found" +id);
        } catch (DataIntegrityViolationException e){
            throw new DataBaseException("Integrity violation");
        }

    }

    public void insertEntity(Client entity, ClientDTO dto) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());

    }
}
