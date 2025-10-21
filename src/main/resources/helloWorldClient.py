import sys
import grpc

# Die generierten Dateien heißen immer noch `hello_pb2...`, aber ihr Inhalt
# wurde durch den `protoc`-Befehl aktualisiert, um unseren DataWarehouse-Service zu beschreiben.
# Wir geben ihnen hier bessere Namen ("Aliase"), um den Code lesbarer zu machen.
import hello_pb2 as dw_pb2
import hello_pb2_grpc as dw_pb2_grpc


def main():
    # Wir nehmen eine Warehouse-ID vom Kommandozeilen-Argument,
    # oder benutzen einen Standardwert, wenn nichts angegeben wird.
    warehouse_id = sys.argv[1] if len(sys.argv) > 1 else "python-client-007"

    # Baue eine Verbindung zum gRPC-Server auf, der auf localhost:50051 läuft.
    # Das ist derselbe Java-Server, den du vorher gestartet hast.
    with grpc.insecure_channel("localhost:50051") as channel:

        # Erstelle einen "Stub". Das ist das Objekt, das uns erlaubt,
        # die Funktionen des DataWarehouseService aus der Ferne aufzurufen.
        stub = dw_pb2_grpc.DataWarehouseServiceStub(channel)

        # Erstelle die Anfrage-Nachricht. Sie entspricht der `WarehouseRequest`-Message
        # in unserer .proto-Datei.
        request = dw_pb2.WarehouseRequest(warehouseID=warehouse_id)

        # Rufe die Remote-Funktion 'getWarehouseData' auf dem Java-Server auf
        # und warte auf die Antwort.
        response = stub.getWarehouseData(request)

        # Gib die erhaltene Antwort schön formatiert aus.
        print("\n--- Antwort vom Java-Server (empfangen von Python) ---")
        print(response)
        print("------------------------------------------------------\n")

# Standard-Startpunkt für ein Python-Skript
if __name__ == "__main__":
    main()