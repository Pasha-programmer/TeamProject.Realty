package com.example.Infrastructure.Services.External;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class JacksonStreamService<T> {
    private final ObjectMapper mapper = new ObjectMapper();
    private final JsonFactory factory = mapper.getFactory();

    public Stream<T> streamRealtyJson(File jsonFile, Class<T> theClass) throws IOException {
        JsonParser parser = factory.createParser(jsonFile);

        while (parser.nextToken() != null && parser.nextToken() != JsonToken.START_OBJECT) {
            // Empty loop, to move pointer
        }

        if (parser.currentToken() == JsonToken.START_OBJECT) {
            MappingIterator<T> iterator = mapper.readValues(parser, theClass);

            return StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 0), false).onClose(() -> {
                try {
                    parser.close();
                } catch (IOException e) {
                    throw new RuntimeException("Error while closing JSONParser", e);
                }
            });
        } else {
            System.err.println("No JSON objects found in the File.");
            parser.close();
            return Stream.empty();
        }
    }
}
