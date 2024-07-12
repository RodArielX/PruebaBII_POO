import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Registrar extends JFrame{
    private JTextField historial;
    private JTextField nom;
    private JTextField apel;
    private JTextField cel;
    private JTextField años;
    private JTextField enfer_descrip;
    private JPanel panel_registrar;
    private JButton BUSQUEDAButton;
    private JButton REGISTRARButton;
    private JTextField ced;
    private JLabel cedu;

    public Registrar (){
        super("REGISTRO");
        setContentPane(panel_registrar);
        REGISTRARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Registro();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        BUSQUEDAButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Busqueda ventana_busqueda = new Busqueda();
                ventana_busqueda.iniciar();
                dispose();
            }
        });
    }

    public Connection conexion()throws SQLException {
        String url = "jdbc:mysql://localhost:3306/sistema_hospitalario";
        String user = "root";
        String password = "";

        return DriverManager.getConnection(url,user,password);
    }

    public void Registro()throws SQLException {
        String histo = historial.getText();
        String nombre = nom.getText();
        String apellido = apel.getText();
        String telefono = cel.getText();
        String edad = años.getText();
        String enfermedad = enfer_descrip.getText();
        String cedula = ced.getText();

        Connection conect = conexion();
        String sql = "INSERT INTO paciente(cedula,n_historial_clinico, nombre, apellido, telefono, edad, descripcion_enfermedad) values(?,?,?,?,?,?)";

        PreparedStatement pst = conect.prepareStatement(sql);
        pst.setInt(1, Integer.parseInt(cedula));
        pst.setString(2,histo);
        pst.setString(3,nombre);
        pst.setString(4,apellido);
        pst.setInt(5,Integer.parseInt(telefono));
        pst.setInt(6,Integer.parseInt(edad));
        pst.setString(7,enfermedad);

        int rows = pst.executeUpdate();
        if(rows > 0){
            JOptionPane.showMessageDialog(null,"Registro insertado correctamente");
        }
        pst.close();
        conect.close();
    }

    public void iniciar(){
        setVisible(true);
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
