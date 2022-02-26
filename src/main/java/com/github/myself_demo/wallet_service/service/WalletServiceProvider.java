package com.github.myself_demo.wallet_service.service;

import com.github.myself_demo.wallet_service_api.pb.Common;
import com.github.myself_demo.wallet_service_api.pb.Model;
import com.github.myself_demo.wallet_service_api.pb.Service;
import com.github.myself_demo.wallet_service_api.pb.WalletServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

public class WalletServiceProvider extends WalletServiceGrpc.WalletServiceImplBase {
    @Override
    public void queryWallet(Service.QueryWalletRequest request, StreamObserver<Service.QueryWalletResult> responseObserver) {
        if (!("888888".equals(request.getUserId()) && "2".equals(request.getCoinTypeId()))) {
            var result = Service.QueryWalletResult.newBuilder()
                    .setHandleResult(Common.HandleResult.newBuilder().setCode(1).setMessage("钱包不存在").build())
                    .build();
            responseObserver.onNext(result);
        } else {
            var result = Service.QueryWalletResult.newBuilder()
                    .setHandleResult(Common.HandleResult.newBuilder().setCode(0).build())
                    .setWallet(Model.Wallet.newBuilder()
                            .setUserId(request.getUserId())
                            .setWalletId(UUID.randomUUID().toString().replaceAll("-", "").toLowerCase())
                            .setCoinTypeId(request.getCoinTypeId())
                            .setAvailableBalance(800)
                            .setFreezeBalance(0)
                            .setIsLocking(false)
                            .build())
                    .build();
            responseObserver.onNext(result);
        }
        responseObserver.onCompleted();
    }
}
