package br.com.alura.TableFIPE.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DataReceived(@JsonAlias("codigo") String code,
                           @JsonAlias("nome") String name)  {

    @Override
    public String toString() {
        return "Code: " + code + "  \t Name: " +name;
    }
}