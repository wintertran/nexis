package com.example.nexis.repository;

import com.example.nexis.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {

    // Tìm các hóa đơn theo user
    List<Invoice> findByUser_Id(String userId); // Sử dụng "user.id" để tham chiếu đến ID của User
}
