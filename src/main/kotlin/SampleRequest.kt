package org.example

import com.fasterxml.jackson.annotation.JsonProperty

data class SampleRequest (
    @JsonProperty("foo") val foo: String,
    @JsonProperty("bar") val bar: String
)