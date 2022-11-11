package ru.sfu.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * Television Persistence Entity
 * @author Agapchenko V.V.
 */
@Entity
@Table(name="televisions")
public class Television {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id = 0;

    @NotEmpty(message = "Cannot be empty")
    private String model;

    @NotEmpty(message = "Cannot be empty")
    private String producer;

    @Column(name = "production_country")
    private String productionCountry;

    @NotNull(message = "Cannot be empty")
    @Min(value = 160, message = "Screen width cannot be lower 160")
    @Max(value = 7680, message = "Screen width cannot be higher 7680")
    private Integer width;

    @NotNull(message = "Cannot be empty")
    @Min(value = 200, message = "Screen height cannot be lower 200")
    @Max(value = 4800, message = "Screen height cannot be higher 4800")
    private Integer height;

    /**
     * Default constructor
     */
    public Television() {}

    /**
     * Full constructor
     * @param id Identification Number
     * @param model Television model
     * @param producer Television manufacturer
     * @param productionCountry Country of manufacture
     * @param width Screen width in pixels
     * @param height Screen height in pixels
     */
    public Television(
            Integer id,
            String model,
            String producer,
            String productionCountry,
            Integer width,
            Integer height
    ) {
        this.id = id;
        this.model = model;
        this.producer = producer;
        this.productionCountry = productionCountry;
        this.width = width;
        this.height = height;
    }

    /**
     * Identification Number getter
     * @return Identification Number
     */
    public Integer getId() {
        return id;
    }

    /**
     * Television model getter
     * @return Television model
     */
    public String getModel() {
        return model;
    }

    /**
     * Television manufacturer getter
     * @return Television manufacturer
     */
    public String getProducer() {
        return producer;
    }

    /**
     * Country of manufacture getter
     * @return Country of manufacture
     */
    public String getProductionCountry() {
        return productionCountry;
    }

    /**
     * Screen width getter
     * @return Screen width
     */
    public Integer getWidth() {
        return width;
    }

    /**
     * Screen height getter
     * @return Screen height
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * Identification Number setter
     * @param id Identification number
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Television model setter
     * @param model  Television model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Television manufacturer setter
     * @param producer Television manufacturer
     */
    public void setProducer(String producer) {
        this.producer = producer;
    }

    /**
     * Country of manufacture setter
     * @param productionCountry Country of manufacture
     */
    public void setProductionCountry(String productionCountry) {
        this.productionCountry = productionCountry;
    }

    /**
     * Screen height setter
     * @param width Screen height
     */
    public void setWidth(Integer width) {
        this.width = width;
    }

    /**
     * Screen height setter
     * @param height Screen height
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * Represent Television as a String
     * @return String representing Object
     */
    @Override
    public String toString() {
        return String.format(
                """
                ID: %d
                Model: %s
                Producer: %s
                Production Country: %s
                Width: %d
                Height: %d
                """,
                this.id,
                this.model,
                this.producer,
                this.productionCountry,
                this.width,
                this.height
        );
    }
}
