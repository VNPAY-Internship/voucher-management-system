package com.vnpay.vouchersystem.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendRedemptionRequest(Long customerId, Long voucherId) {
        String topic = "VoucherRedemptionTopic";
        String message = createRedemptionRequestMessage(customerId, voucherId);

        kafkaTemplate.send(topic, message);
    }

    private String createRedemptionRequestMessage(Long customerId, Long voucherId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("customerId", customerId);
        jsonObject.put("voucherId", voucherId);

        return jsonObject.toString();
    }
}
