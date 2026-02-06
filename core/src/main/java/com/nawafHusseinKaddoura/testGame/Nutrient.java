package com.nawafHusseinKaddoura.testGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
public class Nutrient {

     Vector2 position;

     Texture texture;

     Rectangle hitBox;

     float protein;


     Nutrient(float x, float y, Texture texture1) {
         position = new Vector2(x, y);
         texture = texture1;
         protein = 50f;
         hitBox = new Rectangle(x, y, 1, 1);
     }


    void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, 1, 1);
    }

    void dispose() {
        texture.dispose();
    }
}
