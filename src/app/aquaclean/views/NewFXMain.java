/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.aquaclean.views;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author user
 */
public class NewFXMain extends Application {
    
    public static String Path="../Bon de livraison/";

    public static Stage _stage;
    public static NewFXMain _self;
    @Override
    public void start(Stage primaryStage) {
          _stage = primaryStage;
          _self = this;
          setScene("Home");
    }
     public static BufferedImage iconToImage(Icon icon) {
   if (icon instanceof ImageIcon) {
      return (BufferedImage)((ImageIcon)icon).getImage();
   } 
   else {
      int w = icon.getIconWidth();
      int h = icon.getIconHeight();
       GraphicsEnvironment ge = 
        GraphicsEnvironment.getLocalGraphicsEnvironment();
       GraphicsDevice gd = ge.getDefaultScreenDevice();
       GraphicsConfiguration gc = gd.getDefaultConfiguration();
       BufferedImage image = gc.createCompatibleImage(w, h);
       Graphics2D g = image.createGraphics();
      icon.paintIcon(null, g, 0, 0);
      g.dispose();
      return image;
   }
 }
    public static void setScene(String sceneName)
    {
        try {
            Parent root = FXMLLoader.load(_self.getClass().getResource(sceneName + ".fxml"));
            

            Scene scene = new Scene(root);
            _stage.setScene(scene);
            _stage.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
