package com.github.myself_demo.wallet_service;

import com.github.myself_demo.wallet_service.service.WalletServiceProvider;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        final var server = ServerBuilder.forPort(9002)
                .addService(new WalletServiceProvider())
                .build()
                .start();
        server.awaitTermination();
    }
}
