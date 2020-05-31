package com.ecommerce.maha.controller;

import com.google.gson.Gson;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ControllerTest extends BaseIntegrationTest {

    @Test
    public void checkoutApi() throws Exception {

        String watchCodesList[] = { "001", "002", "001", "004", "003" };
        List<String> watchCodes = Arrays.asList(watchCodesList);

        mvc.perform(post("/checkout/").content(new Gson().toJson(watchCodes)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.price", is(360.0)));
    }

    @Test
    public void checkoutApi_whenAllWatchesEachOne() throws Exception {

        String watchCodesList[] = { "001", "002", "003", "004" };
        List<String> watchCodes = Arrays.asList(watchCodesList);

        mvc.perform(post("/checkout/").content(new Gson().toJson(watchCodes)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.price", is(260.0)));
    }

    @Test
    public void checkoutApi_whenOneRolexWatchOnly() throws Exception {

        String watchCodesList[] = { "001" };
        List<String> watchCodes = Arrays.asList(watchCodesList);

        mvc.perform(post("/checkout/").content(new Gson().toJson(watchCodes)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.price", is(100.0)));
    }

    @Test
    public void checkoutApi_whenOneMichaelKorsWatchOnly() throws Exception {

        String watchCodesList[] = { "002" };
        List<String> watchCodes = Arrays.asList(watchCodesList);

        mvc.perform(post("/checkout/").content(new Gson().toJson(watchCodes)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.price", is(80.0)));
    }

    @Test
    public void checkoutApi_whenOneSwatchWatchOnly() throws Exception {

        String watchCodesList[] = { "003" };
        List<String> watchCodes = Arrays.asList(watchCodesList);

        mvc.perform(post("/checkout/").content(new Gson().toJson(watchCodes)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.price", is(50.0)));
    }


    @Test
    public void checkoutApi_whenOneCasioWatchOnly() throws Exception {

        String watchCodesList[] = { "004" };
        List<String> watchCodes = Arrays.asList(watchCodesList);

        mvc.perform(post("/checkout/").content(new Gson().toJson(watchCodes)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.price", is(30.0)));
    }

    @Test
    public void checkoutApi_whenTwoRolexWatchOnly() throws Exception {

        String watchCodesList[] = { "001", "001" };
        List<String> watchCodes = Arrays.asList(watchCodesList);

        mvc.perform(post("/checkout/").content(new Gson().toJson(watchCodes)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.price", is(200.0)));
    }

    @Test
    public void checkoutApi_whenThreeRolexWatchOnly() throws Exception {

        String watchCodesList[] = { "001", "001", "001" };
        List<String> watchCodes = Arrays.asList(watchCodesList);

        mvc.perform(post("/checkout/").content(new Gson().toJson(watchCodes)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.price", is(200.0)));
    }

    @Test
    public void checkoutApi_whenFourRolexWatchOnly() throws Exception {

        String watchCodesList[] = { "001", "001", "001", "001" };
        List<String> watchCodes = Arrays.asList(watchCodesList);

        mvc.perform(post("/checkout/").content(new Gson().toJson(watchCodes)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.price", is(300.0)));
    }

    @Test
    public void checkoutApi_whenTwoMichaelKorsWatchesOnly() throws Exception {

        String watchCodesList[] = { "002", "002" };
        List<String> watchCodes = Arrays.asList(watchCodesList);

        mvc.perform(post("/checkout/").content(new Gson().toJson(watchCodes)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.price", is(120.0)));
    }

    @Test
    public void checkoutApi_whenThreeMichaelKorsWatchesOnly() throws Exception {

        String watchCodesList[] = { "002", "002", "002" };
        List<String> watchCodes = Arrays.asList(watchCodesList);

        mvc.perform(post("/checkout/").content(new Gson().toJson(watchCodes)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.price", is(200.0)));
    }

    @Test
    public void checkoutApi_whenThreeRolexAndTwoMichaelKorsWatches() throws Exception {

        String watchCodesList[] = { "001", "001", "001", "002", "002" };
        List<String> watchCodes = Arrays.asList(watchCodesList);

        mvc.perform(post("/checkout/").content(new Gson().toJson(watchCodes)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.price", is(320.0)));
    }

    @Test
    public void checkoutApi_whenThreeRolexAndFiveMichaelKorsWatches() throws Exception {

        String watchCodesList[] = { "001", "001", "001", "002", "002", "002", "002", "002" };
        List<String> watchCodes = Arrays.asList(watchCodesList);

        mvc.perform(post("/checkout/").content(new Gson().toJson(watchCodes)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.price", is(520.0)));
    }

    @Test
    public void checkoutApi_whenSevenRolexAndFiveMichaelKorsWatchesAndTwoSwatchThreeCasio() throws Exception {

        String watchCodesList[] = { "001", "001", "001", "001", "001", "001", "001", "002", "002", "002", "002", "002",
                "003", "003", "004", "004", "004" };
        List<String> watchCodes = Arrays.asList(watchCodesList);

        mvc.perform(post("/checkout/").content(new Gson().toJson(watchCodes)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.price", is(1010.0)));
    }

}
