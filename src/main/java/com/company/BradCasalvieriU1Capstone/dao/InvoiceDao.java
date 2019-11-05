package com.company.BradCasalvieriU1Capstone.dao;

import com.company.BradCasalvieriU1Capstone.dto.Invoice;

import java.util.List;

public interface InvoiceDao {
    Invoice addInvoice(Invoice invoice);
    Invoice getInvoiceById(int id);
    List<Invoice> getAllInvoices();
    void updateInvoice(Invoice invoice);
    void deleteInvoice(int id);
}
