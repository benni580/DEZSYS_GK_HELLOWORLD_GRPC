import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HelloWorldClient {

    public static void main(String[] args) {

        String firstname = args.length > 0 ? args[0] : "Max";
        String lastname  = args.length > 1 ? args[1] : "Mustermann";

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        HelloWorldServiceGrpc.HelloWorldServiceBlockingStub stub = HelloWorldServiceGrpc.newBlockingStub(channel);

        Hello.HelloResponse helloResponse = stub.hello(Hello.HelloRequest.newBuilder()
                .setFirstname(firstname)
                .setLastname(lastname)
                .build());
        System.out.println( helloResponse.getText() );
        channel.shutdown();

    }

}
