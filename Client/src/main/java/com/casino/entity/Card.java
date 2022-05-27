package com.casino.entity;

import casino.client.CasinoApplication;
import com.casino.enums.Color;
import com.casino.enums.Value;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONObject;

import java.io.File;


public class Card {

    public static double width = 40;
    public static double height = 60;
    public static int fixWidth = 100;

    public static int lineOfBack = 4;

    private Value value;
    private Color color;

    public Card(Color color, Value value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public Value getValue() {
        return value;
    }

    public static ImageView createBackCard() {

        try {
            ImageView imageView = new ImageView();

            Image image = new Image("./decks_sprite.png");

            imageView.setImage(image);

            double y = lineOfBack * height;

            return createCard(0, y);

        } catch (Exception e) {
            System.out.println("Apparu a Card - createBackCard");
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static ImageView createCard(double x, double y) {

        try {
            ImageView imageCard = new ImageView();

            Image image = new Image(new File("src/main/resources/decks_sprite.png").toURI().toString());

            System.out.println(image.getUrl());

            imageCard.setImage(image);

            Rectangle2D viewportRect = new Rectangle2D(x, y, width, height);
            imageCard.setViewport(viewportRect); // Sélectionne la partie de l'image que l'on souhaite obtenir

            imageCard.setFitWidth(fixWidth); // Réduit la taille de l'image
            imageCard.setPreserveRatio(true); // Préserve le ratio largeur hauteur lors du redimensionnement de l'image
            imageCard.setSmooth(true); // « lisse », utile dans le cas d'un redimensionnement uniquement
            imageCard.setCache(true); // Mets en cache l'image (optimisation)

            return imageCard;

        } catch (Exception e) {
            System.out.println("Apparu a Card - initFrom");
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static Card initFrom(JSONObject object) {
        try {
            Color colorCard = Color.valueOf(object.getString("color"));
            Value valueCard = Value.valueOf(object.getString("value"));

            Card card = new Card(colorCard, valueCard);

            return card;
        } catch (Exception e) {
            System.out.println("Apparu a Card - initFrom");
            System.out.println(e.getMessage());
        }

        return null;
    }
}
