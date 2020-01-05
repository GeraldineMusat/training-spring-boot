package com.ecommerce.microcommerce;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ecommerce.microcommerce.web.controller.ProductController;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MicrocommerceApplicationTests {

	MockMvc mockMvc;
    @Autowired
    protected WebApplicationContext wac;
    @Autowired
    ProductController productController;
    
    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(this.productController).build();// Standalone context
    }
    
    @Test
    public void testChercherProduits() throws Exception {
        // Mocking service
        //when(productController.listeProduits()).thenReturn(product);
        mockMvc.perform(get("/Produits").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[1].nom", is("Aspirateur Robot")));
    }

    @Test
    public void testChercherProduit() throws Exception {
        mockMvc.perform(get("/Produits/1").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
    
    @Test
    public void testChercherProduitERR() throws Exception {
        mockMvc.perform(get("/Produits/18").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(404));
    }
    
    @Test
    public void testCalculerMarges() throws Exception {
        mockMvc.perform(get("/AdminProduits").contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }
    
}
