import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class HelloWorldServer {

    private static final int PORT = 50051;
    private Server server;

    public void start() throws IOException {
        server = ServerBuilder.forPort(PORT)
                .addService(new DataWarehouseServiceImpl())
                .build()
                .start();
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        HelloWorldServer server = new HelloWorldServer();
        System.out.println("DataWarehouse gRPC Service is running!");
        server.start();
        server.blockUntilShutdown();
    }
}