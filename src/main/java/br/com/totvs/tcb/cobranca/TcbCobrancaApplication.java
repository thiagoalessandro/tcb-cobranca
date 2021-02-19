package br.com.totvs.tcb.cobranca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class TcbCobrancaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TcbCobrancaApplication.class, args);
    }

}
