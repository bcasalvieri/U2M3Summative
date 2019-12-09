package com.company.BradCasalvieriU2M3Summative.viewmodel;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class GameViewModel {
    private int id;
    @NotEmpty(message = "Please enter a title.")
    private String title;
    @NotEmpty(message = "Please enter a ESRB rating.")
    private String esrbRating;
    @NotEmpty(message = "Please enter a description.")
    private String description;
    @NotNull(message = "Please enter a price.")
    @DecimalMin(value = "0.0")
    @Digits(integer = 7, fraction = 2)
    private BigDecimal price;
    @NotEmpty(message = "Please enter a studio.")
    private String studio;
    @NotNull(message = "Please enter a quantity.")
    @Min(value = 0, message = "Please enter a valid quantity.")
    private Integer quantity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEsrbRating() {
        return esrbRating;
    }

    public void setEsrbRating(String esrbRating) {
        this.esrbRating = esrbRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameViewModel that = (GameViewModel) o;
        return id == that.id &&
                quantity == that.quantity &&
                Objects.equals(title, that.title) &&
                Objects.equals(esrbRating, that.esrbRating) &&
                Objects.equals(description, that.description) &&
                Objects.equals(price, that.price) &&
                Objects.equals(studio, that.studio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, esrbRating, description, price, studio, quantity);
    }

    @Override
    public String toString() {
        return "GameViewModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", esrbRating='" + esrbRating + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", studio='" + studio + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
