package com.company.BradCasalvieriU1Capstone.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "invoice_id", length = 11, unique = true, nullable = false)
    private int id;
    @Column(length = 80, nullable = false)
    private String name;
    @Column(name = "street_number", length = 10, nullable = false)
    private int streetNumber;
    @Column(length = 30, nullable = false)
    private String street;
    @Column(length = 30, nullable = false)
    private String city;
    @Column(length = 30, nullable = false)
    private String state;
    @Column(length = 5, nullable = false)
    private String zipcode;
    @Column(name = "item_type", length = 20, nullable = false)
    private String itemType;
    @Column(name = "item_id", length = 11, nullable = false)
    private int itemId;
    @Column(name = "unit_price", precision = 5, scale = 2, nullable = false)
    private BigDecimal unitPrice;
    @Column(length = 11, nullable = false)
    private int quantity;
    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal subtotal;
    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal tax;
    @Column(name = "processing_fee", precision = 8, scale = 2, nullable = false)
    private BigDecimal processingFee;
    @Column(precision = 8, scale = 2, nullable = false)
    private BigDecimal total;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(BigDecimal processingFee) {
        this.processingFee = processingFee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return id == invoice.id &&
                streetNumber == invoice.streetNumber &&
                itemId == invoice.itemId &&
                quantity == invoice.quantity &&
                Objects.equals(name, invoice.name) &&
                Objects.equals(street, invoice.street) &&
                Objects.equals(city, invoice.city) &&
                Objects.equals(state, invoice.state) &&
                Objects.equals(zipcode, invoice.zipcode) &&
                Objects.equals(itemType, invoice.itemType) &&
                Objects.equals(unitPrice, invoice.unitPrice) &&
                Objects.equals(subtotal, invoice.subtotal) &&
                Objects.equals(tax, invoice.tax) &&
                Objects.equals(processingFee, invoice.processingFee) &&
                Objects.equals(total, invoice.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, streetNumber, street, city, state, zipcode, itemType, itemId, unitPrice, quantity, subtotal, tax, processingFee, total);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", streetNumber=" + streetNumber +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemId=" + itemId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", processingFee=" + processingFee +
                ", total=" + total +
                '}';
    }
}
