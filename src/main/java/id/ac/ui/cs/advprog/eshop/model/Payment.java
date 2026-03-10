package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;

        if (paymentData.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.paymentData = paymentData;
        }

        if (method.equals("VOUCHER_CODE")) {
            String voucherCode = paymentData.get("voucherCode");
            if (voucherCode != null && voucherCode.length() == 16 && voucherCode.startsWith("ESHOP")) {
                int numDigit = 0;
                for (int i = 0; i < voucherCode.length(); i++) {
                    if (Character.isDigit(voucherCode.charAt(i))) {
                        numDigit++;
                    }
                }
                if (numDigit == 8) {
                    this.status = "SUCCESS";
                } else {
                    this.status = "REJECTED";
                }
            } else {
                this.status = "REJECTED";
            }
        } else if (method.equals("CASH_ON_DELIVERY")) {
            String address = paymentData.get("address");
            String deliveryFee = paymentData.get("deliveryFee");
            if (address != null && !address.trim().isEmpty() && deliveryFee != null && !deliveryFee.trim().isEmpty()) {
                this.status = "SUCCESS";
            } else {
                this.status = "REJECTED";
            }
        } else {
            this.status = "REJECTED";
        }
    }

    public Payment(String id, String method, Map<String, String> paymentData, String status) {
        this(id, method, paymentData);
        this.setStatus(status);
    }

    public void setStatus(String status) {
        if (status.equals("SUCCESS") || status.equals("REJECTED")) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
