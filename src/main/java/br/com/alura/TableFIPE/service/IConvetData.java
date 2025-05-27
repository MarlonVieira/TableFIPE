package br.com.alura.TableFIPE.service;

import java.util.List;

public interface IConvetData {
    <T> T getData(String json, Class<T> tClass);

    <T> List<T> getList(String json, Class<T> tClass);
}
