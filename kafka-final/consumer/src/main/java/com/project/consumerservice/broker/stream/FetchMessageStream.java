package com.project.consumerservice.broker.stream;

import com.project.consumerservice.broker.message.UserMessage;
import com.project.consumerservice.serde.CustomJsonDeserializer;
import com.project.consumerservice.serde.CustomJsonSerde;
import com.project.consumerservice.serde.CustomJsonSerializer;
import com.project.consumerservice.util.PrintUtil;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FetchMessageStream {

    @Value("${kafka.topic}")
    private String topic;

    @Bean
    public KStream<String, UserMessage>  kstreamPromotionUppercase(StreamsBuilder builder) {
        //For Key by default string serde
        Serde stringSerde = Serdes.String();

        //For UserMessage POJO custom json serde should be used
        CustomJsonSerializer<UserMessage> ser = new CustomJsonSerializer<>();
        CustomJsonDeserializer<UserMessage> dese = new CustomJsonDeserializer<>(
                UserMessage.class);
        CustomJsonSerde userMessageSerde = new CustomJsonSerde(ser, dese);

        //Get the stream
        KStream<String, UserMessage> sourceStream = builder.stream(topic,
                Consumed.with(stringSerde, userMessageSerde));

        //Fetch Message
        sourceStream.print(Printed.<String, UserMessage>toSysOut().withLabel("original Stream"));
        sourceStream.mapValues((ValueMapper<UserMessage, UserMessage>) PrintUtil::printMessage);
        return sourceStream;
    }


}
