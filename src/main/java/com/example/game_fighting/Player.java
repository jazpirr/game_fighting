package com.example.game_fighting;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Player {
    private Shape body;

    public Player(double x, double y, Color color, boolean isRectangle) {
        if (isRectangle) {
            body = new Rectangle(50, 50, color);
        } else {
            body = new Circle(25, color);
        }
        body.setTranslateX(x);
        body.setTranslateY(y);
    }

    public Shape getBody() {
        return body;
    }

    public void move(double dx, double dy) {
        body.setTranslateX(body.getTranslateX() + dx);
        body.setTranslateY(body.getTranslateY() + dy);
    }

    public boolean intersects(Player other) {
        return body.getBoundsInParent().intersects(other.body.getBoundsInParent());
    }


    public void setColor(Color color) {
        if (body instanceof Rectangle) {
            body.setFill(color);
        } else if (body instanceof Circle) {
            body.setFill(color);
        }
    }

    public void setShapeToCircle() {
        if (body instanceof Rectangle rect) {
            double centerX = rect.getTranslateX() + rect.getWidth() / 2;
            double centerY = rect.getTranslateY() + rect.getHeight() / 2;
            Circle circle = new Circle(rect.getWidth() / 2, Color.RED);
            circle.setTranslateX(centerX);
            circle.setTranslateY(centerY);
            body = circle;
        }
    }
    public void setShapeToCircle2() {
        if (body instanceof Rectangle rect) {
            double centerX = rect.getTranslateX() + rect.getWidth() / 2;
            double centerY = rect.getTranslateY() + rect.getHeight() / 2;
            Circle circle = new Circle(rect.getWidth() / 2, Color.BLUE);
            circle.setTranslateX(centerX);
            circle.setTranslateY(centerY);
            body = circle;
        }
    }

    public void setShapeToRectangle() {
        if (body instanceof Circle circle) {
            double x = circle.getTranslateX() - circle.getRadius();
            double y = circle.getTranslateY() - circle.getRadius();
            Rectangle rect = new Rectangle(circle.getRadius() * 2, circle.getRadius() * 2, Color.RED);
            rect.setTranslateX(x);
            rect.setTranslateY(y);
            body = rect;
        }
    }

    public void setShapeToRectangle2() {
        if (body instanceof Circle circle) {
            double x = circle.getTranslateX() - circle.getRadius();
            double y = circle.getTranslateY() - circle.getRadius();
            Rectangle rect = new Rectangle(circle.getRadius() * 2, circle.getRadius() * 2, Color.BLUE);
            rect.setTranslateX(x);
            rect.setTranslateY(y);
            body = rect;
        }
    }

    public javafx.geometry.Bounds getBoundsInParent() {
        return body.getBoundsInParent();
    }
}
