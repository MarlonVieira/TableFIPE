package br.com.alura.TableFIPE.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record Brand(@JsonAlias("Código") String code, @JsonAlias("Nome") String name)  {
}