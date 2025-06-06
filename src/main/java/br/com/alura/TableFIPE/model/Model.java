package br.com.alura.TableFIPE.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Model(@JsonAlias ("modelos") List<DataReceived> vehicleModels) {
}
