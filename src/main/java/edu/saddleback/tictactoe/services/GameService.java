package edu.saddleback.tictactoe.services;

import edu.saddleback.tictactoe.models.Player;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class GameService {
  public static void setPlayerProfile(AnchorPane profile, Player player) {
    ImageView image = (ImageView) profile.getChildren().get(0);
    image.setImage(new Image(player.getAvatarPath().toString()));

    AnchorPane inputDiv = (AnchorPane) profile.getChildren().get(1);
    Label label = (Label) inputDiv.getChildren().get(0);
    label.setText(player.getName());
  }

  public static void setPlayerTile(HBox box, Player player) {
    ImageView imageTile = new ImageView(player.getAvatarPath().toString());
    imageTile.setFitWidth(100);
    imageTile.setFitHeight(100);
    imageTile.setEffect(new DropShadow());

    box.getChildren().add(imageTile);

    box.getStyleClass().removeAll("tile-active");
    box.getStyleClass().add("tile-taken");
  }

  public static void resetTile(HBox tile) {
    if (tile.getStyleClass().contains("tile-active")) {
      return;
    }
    tile.getChildren().clear();
    tile.getStyleClass().removeAll("tile-taken");
    tile.getStyleClass().add("tile-active");
  }

  public static void setPlayerThinking(AnchorPane profile, boolean bool) {
    getIndicator(profile).setVisible(bool);
  }

  private static ProgressIndicator getIndicator(AnchorPane profile) {
    AnchorPane inputDiv = (AnchorPane) profile.getChildren().get(1);
    ProgressIndicator indicator = (ProgressIndicator) inputDiv.getChildren().get(1);
    return indicator;
  }
}
