package com.company.BradCasalvieriU1Capstone.dao;

import com.company.BradCasalvieriU1Capstone.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoTest {
    @Autowired
    InvoiceDao invoiceDao;

    private Invoice invoice;

    @Before
    public void setUp() throws Exception {
        clearDatabase();
        setUpTestObjects();
    }

    public void clearDatabase() {
        invoiceDao.getAllInvoices().forEach(invoice1 -> invoiceDao.deleteInvoice(invoice1.getId()));
    }

    public void setUpTestObjects() {
        invoice = new Invoice();
        invoice.setName("John Doe");
        invoice.setStreet("Mulberry Street");
        invoice.setCity("New York");
        invoice.setState("NY");
        invoice.setZipcode("55555");
        invoice.setItemType("Console");
        invoice.setItemId(1);
        invoice.setUnitPrice(new BigDecimal("399.99"));
        invoice.setQuantity(1);
        invoice.setSubtotal(new BigDecimal("399.99"));
        invoice.setTax(new BigDecimal("23.99"));
        invoice.setProcessingFee(new BigDecimal("14.99"));
        invoice.setTotal(new BigDecimal("438.97"));
    }

    @Test
    public void shouldAddGetDeleteInvoice() {
        invoice = invoiceDao.addInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoiceById(invoice.getId());
        assertEquals(invoice, invoice1);

        invoiceDao.deleteInvoice(invoice.getId());
        invoice1 = invoiceDao.getInvoiceById(invoice.getId());
        assertNull(invoice1);
    }

    @Test
    public void shouldGetAllInvoices() {
        invoiceDao.addInvoice(invoice);

        List<Invoice> invoices = invoiceDao.getAllInvoices();
        assertEquals(1, invoices.size());
    }

    @Test
    public void shouldUpdateInvoice() {
        invoice = invoiceDao.addInvoice(invoice);
        invoice.setName("Jane Smith");
        invoice.setStreet("Main Street");
        invoice.setCity("Jersey City");
        invoice.setState("NJ");
        invoice.setZipcode("99999");
        invoice.setQuantity(2);
        invoice.setSubtotal(new BigDecimal("799.98"));
        invoice.setTax(new BigDecimal("47.99"));
        invoice.setTotal(new BigDecimal("862.96"));
        invoiceDao.updateInvoice(invoice);

        Invoice invoice1 = invoiceDao.getInvoiceById(invoice.getId());
        assertEquals(invoice, invoice1);
    }
}