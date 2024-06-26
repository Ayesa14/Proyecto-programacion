package controller.sistema_pedidos.nuevo_pedido;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import controller.sistema_pedidos.MenuSistemaPedidosController;
import model.sistema_pedidos.MesasModel;
import view.sistema_pedidos.MesasView;

public class MesasNuevoPedidoController {

    private MesasModel model = null;
    private MesasView view = null;

    public MesasNuevoPedidoController() {
        model = new MesasModel();
        generarVentana(model.getNombreMesasDisponibles());
    }

    public void generarVentana(String[] mesasButtonNames) {
        //Creamos la vista(pasamos la lista de nombres de las mesas que esten disponibles)
        view = new MesasView(mesasButtonNames);

        //Eventos de la vista
        view.getBtnAtras().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                destruirVentana();
                new MenuSistemaPedidosController();
            }
        });
        if(mesasButtonNames.length != 0) {
            for(JButton mesaButton : view.getMesasButtons()) {
                mesaButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int idMesa = model.searchMesaIdByNombre(mesaButton.getText());
                        new NuevoPedidoController(idMesa);
                    }
                });
            }
        }
    }

    public void destruirVentana() {
        this.view.dispose();
    }
}