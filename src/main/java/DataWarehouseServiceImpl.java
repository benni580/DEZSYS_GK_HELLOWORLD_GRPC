
import grpc.datawarehouse.*;
import io.grpc.stub.StreamObserver;

public class DataWarehouseServiceImpl extends DataWarehouseServiceGrpc.DataWarehouseServiceImplBase {

    @Override
    public void getWarehouseData(WarehouseRequest request, StreamObserver<WarehouseData> responseObserver) {
        System.out.println("Anfrage für Warehouse-ID erhalten: " + request.getWarehouseID());

        Product product1 = Product.newBuilder()
                .setProductID("00-443175")
                .setProductName("Bio Orangensaft Sonne")
                .setProductQuantity(2500)
                .build();

        WarehouseData response = WarehouseData.newBuilder()
                .setWarehouseID(request.getWarehouseID())
                .setWarehouseName("Linz Bahnhof (gRPC)")
                .setWarehouseCity("Linz")
                .addProductData(product1)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}