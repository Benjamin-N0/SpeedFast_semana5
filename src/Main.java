import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ZonaDeCarga carga = new ZonaDeCarga();

        carga.agregarPedido(new Pedido(1, "direccion 1"));
        carga.agregarPedido(new Pedido(2,"direccion 2"));
        carga.agregarPedido(new Pedido(3,"direccion 3"));
        carga.agregarPedido(new Pedido(4,"direccion 4"));
        carga.agregarPedido(new Pedido(5,"direccion 5"));

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.execute(new Repartidor("Pepe Grillo", carga));
        executor.execute(new Repartidor("Juan Carlos Bodoque", carga));
        executor.execute(new Repartidor("Tulio Trivinio", carga));
        executor.shutdown();

        try {
            boolean finalizaron = executor.awaitTermination(30, TimeUnit.SECONDS);
            if (finalizaron) {
                System.out.println("TODOS LOS PEDIDOS HAN SIDO ENTREGADOS CORRECTAMENTE");
            }else{
                System.out.println("[MAIN] Se agoto el tiempo de espera");
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            executor.shutdownNow();
        }


    }
}