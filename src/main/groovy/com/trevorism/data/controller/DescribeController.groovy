package com.trevorism.data.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag

@Controller("/describe")
class DescribeController {

    def ops = ["list","create","read","update","delete","search"]

    @Tag(name = "Describe Operations")
    @Operation(summary = "Perform a describe data operation")
    @Post(value = "/", produces = MediaType.APPLICATION_JSON, consumes = MediaType.APPLICATION_JSON)
    def operate(@Body Map query) {
        ops
    }

    @Tag(name = "Describe Operations")
    @Operation(summary = "Get a description of the performable data actions")
    @Get(value = "{id}", produces = MediaType.APPLICATION_JSON)
    def operateById(String id) {
        ops
    }

    @Tag(name = "Describe Operations")
    @Operation(summary = "Get a description of the performable data actions")
    @Get(value = "/", produces = MediaType.APPLICATION_JSON)
    def describe() {
        ops
    }
}
