package br.com.alura.TableFIPE.service;

public interface IConvetData {
    <T> T getData(String json, Class<T> tClass);
}
