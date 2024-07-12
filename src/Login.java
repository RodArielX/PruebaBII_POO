import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame{
    private JButton INGRESARButton;
    private JTextField user;
    private JTextField contra;
    private JPanel panel_login;

    public Login (){
        super("LOGIN");
        setContentPane(panel_login);
        INGRESARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    validarLogin();
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

    public void validarLogin() throws SQLException {
        String usuario = user.getText();
        String contraseña = contra.getText();
        Connection conecta = conexion();
        String sql = "SELECT * FROM USUARIO WHERE username = ? AND password = ?";
        PreparedStatement pst = conecta.prepareStatement(sql);
        pst.setString(1, usuario);
        pst.setString(2, contraseña);
        ResultSet rs = pst.executeQuery();

        if (rs.next()){
            String user1 = rs.getString("username");
            String contra1 = rs.getString("password");
            if(user1.equals(usuario) && contra1.equals(contraseña)){
                Registrar registrar = new Registrar();
                registrar.iniciar();
                dispose();
            }
        }else{
            JOptionPane.showMessageDialog(null,"ERROR. INTENTE DE NUEVO");
        }
        conecta.close();
        pst.close();

    }

    public void iniciar(){
        setVisible(true);
        setSize(400,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
