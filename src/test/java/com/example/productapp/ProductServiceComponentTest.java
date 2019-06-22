package com.example.productapp;

import com.example.productapp.model.ProductRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductServiceComponentTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testGetProducts() throws Exception {
        mvc.perform(MockMvcRequestBuilders
        .get("/product/product")
        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").exists())
                .andExpect(jsonPath("$[*].name").exists())
                .andExpect(jsonPath("$[*].currentPrice").exists())
                .andExpect(jsonPath("$[*].lastUpdate").exists());
    }

    @Test
    public void testAddProduct() throws Exception{
        mvc.perform(MockMvcRequestBuilders.post("/product/product")
        .content(asJsonString(ProductRequest.builder().name("cookies").currentPrice(2.56f).build()))
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("cookies"))
                .andExpect(jsonPath("$.currentPrice").value(2.56f))
                .andExpect(jsonPath("$.lastUpdate").exists());
    }

    @Test
    public void testAddProductNameBlank() throws Exception {
        mvc.perform(MockMvcRequestBuilders
        .post("/product/product").content(asJsonString(ProductRequest.builder().name("").currentPrice(2.11f).build()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddProductCurrentPriceNegative() throws Exception {
        mvc.perform(MockMvcRequestBuilders
                .post("/product/product").content(asJsonString(ProductRequest.builder().name("cookies").currentPrice(-2.11f).build()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
