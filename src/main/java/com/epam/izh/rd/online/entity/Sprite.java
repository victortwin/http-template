package com.epam.izh.rd.online.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
class Sprite {
    @JsonProperty("front_default")
    private String imageUrl;
}
