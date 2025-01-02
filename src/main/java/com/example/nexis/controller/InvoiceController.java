package com.example.nexis.controller;

import com.example.nexis.entity.Invoice;
import com.example.nexis.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    // Lấy tất cả hóa đơn
    @GetMapping
    public ResponseEntity<?> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return ResponseEntity.ok(invoices);
    }

    // Lấy hóa đơn theo ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getInvoiceById(@PathVariable String id) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if (invoice == null) {
            return ResponseEntity.badRequest().body("Invoice not found");
        }
        return ResponseEntity.ok(invoice);
    }

    // Tạo hóa đơn mới
    @PostMapping
    public ResponseEntity<?> createInvoice(@RequestBody Invoice invoice) {
        invoiceRepository.save(invoice);
        return ResponseEntity.ok("Invoice created successfully");
    }

    // Cập nhật hóa đơn
    @PutMapping("/{id}")
    public ResponseEntity<?> updateInvoice(@PathVariable String id, @RequestBody Invoice updatedInvoice) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if (invoice == null) {
            return ResponseEntity.badRequest().body("Invoice not found");
        }

        invoice.setInvoiceDate(updatedInvoice.getInvoiceDate());
        invoice.setTotalAmount(updatedInvoice.getTotalAmount());
        invoice.setPaymentStatus(updatedInvoice.getPaymentStatus());
        invoice.setDueDate(updatedInvoice.getDueDate());
        invoiceRepository.save(invoice);

        return ResponseEntity.ok("Invoice updated successfully");
    }

    // Xóa hóa đơn
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable String id) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);
        if (invoice == null) {
            return ResponseEntity.badRequest().body("Invoice not found");
        }

        invoiceRepository.delete(invoice);
        return ResponseEntity.ok("Invoice deleted successfully");
    }
}
