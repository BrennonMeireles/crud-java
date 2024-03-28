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
    String sql = "SELECT * FROM tb_dados WHERE email=?";
    
    try {
    pst = conexao.prepareStatement(sql);
    pst.setString(1,inpEmail.getText());
    
    rs = pst.executeQuery();
    if (rs.next()) {
        inpnomes.setText (rs.getString(2));
        inpDdd.setText   (rs.getString(3));
        inpTel.setText   (rs.getString(4));
        inpEmail.setText (rs.getString(5));
        inpNascimento.setText(rs.getString(6));
        inpCpf.setText   (rs.getString(7));
        inpEnd.setText   (rs.getString(8));
        inpCidade.setText(rs.getString(9));
        inpEstado.setText(rs.getString(10));
    } else {
        JOptionPane.showMessageDialog(null, "USUARIO NÃO CADASTRADO...");
//      nas linhas abaixo limpam os campos do formulario
        inpTel.setText   (null);
        inpnomes.setText (null);
        inpCidade.setText(null);
                
        inpnomes.setText (null);
        inpDdd.setText   (null);
        inpTel.setText   (null);
        inpEmail.setText (null);
        inpNascimento.setText(null);
        inpCpf.setText   (null);
        inpEnd.setText   (null);
        inpCidade.setText(null);
        inpEstado.setText(null);
    }   
    }catch (Exception e){
        JOptionPane.showConfirmDialog(null, e);
        }
    }
    private void adicionar(){
    // Validação do CPF
    String cpf = inpCpf.getText();
    boolean cpfValido = CPFValidator.validarCPF(cpf);
    
    if (!cpfValido) {
        JOptionPane.showMessageDialog(null, "CPF inválido. Por favor, insira um CPF válido.");
        return;
    }

    String sql = "INSERT INTO tb_dados (nome, DDD, celular, email, dataNascimento, cpf, endereco, cidade, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try {
        pst = conexao.prepareStatement(sql);
        pst.setString(1, inpnomes.getText());
        pst.setString(2, inpDdd.getText());
        pst.setString(3, inpTel.getText());
        pst.setString(4, inpEmail.getText());
        pst.setString(5, inpNascimento.getText());
        pst.setString(6, inpCpf.getText());
        pst.setString(7, inpEnd.getText());
        pst.setString(8, inpCidade.getText());
        pst.setString(9, inpEstado.getText());

        int adicionado = pst.executeUpdate();

        if (adicionado > 0) {
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso.");
            limparCampos();
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Erro ao adicionar usuário: " + e.getMessage());
    }
    }
        
    private void limparCampos() {
        inpTel.setText   (null);
        inpnomes.setText (null);
        inpCidade.setText(null);
        inpDdd.setText   (null);
        inpTel.setText   (null);
        inpEmail.setText (null);
        inpNascimento.setText(null);
        inpCpf.setText   (null);
        inpEnd.setText   (null);
        inpCidade.setText(null);
        inpEstado.setText(null);
    }
    
    private void alterar() {
//        String cpfFormatado = formatarCPF(inpCpf.getText());

        String sql = "UPDATE tb_dados SET email = ?, DDD = ?, tel = ?, dataNascimento = ?, cpf = ?, endereco = ?, cidade = ?, estado = ? WHERE id = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, inpId.getText());
            pst.setString(2, inpnomes.getText());
            pst.setString(3, inpDdd.getText());
            pst.setString(4, inpTel.getText());
            pst.setString(5, inpEmail.getText());
            pst.setString(6, inpNascimento.getText());
            pst.setString(7, inpCpf.getText());
            pst.setString(8, inpEnd.getText());
            pst.setString(9, inpCidade.getText());
            pst.setString(10, inpEstado.getText());
            
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
            String sql = "DELETE FROM tb_dados WHERE id = ?";
            try{
                pst = conexao.prepareStatement(sql);
                pst.setString(1, inpId.getText());
                pst.executeUpdate();
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }
    
    private String formatarCPF(String cpf) {
    // Remove caracteres não numéricos
    cpf = cpf.replaceAll("[^0-9]", "");
    
    // Insere os pontos e o traço
    return cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9);
    }

    public class CPFValidator {
        public static boolean validarCPF(String cpf_str) {
            // Verificar se o CPF tem 11 dígitos
            if (cpf_str.length() != 11) {
                JOptionPane.showMessageDialog(null, "CPF deve ter 11 dígitos. Tente novamente.");
                return false;
            }

            char[] cpf_char = cpf_str.toCharArray();
            int[] cpf_int = new int[cpf_char.length];

            for (int i = 0; i < cpf_char.length; i++) {
                cpf_int[i] = Character.getNumericValue(cpf_char[i]);
            }

            // Calcular o primeiro dígito verificador
            int soma = 0;
            for (int i = 0; i < 9; i++) {
                soma += cpf_int[i] * (10 - i);
            }
            int primeiroDigito = 11 - (soma % 11);
            if (primeiroDigito > 9) {
                primeiroDigito = 0;
            }

            // Calcular o segundo dígito verificador
            soma = 0;
            for (int i = 0; i < 10; i++) {
                soma += cpf_int[i] * (11 - i);
            }
            int segundoDigito = 11 - (soma % 11);
            if (segundoDigito > 9) {
                segundoDigito = 0;
            }

            // Verificar se os dígitos verificadores estão corretos
            if (cpf_int[9] == primeiroDigito && cpf_int[10] == segundoDigito) {
                JOptionPane.showMessageDialog(null, "CPF válido.");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "CPF inválido. Tente novamente.");
                return false;
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
        inpTel = new javax.swing.JTextField();
        inpnomes = new javax.swing.JTextField();
        inpCidade = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        inpDdd = new javax.swing.JTextField();
        inpEmail = new javax.swing.JTextField();
        inpNascimento = new javax.swing.JTextField();
        inpEnd = new javax.swing.JTextField();
        inpCpf = new javax.swing.JTextField();
        inpEstado = new javax.swing.JTextField();
        btnLimpar = new javax.swing.JButton();

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

        ID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ID.setText("ID");

        btnApagar.setText("Apagar  Usuario");
        btnApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApagarActionPerformed(evt);
            }
        });

        nome.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nome.setText("Nome");

        btnEdit.setText("Editar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        email.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        email.setText("Email");

        senha.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        senha.setText("Cidade");

        inpId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpIdActionPerformed(evt);
            }
        });

        inpTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpTelActionPerformed(evt);
            }
        });

        inpnomes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpnomesActionPerformed(evt);
            }
        });

        inpCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpCidadeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("TELA CADASTRO");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("DDD");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setText("Telefone");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel4.setText("Data de Nascimento");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel5.setText("CPF");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel6.setText("Endereço");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setText("Estado");

        inpDdd.setMinimumSize(new java.awt.Dimension(300, 300));
        inpDdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpDddActionPerformed(evt);
            }
        });

        inpEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpEmailActionPerformed(evt);
            }
        });

        inpNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpNascimentoActionPerformed(evt);
            }
        });

        inpEnd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpEndActionPerformed(evt);
            }
        });

        inpCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inpCpfActionPerformed(evt);
            }
        });

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nome)
                            .addComponent(email)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(inpEmail)
                            .addComponent(inpnomes)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(inpDdd, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(inpTel, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(inpNascimento))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(inpId, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(senha)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(inpEstado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(inpCidade)
                                    .addComponent(inpCpf)
                                    .addComponent(inpEnd)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnLimpar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnAdd)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnVisualizar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnApagar)))))))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ID))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpnomes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nome))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(inpDdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(inpTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(email)
                    .addComponent(inpEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inpNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(inpCpf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(inpEnd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(senha)
                    .addComponent(inpCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(inpEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVisualizar)
                    .addComponent(btnLimpar)
                    .addComponent(btnApagar))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
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

    private void inpTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpTelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpTelActionPerformed

    private void inpEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpEmailActionPerformed

    private void inpNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpNascimentoActionPerformed

    private void inpEndActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpEndActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpEndActionPerformed

    private void inpCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpCpfActionPerformed
        // validar CPF
//        String cpf = inpCpf.getText();
//        boolean cpfValido = CPFValidator.validarCPF(cpf);
    }//GEN-LAST:event_inpCpfActionPerformed

    private void inpnomesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpnomesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpnomesActionPerformed

    private void inpCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpCidadeActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        // Função para limpar os dados no login:
        // As linhas abaixo irão limpar o formulario
        limparCampos();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void inpDddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inpDddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inpDddActionPerformed

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
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnVisualizar;
    private javax.swing.JLabel email;
    private javax.swing.JTextField inpCidade;
    private javax.swing.JTextField inpCpf;
    private javax.swing.JTextField inpDdd;
    private javax.swing.JTextField inpEmail;
    private javax.swing.JTextField inpEnd;
    private javax.swing.JTextField inpEstado;
    private javax.swing.JTextField inpId;
    private javax.swing.JTextField inpNascimento;
    private javax.swing.JTextField inpTel;
    private javax.swing.JTextField inpnomes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel nome;
    private javax.swing.JLabel senha;
    // End of variables declaration//GEN-END:variables
}
