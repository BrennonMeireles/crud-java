package telas;

import dal.mod_conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class tela_user extends javax.swing.JFrame {
    
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public tela_user() {
        initComponents();
        conexao = mod_conexao.conector();
    }
    
    private void consultar(){
    String sql = "SELECT * FROM tb_autenticacao WHERE id=?";
    
    try {
    pst = conexao.prepareStatement(sql);
    pst.setString(1,inpId.getText());
    
    rs = pst.executeQuery();
    if (rs.next()) {
        inpNome.setText(rs.getString(2));
        inpEmail.setText(rs.getString(3));
        inpSenha.setText(rs.getString(4));
    } else {
        JOptionPane.showMessageDialog(null, "USUARIO NÃO CADASTRADO...");
//      nas linhas abaixo limpam os campos do formulario
        inpNome.setText(null);
        inpEmail.setText(null);
        inpSenha.setText(null);
    }   
    }catch (Exception e){
        JOptionPane.showConfirmDialog(null, e);
        }
    }
    private void adicionar(){
    String sql = "INSERT INTO tb_autenticacao (email,senha) VALUES (?,?,?)";
    try {
        pst = conexao.prepareStatement(sql);
        pst.setString(1 ,inpNome.getText());
        pst.setString(2 ,inpEmail.getText());
        pst.setString(3 ,inpSenha.getText());
        
        int adicionado = pst.executeUpdate(); // retorna 1 se estiver correto
        
        if (adicionado > 0) {
            JOptionPane.showMessageDialog(null, "Usuario Cadastrado");
    //      Exibe a msg caso inserido com sucesso
    //      as linhas abaixo irão limpar o formulario
            inpNome.setText(null);
            inpEmail.setText(null);
            inpSenha.setText(null);
            inpNome.setText(null);
        }    
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao adicionar usuário: " + e.getMessage());
    }
    }
    
    private void alterar() {
        String sql = "UPDATE tb_autenticaco SET email = ?, senha = ? WHERE id = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, inpNome.getText());
            pst.setString(2, inpEmail.getText());
            pst.setString(3, inpSenha.getText());
            pst.setString(4, inpId.getText());
            int adicionado = pst.executeUpdate();
            // Retorna 1 se OK
            if (adicionado > 0) {
                JOptionPane.showMessageDialog(null, "USUÁRIO ALTERADO COM SUCESSO");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar usuário: " + e.getMessage());
        }
    }  
    
    private void apagar(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir este usuario?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
        if(confirma == JOptionPane.YES_OPTION){
            String sql = "DELETE FROM tb_autenticacao WHERE id = ?";
            try{
                pst = conexao.prepareStatement(sql);
                pst.setString(1, inpId.getText());
                pst.executeUpdate();
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAdd = new javax.swing.JButton();
        btnVisualizar = new javax.swing.JButton();
        ID = new javax.swing.JLabel();
        btnApagar = new javax.swing.JButton();
        nome = new javax.swing.JLabel();
        btnEdit = new javax.swing.JButton();
        email = new javax.swing.JLabel();
        senha = new javax.swing.JLabel();
        inpId = new javax.swing.JTextField();
        inpNome = new javax.swing.JTextField();
        inpEmail = new javax.swing.JTextField();
        inpSenha = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAdd.setText("Adicionar");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnVisualizar.setText("Visualizar");
        btnVisualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisualizarActionPerformed(evt);
            }
        });

        ID.setText("ID:");

        btnApagar.setText("Apagar");
        btnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarActionPerformed(evt);
            }
        });

        nome.setText("Nome:");

        btnEdit.setText("Editar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        email.setText("Email:");

        senha.setText("Senha");

        inpId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpIdActionPerformed(evt);
            }
        });

        inpNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpNomeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("TELA CADASTRO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addComponent(jLabel1)
                        .addGap(0, 135, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(email)
                            .addComponent(senha)
                            .addComponent(nome))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inpSenha)
                            .addComponent(inpEmail)
                            .addComponent(inpNome)
                            .addComponent(inpId)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAdd)
                            .addComponent(btnVisualizar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                            .addComponent(btnApagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(110, 110, 110))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ID))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(email))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nome))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(senha)
                    .addComponent(inpSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnVisualizar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnApagar)))
                .addContainerGap(66, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnVisualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisualizarActionPerformed
        // TODO add your handling code here:
        consultar();
    }//GEN-LAST:event_btnVisualizarActionPerformed

    private void btnApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApagarActionPerformed
        // TODO add your handling code here:
        apagar();
    }//GEN-LAST:event_btnApagarActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_btnEditActionPerformed

    private void inpIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpIdActionPerformed

    private void inpNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpNomeActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_inpNomeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(tela_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tela_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tela_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tela_user.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tela_user().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ID;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnApagar;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JLabel email;
    private javax.swing.JTextField inpEmail;
    private javax.swing.JTextField inpId;
    private javax.swing.JTextField inpNome;
    private javax.swing.JTextField inpSenha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel nome;
    private javax.swing.JLabel senha;
    // End of variables declaration//GEN-END:variables
}
