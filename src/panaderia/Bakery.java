package parcial1remastserizado;



import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Bakery extends JFrame {

    Manejador m = new Manejador();
    Manejador2 mc = new Manejador2();
    Manejador3 mn = new Manejador3();

    Container contenedor;

    public Bakery() {
        setTitle("Bakery");
        setSize(900, 600);
        setLocationRelativeTo(null);
        iniciarPanel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JButton añadir, salir, limpiar, generarPrecio;
    private JComboBox box, boxInventario;
    private JTextField texto;
    private JTextArea area;
    private JLabel etiqueta, etiqueta2;
    private JPanel panel, panel2;
    private int cantidad = 0;
    private int precio = 0;
    private int precioFinal = 0;

    public String[] Productos(String estado) {
        String[] productosx = new String[3];

        switch (estado) {
            case "FRIA":
                productosx[0] = "Pony Malta";
                productosx[1] = "Jugo de Mango";
                productosx[2] = "Milo";
                break;
            case "CALIENTE":
                productosx[0] = "Cafe";
                productosx[1] = "Chocolate";
                productosx[2] = "Tinto";
                break;
            case "ENERGIZANTE":
                productosx[0] = "GatoRade";
                productosx[1] = "Vive 100";
                productosx[2] = "Monster";
                break;
            case "SALADOS":
                productosx[0] = "Empanada";
                productosx[1] = "Chorizo";
                productosx[2] = "Papa Aborrajada";
                break;
            case "DULCES":
                productosx[0] = "Pastel de Fresa";
                productosx[1] = "Rosquillas";
                productosx[2] = "Pan Agridulce";
                break;

        }

        return productosx;
    }
    //////////////////////////////////////////////

    // Iniciar Componentes
    public void iniciarPanel() {
        paneles();
        Botones();
        TextField();
        TextArea();
        etiquetas();
        ComboBox();
    }

    // Metodo de Paneles
    private void paneles() {
        panel = new JPanel(); // Panel creado
        panel.setLayout(null);
        this.getContentPane().add(panel);// Agrego el panel a la ventana
        panel.setBackground(Color.WHITE);

        panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBackground(Color.black);
        panel2.setBounds(0, 0, 250, 600);
        panel.add(panel2);
    }

    // Metodo de etiquetas
    private void etiquetas() {
        etiqueta = new JLabel("ESTADO DE CUENTA");
        etiqueta.setBounds(490, -20, 300, 300);
        etiqueta.setForeground(Color.BLACK);

        etiqueta.setFont(new Font("arial", Font.BOLD, 15));
        panel.add(etiqueta);

        etiqueta2 = new JLabel("PANADERIA");
        etiqueta2.setBounds(50, -100, 300, 300);
        etiqueta2.setFont(new Font("arial", Font.BOLD, 15));
        etiqueta2.setForeground(Color.red);
        panel2.add(etiqueta2);

    }

    private void Botones() {
        añadir = new JButton("Añadir");
        añadir.addActionListener(m);
        añadir.addMouseListener(mc);
        añadir.setBounds(50, 350, 150, 30);

        añadir.setEnabled(false);
        panel2.add(añadir);

        limpiar = new JButton("Limpiar");
        limpiar.addActionListener(m);
        limpiar.setBounds(50, 400, 150, 30);
        panel2.add(limpiar);

        salir = new JButton("Salir");
        salir.addActionListener(m);
        salir.setBounds(50, 500, 150, 30);
        panel2.add(salir);

        generarPrecio = new JButton("Generar Factura");
        generarPrecio.addActionListener(m);
        generarPrecio.setBounds(50, 450, 150, 30);
        panel2.add(generarPrecio);

    }

    private void TextField() {
        texto = new JTextField();
        texto.setBounds(50, 300, 150, 20);
        texto.setText("Cantidad...");
        panel2.add(texto);

    }

    private void TextArea() {
        area = new JTextArea("");
        area.setBackground(Color.BLACK);
        area.setForeground(Color.YELLOW);
        area.setBounds(380, 150, 400, 250);
        panel.add(area);
    }

    private void ComboBox() {

        String[] menu = { "MENU", "FRIA", "CALIENTE", "ENERGIZANTE", "DULCES", "SALADOS" };
        box = new JComboBox(menu);
        box.addItemListener(mn);
        box.setBounds(50, 100, 150, 20);
        panel2.add(box);

        boxInventario = new JComboBox();
        boxInventario.setBounds(50, 200, 150, 20);
        box.addItemListener(mn);
        panel2.add(boxInventario);
    }

    public void productInfo() {
        if (cantidad <= 5) {
            area.append("Producto: " + boxInventario.getSelectedItem() + " " + "Precio: " + precio
                    + " " + "Cantidad: " + cantidad + "\n");
        } else {
            JOptionPane.showMessageDialog(null, "No se pueden agregar mas de 5 productos por compra!");
        }
    }

    public class Manejador implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            switch (e.getActionCommand()) {
                case "Salir":
                    System.exit(0);
                    break;

                case "Limpiar":
                    area.setText("");
                    break;

                case "Generar Factura":
                    area.append("---> Factura <---" + "\n" +
                            "ᴘʀᴇᴄɪᴏ ᴛᴏᴛᴀʟ: " + precioFinal);
                    break;
            }

            if (e.getSource() == añadir) {

                cantidad = Integer.parseInt(texto.getText());

                switch (boxInventario.getSelectedItem().toString()) {
                    case "Pony Malta":
                        precio = (cantidad * 1500);
                        precioFinal += precio;
                        productInfo();
                        break;
                    case "Jugo de Mango":
                        precio = (cantidad * 1500);
                        precioFinal += precio;
                        productInfo();
                        break;
                    case "Milo":
                        precio = (cantidad * 2000);
                        precioFinal += precio;
                        productInfo();
                        break;
                    case "Cafe":
                        precio = (cantidad * precio);
                        precioFinal += precio;
                        productInfo();
                        break;
                    case "Chocolate":
                        precio = (cantidad * 2000);
                        precioFinal += precio;
                        productInfo();
                        break;
                    case "Tinto":
                        precio = (cantidad * 1500);
                        precioFinal += precio;
                        productInfo();
                        break;
                    case "GatoRade":
                        precio = (cantidad * 2200);
                        precioFinal += precio;
                        productInfo();
                        break;
                    case "Vive 100":
                        precio = (cantidad * 1300);
                        precioFinal += precio;
                        productInfo();
                        break;
                    case "Monster":
                        precio = (cantidad * 1300);
                        precioFinal += precio;
                        productInfo();
                        break;
                    case "Empanada":
                        precio = (cantidad * 1200);
                        precioFinal += precio;
                        productInfo();
                        break;
                    case "Chorizo":
                        precio = (cantidad * 1300);
                        precioFinal += precio;
                        productInfo();
                        break;
                    case "Papa Aborrajada":
                        precio = (cantidad * 2500);
                        precioFinal += precio;
                        productInfo();
                        break;
                    case "Pastel de Fresa":
                        precio = (cantidad * 25000);
                        precioFinal += precio;
                        productInfo();
                        break;
                    case "Rosquillas":
                        precio = (cantidad * 3500);
                        precioFinal += precio;
                        productInfo();
                        break;
                    case "Pan Agridulce":
                        precio = (cantidad * 500);
                        precioFinal += precio;
                        productInfo();
                        break;
                }
            }
        }
    }

    public class Manejador2 implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }

    public class Manejador3 implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (box.getSelectedIndex() > 0) {
                    boxInventario.setModel(new DefaultComboBoxModel(Productos(box.getSelectedItem().toString())));
                }
                if (box.getSelectedIndex() > 0) {
                    añadir.setEnabled(true);
                }
            }

        }

    }
}
