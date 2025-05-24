package br.com.capitalearn.capitalearnweb.service.base;

import java.sql.Connection;

@FunctionalInterface
public interface ConnectionConsumer {
    void accept(Connection conn) throws Exception;
}
