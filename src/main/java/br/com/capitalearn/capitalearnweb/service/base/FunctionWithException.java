package br.com.capitalearn.capitalearnweb.service.base;

@FunctionalInterface
public interface FunctionWithException<T, R> {
    R apply(T t) throws Exception;
}
