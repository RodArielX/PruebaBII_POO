import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Busqueda extends JFrame{

    private JTextField numcedula;
    private JButton SALIRButton;
    private JButton VOLVERREGISTRARButton;
    private JPanel panel_busqueda;
    private JButton BUSCARButton;

    public Busqueda (){
        super("BUSQUEDA");
        setContentPane(panel_busqueda);
        SALIRButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login ventana_login = new Login();
                ventana_login.iniciar();
            }
        });
        VOLVERREGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registrar ventana_registro = new Registrar();
                ventana_registro.iniciar();
            }
        });
        BUSCARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    visualizar();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public Connection conexion()throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user = "root";
        String password = "";

        return DriverManager.getConnection(url,user,password);
    }

    public void visualizar()throws SQLException {
        String num_cedula = numcedula.getText();
        Connection conec = conexion();
        String sql = "select * from paciente where cedula = ?";
        PreparedStatement pst = conec.prepareStatement(sql);
        pst.setString(1,num_cedula);
        ResultSet rs = pst.executeQuery();

        if (rs.next()){
            String cedu = rs.getString("cedula");
            if (cedu.equals(num_cedula)){

            }


        }
        rs.close();
        pst.close();
    }
    public void iniciar(){
        setVisible(true);
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

