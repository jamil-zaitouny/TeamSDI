package web.controller;

import core.service.ClientService;
import core.service.ClientServiceInterface;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.converter.ClientConverter;
import web.dto.ClientDto;
import web.dto.ClientsDto;

@RestController
public class ClientController
{
    final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientServiceInterface clientService;

    @Autowired
    private ClientConverter clientConverter;

    @RequestMapping(value = "/clients",method= RequestMethod.GET)
    ClientsDto getClients()
    {
        logger.trace("printing clients");
        return new ClientsDto(clientConverter.convertModelsToDtos(clientService.getAllClients()));
    }

    @RequestMapping(value = "/clients",method= RequestMethod.POST)
    ResponseEntity<?> saveClient(@RequestBody ClientDto clientDto)
    {
        logger.trace("adding client");
        try {
            clientService.addClient(clientConverter.convertDtoToModel(clientDto));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ServiceException e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/clients/{id}",method = RequestMethod.PUT)
    ResponseEntity<?> updateClient(@PathVariable Integer id, @RequestBody ClientDto clientDto)
    {
        logger.trace("updating client");
        try {
            clientService.updateClient(clientConverter.convertDtoToModel(clientDto));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ServiceException e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/clients/{id}",method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Integer id)
    {
        logger.trace("deleting client");
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/clients/filter",method = RequestMethod.GET)
    ClientsDto filterByName(@RequestParam(value ="name") String name)
    {
        logger.trace("filtering clients");
        return new ClientsDto(clientConverter.convertModelsToDtos(clientService.filterByName(name)));
    }
}
