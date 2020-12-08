package com.naguib.technicalTasks.vaccNow.services.impl;

import com.naguib.technicalTasks.vaccNow.controller.dto.ResponseDTO;
import com.naguib.technicalTasks.vaccNow.services.PaymentMethodsCRUDService;
import com.naguib.technicalTasks.vaccNow.utils.PayingType;
import org.springframework.stereotype.Service;

@Service
public class PaymentMethodsCRUDServiceImpl implements PaymentMethodsCRUDService {
    @Override
    public ResponseDTO gatAllPaymentMethods() {
        return new ResponseDTO(PayingType.values());
    }
}
