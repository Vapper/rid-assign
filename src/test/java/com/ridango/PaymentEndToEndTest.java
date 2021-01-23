package com.ridango;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ridango.payment.IncomingPayment;

@SpringBootTest(classes = PaymentApplication.class)
@AutoConfigureMockMvc
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class PaymentEndToEndTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Sql("test-data.sql")
    public void testCorrectPayment() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/payment").content(asJsonString(new IncomingPayment(0L, 1L, "10.00")))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

        MvcResult result_accounts = this.mockMvc.perform(MockMvcRequestBuilders.get("/accounts")).andReturn();
        MvcResult result_payments = this.mockMvc.perform(MockMvcRequestBuilders.get("/payments")).andReturn();
        assertEquals(
                "[{\"id\":0,\"name\":\"karl\",\"balance\":4000},{\"id\":1,\"name\":\"toomas\",\"balance\":16000},{\"id\":2,\"name\":\"mart\",\"balance\":100000}]",
                result_accounts.getResponse().getContentAsString());
        assertEquals("[{\"id\":1,\"senderAccountId\":0,\"receiverAccountId\":1}]", result_payments.getResponse().getContentAsString());

    }

    @Test
    @Sql("test-data.sql")
    public void testPaymentWithNegativeAmount() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/payment")
                        .content(asJsonString(new IncomingPayment(0L, 1L, "-10.00")))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) MockMvcResultMatchers.content().string("Payment amount must be above 0"));

        MvcResult result_payments = this.mockMvc.perform(MockMvcRequestBuilders.get("/payments")).andReturn();
        MvcResult result_accounts = this.mockMvc.perform(MockMvcRequestBuilders.get("/accounts")).andReturn();
        assertEquals("[]", result_payments.getResponse().getContentAsString());
        assertEquals(
                "[{\"id\":0,\"name\":\"karl\",\"balance\":5000},{\"id\":1,\"name\":\"toomas\",\"balance\":15000},{\"id\":2,\"name\":\"mart\",\"balance\":100000}]",
                result_accounts.getResponse().getContentAsString());

    }

    @Test
    @Sql("test-data.sql")
    public void testPaymentWithInsufficientFunds() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/payment")
                        .content(asJsonString(new IncomingPayment(0L, 1L, "100.00")))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) MockMvcResultMatchers.content().string("Not enough money on account"));

                MvcResult result_payments = this.mockMvc.perform(MockMvcRequestBuilders.get("/payments")).andReturn();
                MvcResult result_accounts = this.mockMvc.perform(MockMvcRequestBuilders.get("/accounts")).andReturn();
                assertEquals("[]", result_payments.getResponse().getContentAsString());
                assertEquals(
                        "[{\"id\":0,\"name\":\"karl\",\"balance\":5000},{\"id\":1,\"name\":\"toomas\",\"balance\":15000},{\"id\":2,\"name\":\"mart\",\"balance\":100000}]",
                        result_accounts.getResponse().getContentAsString());
    }

    @Test
    @Sql("test-data.sql")
    public void testPaymentWithMissingAccount() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/payment")
                        .content(asJsonString(new IncomingPayment(0L, 3L, "10.00")))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) MockMvcResultMatchers.content().string("Account is missing or incorrectly formatted. Must be numeric Id"));

                MvcResult result_payments = this.mockMvc.perform(MockMvcRequestBuilders.get("/payments")).andReturn();
                MvcResult result_accounts = this.mockMvc.perform(MockMvcRequestBuilders.get("/accounts")).andReturn();
                assertEquals("[]", result_payments.getResponse().getContentAsString());
                assertEquals(
                        "[{\"id\":0,\"name\":\"karl\",\"balance\":5000},{\"id\":1,\"name\":\"toomas\",\"balance\":15000},{\"id\":2,\"name\":\"mart\",\"balance\":100000}]",
                        result_accounts.getResponse().getContentAsString());
    }

    @Test
    @Sql("test-data.sql")
    public void testPaymentWronglyFormattedAmount() throws Exception {
        this.mockMvc
                .perform(MockMvcRequestBuilders.post("/payment")
                        .content(asJsonString(new IncomingPayment(0L, 1L, "10.0")))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect((ResultMatcher) MockMvcResultMatchers.content().string("Account is missing or incorrectly formatted. Must be numeric Id"));

                MvcResult result_payments = this.mockMvc.perform(MockMvcRequestBuilders.get("/payments")).andReturn();
                MvcResult result_accounts = this.mockMvc.perform(MockMvcRequestBuilders.get("/accounts")).andReturn();
                assertEquals("[]", result_payments.getResponse().getContentAsString());
                assertEquals(
                        "[{\"id\":0,\"name\":\"karl\",\"balance\":5000},{\"id\":1,\"name\":\"toomas\",\"balance\":15000},{\"id\":2,\"name\":\"mart\",\"balance\":100000}]",
                        result_accounts.getResponse().getContentAsString());
    }


    //Helper
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
