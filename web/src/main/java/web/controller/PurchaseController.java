package web.controller;

import core.model.Purchase;
import core.service.PurchaseService;
import core.service.PurchaseServiceInterface;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.converter.PurchaseConverter;
import web.dto.ClientDto;
import web.dto.ClientsDto;
import web.dto.PurchaseDto;
import web.dto.PurchasesDto;

import java.util.List;

@RestController
public class PurchaseController {
    final Logger logger = LoggerFactory.getLogger(PurchaseController.class);

    @Autowired
    private PurchaseServiceInterface purchaseService;

    @Autowired
    private PurchaseConverter purchaseConverter;

    @RequestMapping(value = "/purchases",method= RequestMethod.GET)
    PurchasesDto getPurchases()
    {
        logger.trace("printing purchases");
        return new PurchasesDto(purchaseConverter.convertModelsToDtos(purchaseService.getAllPurchases()));
    }

    @RequestMapping(value = "/purchases",method= RequestMethod.POST)
    ResponseEntity<?> savePurchase(@RequestBody PurchaseDto  purchaseDto) throws Throwable {
        logger.trace("adding purchase");
        try {
            purchaseService.addPurchase( purchaseConverter.convertDtoToModel(purchaseDto));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ServiceException e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/purchases/{id}",method = RequestMethod.PUT)
    ResponseEntity<?> updatePurchase(@PathVariable Integer id, @RequestBody PurchaseDto  purchaseDto) throws Throwable {
        logger.trace("updating purchase");
        try {
            purchaseService.updatePurchase( purchaseConverter.convertDtoToModel( purchaseDto));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (ServiceException e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/purchases/{id}",method = RequestMethod.DELETE)
    ResponseEntity<?> deletePurchase(@PathVariable Integer id)
    {
        logger.trace("deleting purchase");
        purchaseService.deletePurchase(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
