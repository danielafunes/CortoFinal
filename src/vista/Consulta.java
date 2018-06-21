package vista;

import dao.FiltroDao;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Filtro;


/**
 *
 * @author LN710Q
 */
public class Consulta extends JFrame{
    
    public JLabel lblAFP, lblnombres,lblapellidos,lbledad, lblprofesion,lblestado;
    public JTextField nombres, apellidos, edad,AFP;
    public JComboBox profesion,estado;
    
    ButtonGroup clasificacion=new ButtonGroup();
    ButtonGroup existencia=new ButtonGroup();
    public JRadioButton si;
    public JRadioButton no;
    public JRadioButton ingeniero;
    public JRadioButton arquitecto;
    public JRadioButton medico;
    public JRadioButton licenciado;
    public JRadioButton tecnico;
   
    public JTable resultados;
    
    public JPanel table;
    
    public JButton   insertar, actualizar,buscar,vaciar, eliminar;
    private static final int ANCHOC=130, ALTOC=30;
    
    DefaultTableModel tm;
    
    public Consulta(){
        super("Inscripsiones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        agregarLabels();
        formulario();
        llenarTabla();
        Container container =getContentPane();
        container.add(lblnombres);
        container.add(lblapellidos);
        container.add(lbledad);
        container.add(lblestado);
        container.add(lblAFP);
        container.add(nombres);
        container.add(apellidos);
        container.add(edad);
        container.add(AFP);
        container.add(si);
        container.add(no);
        container.add(ingeniero);
        container.add(arquitecto);
        container.add(medico);
        container.add(licenciado);
        container.add(tecnico);
        container.add(buscar);
        container.add(insertar);
        container.add(actualizar);
        container.add(eliminar);
        container.add(vaciar);
        container.add(table);
        setSize(600,600);
        eventos();
    }

    private void agregarLabels() {
        lblAFP = new JLabel("AFP");
        lblnombres = new JLabel("nombres");
        lblapellidos = new JLabel("apellidos");
        lbledad = new JLabel("edad");
        lblAFP.setBounds(10,10,ANCHOC, ALTOC);
        lblnombres.setBounds(300,100,ANCHOC, ALTOC);
        lblapellidos.setBounds(10,60,ANCHOC, ALTOC);
        lbledad.setBounds(10, 100, ANCHOC, ALTOC);
        lblprofesion.setBounds(300, 10, ANCHOC, ALTOC);
    }

    private void formulario() {
        nombres= new JTextField();
        profesion = new JComboBox();
        apellidos = new JTextField();
        edad = new JTextField();
        estado = new JComboBox();
        si=new JRadioButton("si",true);
        no=new JRadioButton("no");
        buscar=new JButton("Buscar");
        insertar=new JButton("Insertar");
        eliminar=new JButton("Eliminar");
        actualizar=new JButton("Actualizar");
        vaciar=new JButton("vaciar");
        
        table=new JPanel();
        
        
        profesion = new ButtonGroup();
        profesion.add(ingeniero);
        profesion.add(arquitecto);
        profesion.add(medico);
        profesion.add(licenciado);
        profesion.add(tecnico);
        
        
        existencia = new ButtonGroup();
        existencia.add(si);
        existencia.add(no);
        
        nombres.setBounds(100, 10, ANCHOC, ALTOC);
        insertar.setBounds(10, 210, ANCHOC, ALTOC);
        eliminar.setBounds(150, 210, ANCHOC, ALTOC);
        buscar.setBounds(440, 210, ANCHOC, ALTOC);
        actualizar.setBounds(300, 210, ANCHOC, ALTOC);
        vaciar.setBounds(4500, 210, ANCHOC, ALTOC);
        apellidos.setBounds(100, 60, ANCHOC, ALTOC);
        edad.setBounds(100, 100, ANCHOC, ALTOC);
        AFP.setBounds(350, 100, ANCHOC, ALTOC);
        //G.setBounds(WIDTH, WIDTH, WIDTH, HEIGHT);
        resultados=new JTable();
        table.setBounds(10,250,500,200);
        table.add(new JScrollPane(resultados));
    }

    private void llenarTabla() {
        tm=new DefaultTableModel(){
            public Class<?> getColumnClass(int column){
                switch(column){
                    case 0:
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        tm.addColumn("AFP");
        tm.addColumn("nombres");
        tm.addColumn("apelldios");
        tm.addColumn("edad");
        tm.addColumn("estado");
        tm.addColumn("preofesion");
        
        
        FiltroDao fd =new FiltroDao();
        ArrayList<Filtro> filtros= fd.readAll();
        
        for(Filtro fil: filtros){
            tm.addRow(new Object[]{fil.getAFP(),fil.getNombres(),fil.getApellidos(),fil.getEdad(), fil.getProfesion(), fil.getEstado()});
        
    }
        resultados.setModel(tm);
    }

    private void eventos() {
        insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd =new FiltroDao();
                Filtro f=new Filtro(AFP.getText(), profesion.getSelectedItem().toString(), Integer.parseInt(apellidos.getText()), estado.getSelection().getActionCommand(),true);
                if(no.isSelected()){
                    f.setExistenciap(false);
                }
                if(fd.create(f)){
                    JOptionPane.showMessageDialog(null, "Filtro registrado con exito.");
                    limpiarCampos();
                    llenarTabla();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de crear el filtro.");
                }
            }
        });
        actualizar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd= new FiltroDao();
                Filtro f=new Filtro(AFP.getText(), profesion.getSelectedItem().toString(), Integer.parseInt(apellidos.getText()), estado.getSelection().getActionCommand(),true);
                if(no.isSelected()){
                    f.setExistenciap(false);
                }
                if(fd.create(f)){
                    JOptionPane.showMessageDialog(null, "Filtro modificado con exito.");
                    limpiarCampos();
                    llenarTabla();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de modificar el filtro.");
                }
            }
            
        });
        eliminar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd= new FiltroDao();
                if(fd.delete(nombres.getText())){
                    JOptionPane.showMessageDialog(null, "Filtro eliminado con exito.");
                    limpiarCampos();
                    llenarTabla();
                }
                else{
                    JOptionPane.showMessageDialog(null, "Ocurrio un problema al momento de eliminar el filtro.");
                }
            }
        });
        buscar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd= new FiltroDao();
                Filtro f=fd.read(nombres.getText());
                if(f==null){
                    JOptionPane.showMessageDialog(null, "El filtro buscado no se ha encontrado");
                }
                else{
                    nombres.setText(f.getNombre());////////////////////////////
                    profesion.setSelectedItem(f.getProfesion());
                    apellidos.setText(Integer.toString(f.getStock()));
                    if(f.getExistenciap()){
                        si.setSelected(true);
                    }
                    else{
                        no.setSelected(true);
                    }
                }
            }
            
        });
        vaciar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        }); 
    }
    public void limpiarCampos(){
        nombres.setText("");
        profesion.setSelectedItem("");
        apellidos.setText("");
    }
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable(){
            @Override
            public void run() {
                new Consulta().setVisible(true);
            }
        });
    }
    }
