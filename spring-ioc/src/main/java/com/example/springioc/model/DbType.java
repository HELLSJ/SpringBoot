package com.example.springioc.model;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "dbtypes")
@Component
@Data
public class DbType {
    private List<String> name;
    private Map<String, String> map;
}
