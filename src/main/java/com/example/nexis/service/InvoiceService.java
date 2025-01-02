package com.example.nexis.service;

import com.example.nexis.entity.Invoice;
import com.example.nexis.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> getInvoicesByUserId(String userId) {
        // Gọi phương thức findByUser_Id từ InvoiceRepository
        return invoiceRepository.findByUser_Id(userId);
    }
}
