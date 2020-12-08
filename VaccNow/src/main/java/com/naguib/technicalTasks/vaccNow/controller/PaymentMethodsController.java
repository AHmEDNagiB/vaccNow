package com.naguib.technicalTasks.vaccNow.controller;

import com.naguib.technicalTasks.vaccNow.controller.dto.ResponseDTO;
import com.naguib.technicalTasks.vaccNow.services.PaymentMethodsCRUDService;
import com.naguib.technicalTasks.vaccNow.utils.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payment-methods")
public class PaymentMethodsController {
    private PaymentMethodsCRUDService paymentMethodsCRUDService;
    private UtilService utilService;

    @Autowired
    public PaymentMethodsController(PaymentMethodsCRUDService paymentMethodsCRUDService, UtilService utilService) {
        this.paymentMethodsCRUDService = paymentMethodsCRUDService;
        this.utilService = utilService;
    }


    @GetMapping
    public ResponseEntity<ResponseDTO> gatAllPaymentMethods() {
        return utilService.getResponse(paymentMethodsCRUDService.gatAllPaymentMethods());

    }


}
