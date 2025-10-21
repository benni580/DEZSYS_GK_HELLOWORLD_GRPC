import grpc.datawarehouse.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HelloWorldClient {
    public static void main(String[] args) {
        String warehouseId = args.length > 0 ? args[0] : "001-DEFAULT";

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        DataWarehouseServiceGrpc.DataWarehouseServiceBlockingStub stub =
                DataWarehouseServiceGrpc.newBlockingStub(channel);

        WarehouseRequest request = WarehouseRequest.newBuilder()
                .setWarehouseID(warehouseId)
                .build();

        WarehouseData response = stub.getWarehouseData(request);

        System.out.println("-- Antwort vom Server --");
        System.out.println(response);

        channel.shutdown();
    }
}