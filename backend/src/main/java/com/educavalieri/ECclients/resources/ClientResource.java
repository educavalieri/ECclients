package com.educavalieri.ECclients.resources;

import com.educavalieri.ECclients.dto.ClientDTO;
import com.educavalieri.ECclients.services.implement.ClientServiceIMP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "clients" )
public class ClientResource {

    @Autowired
    private ClientServiceIMP clientServiceIMP;

    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public ResponseEntity<Page<ClientDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "DESC") String direction){

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<ClientDTO> list = clientServiceIMP.findAllService(pageRequest);
        return ResponseEntity.ok().body(list);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ClientDTO> findById(@PathVariable("id") Long id){
        ClientDTO dto = clientServiceIMP.findByIdService(id);
        return ResponseEntity.ok().body(dto);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<ClientDTO> save(@RequestBody ClientDTO dto){
        dto = clientServiceIMP.saveService(dto);
        return ResponseEntity.ok().body(dto);
    }
    @RequestMapping(value = "/update{id}", method = RequestMethod.PUT)
    public ResponseEntity<ClientDTO> update(@PathVariable("id") Long id,
                                            @RequestBody ClientDTO dto){
        dto = clientServiceIMP.updateService(id, dto);
        return ResponseEntity.ok().body(dto);
    }

    @RequestMapping(value = "delete{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        clientServiceIMP.deleteService(id);
        return ResponseEntity.noContent().build();
    }


}
