import java.util.ArrayList;
import java.util.List;

public class ZonaDeCarga {

    private List<Pedido> pedidos;

    public ZonaDeCarga() {
        pedidos = new ArrayList<>();
        System.out.println("[Zona de carga inicializada]");
    }

    public synchronized void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);

        System.out.println(
                "Pedido #" + pedido.getId() +
                        " agregado. Destino: " + pedido.getDireccionEntrega()
        );
    }

    public synchronized Pedido retirarPedido() {

        if (pedidos.isEmpty()) {
            return null;
        }

        Pedido pedido = pedidos.remove(0);

        return pedido;
    }

}
