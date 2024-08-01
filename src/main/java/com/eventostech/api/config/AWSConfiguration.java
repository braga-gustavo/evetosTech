package com.eventostech.api.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfiguration {

    @Value("${aws.region}")
    private String awsRegion;

    @Bean
    public AmazonS3 createS3Instace(){
        return AmazonS3ClientBuilder.standard().withRegion(awsRegion).build(); /* Cria uma instancia do s3 com as
         credenciais padroes do aws configurados na maquina e a regiao definida no ambiente*/
    }
}
