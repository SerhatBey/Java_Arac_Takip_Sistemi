/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package takip_sistemi;

import javax.mail.PasswordAuthentication;

/**
 *
 * @author Serhat
 */
public class MailKullaniciBilgi  extends javax.mail.Authenticator  {
    public PasswordAuthentication getPasswordAuthentication() {
       String username = "serhad12365@gmail.com";
       String password = "kbcmhbilixaifexr";
       return new PasswordAuthentication(username, password);
    }
}
