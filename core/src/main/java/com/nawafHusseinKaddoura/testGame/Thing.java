package com.nawafHusseinKaddoura.testGame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Thing {
    Texture texture;
    Vector2 position;
    Vector2 velocity;

    Rectangle hitBox;

    Vector2 goal;

    boolean hasGoal;

    float hunger;

    float maxHunger;

    int[] gene;

    //Array<Nutrient> nutrients;

    Thing(float _x, float _y, float _mass,Texture texture1) {
        position = new Vector2(_x, _y);
        hasGoal = false;
        velocity = new Vector2(0, 0);
        goal = new Vector2(_x, _y);
        texture = texture1;
        hitBox = new Rectangle(_x, _y, 1, 1);
        hunger = 100f;
        maxHunger = hunger;
        gene = new int[]{1, 1, 1, 1, 1, 1, 1, 1};
    }

    void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, 1, 1);
        hitBox.setPosition(position.x, position.y);
    }

    //handles the frame by frame of the class
    void update(float delta, Array<Nutrient> nutrients1) {
        //nutrients = nutrients1;
        hunger -= 0.01f;
        goTo(delta);
        geneLogic();


    }

    //Makes the object go directly the goal with a specific speed
    void goTo(float deltaTime) {
        Vector2 direction = goal.cpy().sub(position);

        if (direction.len() > 0.001f) {
            hunger -= 0.01f;
            direction.nor();
            position.mulAdd(direction, 1 * deltaTime);
            hasGoal = true;
        } else {
            hasGoal = false;
        }
    }

    //called to set the goal of the object
    void setGoal(Vector2 vect) {
        goal.set(vect);
    }

    //sets the logic for the genome
    void geneLogic() {
        if(hunger <= 0) {
            dispose();
        }

        float closestDist2 = Float.MAX_VALUE;
        Vector2 closest = null;
        float senseRadius = 3f*3f;

        for (Nutrient nutrient : Main.nutrients) {
            float dist2 = nutrient.position.dst2(position);

            if (dist2 < closestDist2 && dist2 <= senseRadius && hunger <= maxHunger/5*4) {
                closestDist2 = dist2;
                closest = nutrient.position;
                if(hitBox.overlaps(nutrient.hitBox)) {
                    nutrient.dispose();
                    Main.nutrients.removeValue(nutrient, false);
                    goal.set(position);
                    hunger += nutrient.protein;

                    hunger = MathUtils.clamp(-5f, maxHunger, hunger);

                }
            }
        }

        if (closest != null) {
            goal.set(closest);
            hasGoal = true;
        }

    }

    //disposes of the texture
    void dispose() {
        texture.dispose();
    }



}
