package com.epam.izh.rd.online.factory.implementation;

import com.epam.izh.rd.online.factory.ObjectMapperFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperFactoryImplementation implements ObjectMapperFactory {
    @Override
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
