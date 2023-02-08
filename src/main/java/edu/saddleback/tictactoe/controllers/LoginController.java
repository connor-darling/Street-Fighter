package edu.saddleback.tictactoe.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

import edu.saddleback.tictactoe.Game;

import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.Scene;

public class LoginController {
  // all fxml properties
  public Button online_btn = null;
  public Button single_player_btn = null;
  public Button multiplayer_btn = null;
  public Button return_btn = null;
  public AnchorPane overlay = null;
  public AnchorPane root = null;
  public ImageView background_image = null;

  public LoginController() {
    backgrounds = new ArrayList<Image>();
    readBackgrounds();
  }

  @FXML
  public void initialize() {
    background_image.setImage(getRandomBackground());

    return_btn.setOnAction(event -> {
        overlay.setVisible(false);
    });

    single_player_btn.setOnAction(event -> {
      overlay.setVisible(true);
    });

    multiplayer_btn.setOnAction(event -> {
      try {
        FXMLLoader loader = new FXMLLoader(Game.class.getResource("views/Game.fxml"));
        Scene scene = loader.<Scene>load();
        Stage window = (Stage)root.getScene().getWindow();
        window.setScene(scene);
        window.show();
      } catch (Exception e) {
        System.out.println(e);
      }
    });

    online_btn.setOnAction(event -> {
      overlay.setVisible(true);
    });
  }

  public void readBackgrounds() {
    String filePath = Game.class.getResource("data/backgrounds.db").toString().substring(5);
    try {
      Scanner input = new Scanner(new File(filePath));
      while (input.hasNextLine()) {
        String line = input.nextLine();
        InputStream stream = new FileInputStream(line);
        Image image = new Image(stream);
        // Image image = new Image(new File(line).toURI().toString());
        backgrounds.add(image);
      }
      input.close();
    } catch (FileNotFoundException e) {
      System.out.println("File not found: " + filePath);
    }
  }

  public Image getRandomBackground() {
    Random rand = new Random();
    int index = rand.nextInt(backgrounds.size());
    Image randomBackground = backgrounds.get(index);
    return randomBackground;
  }

  private ArrayList<Image> backgrounds = null;
}
