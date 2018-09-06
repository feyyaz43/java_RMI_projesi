/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paket;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Feyyaz
 */
public class RMIServer extends UnicastRemoteObject implements RMI {

    public RMIServer() throws RemoteException {
        super();
    }

    @Override
    public byte[] getData(byte[] resim) throws RemoteException {
        byte[] sonuc_resim;
        BufferedImage bufferedImage = null;

        InputStream in = new ByteArrayInputStream(resim);
        try {
            bufferedImage = ImageIO.read(in);
        } catch (IOException ex) {
            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();

        BufferedImage bufferedImage2 = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                bufferedImage2.setRGB(i, j, bufferedImage.getRGB(j, i));
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage2, "jpg", baos);
            baos.flush();
        } catch (IOException ex) {
            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] imageInByte = baos.toByteArray();        //burda byte a çevrildi sola

        return imageInByte;
    }
    
    @Override
    public byte[] getData2(byte[] resim) throws RemoteException {
        byte[] sonuc_resim;
        BufferedImage bufferedImage = null;

        InputStream in = new ByteArrayInputStream(resim);
        try {
            bufferedImage = ImageIO.read(in);
        } catch (IOException ex) {
            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }

        int height = bufferedImage.getHeight();
        int width = bufferedImage.getWidth();

        BufferedImage bufferedImage2 = new BufferedImage(height, width, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                bufferedImage2.setRGB(height-1-i, width-1-j, bufferedImage.getRGB(j, i));       //sağa çevirme
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(bufferedImage2, "jpg", baos);
            baos.flush();
        } catch (IOException ex) {
            Logger.getLogger(RMIServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        byte[] imageInByte = baos.toByteArray();

        return imageInByte;
    }
    
    

    public static void main(String[] args) {
        Registry reg;
        try {
            reg = LocateRegistry.createRegistry(1099);
            reg.rebind("server", new RMIServer());
            System.out.println("server başladı");
        } catch (RemoteException ex) {
            System.out.println(ex);
        }
    }

}
