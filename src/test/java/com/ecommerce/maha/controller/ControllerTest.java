package com.ecommerce.maha.controller;

import com.ecommerce.maha.core.entity.Discount;
import com.ecommerce.maha.core.entity.Watch;
import com.ecommerce.maha.data.repository.WatchRepository;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ControllerTest extends BaseIntegrationTest {

    @MockBean
    private WatchRepository watchRepository;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);

        String watchCodesList[] = { "003", "004" };
        List<String> watchCodes = Arrays.asList(watchCodesList);
        Mockito.when(watchRepository.findByWatchCodeIn(new HashSet<>(watchCodes))).thenReturn(getWatches());

    }

    @Test
    public void checkout() throws Exception {

        String watchCodesList[] = { "003", "004" };
        List<String> watchCodes = Arrays.asList(watchCodesList);

        mvc.perform(post("/checkout/").content(new Gson().toJson(watchCodes)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.price", is(80)));
    }

    private List<Watch> getWatches() {

        List<Watch> watches = new ArrayList<>();

        Watch swatch = getWatch(3, "003", "Swatch", new BigDecimal(50));
        Discount sDiscount = Discount.builder().id(1).minimumQuantityRequired(3).discountedValue(BigDecimal.TEN)
                .watch(swatch).build();
        swatch.setDiscount(sDiscount);

        Watch casio = getWatch(4, "004", "Casio", new BigDecimal(30));
        Discount cDiscount = Discount.builder().id(1).minimumQuantityRequired(3).discountedValue(BigDecimal.TEN)
                .watch(casio).build();
        casio.setDiscount(cDiscount);

        watches.add(swatch);
        watches.add(casio);

        return watches;
    }

    private Watch getWatch(Integer id, String watchCode, String watchName, BigDecimal unitPrice) {

        return new Watch(id, watchCode, watchName, unitPrice, null);
    }
}
