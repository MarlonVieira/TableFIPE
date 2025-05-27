package br.com.alura.TableFIPE.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Vehicle(@JsonAlias("Valor") String value,
                      @JsonAlias("Marca") String brand,
                      @JsonAlias("Modelo") String model,
                      @JsonAlias("AnoModelo") int year,
                      @JsonAlias("Combustivel") String fuel) {

    @Override
    public String toString() {
        return String.format("""
                Value: %s
                Brand: %s
                Model: %s
                Year: %d
                Fuel: %s
                """, value, brand, model, year, fuel);
    }
}