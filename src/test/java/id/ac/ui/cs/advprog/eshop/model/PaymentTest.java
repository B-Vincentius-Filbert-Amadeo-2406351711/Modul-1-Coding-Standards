package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
        this.paymentData.put("voucherCode", "ESHOP1234ABC5678");
    }

    @Test
    void testCreatePaymentEmptyPaymentData() {
        this.paymentData.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "", this.paymentData);
        });
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", this.paymentData);
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentRejectedStatus() {
        this.paymentData.put("voucherCode", "invalid");
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", this.paymentData);
        assertEquals("REJECTED", payment.getStatus());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentWithStatus() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", this.paymentData, "SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentWithInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", this.paymentData, "MEOW");
        });
    }


    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", this.paymentData);
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "VOUCHER_CODE", this.paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("MEOW"));
    }

    @Test
    void testCreatePaymentCashOnDeliverySuccessStatus() {
        this.paymentData.clear();
        this.paymentData.put("address", "Jalan Raya");
        this.paymentData.put("deliveryFee", "10000");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "CASH_ON_DELIVERY", this.paymentData);
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("CASH_ON_DELIVERY", payment.getMethod());
        assertEquals(this.paymentData, payment.getPaymentData());
    }

    @Test
    void testCreatePaymentCashOnDeliveryRejectedStatus() {
        this.paymentData.clear();
        this.paymentData.put("address", "");
        this.paymentData.put("deliveryFee", "10000");

        Payment payment = new Payment("13652556-012a-4c07-b546-54eb1396d79b", "CASH_ON_DELIVERY", this.paymentData);
        assertEquals("REJECTED", payment.getStatus());
        assertEquals("CASH_ON_DELIVERY", payment.getMethod());
        assertEquals(this.paymentData, payment.getPaymentData());
    }

}
