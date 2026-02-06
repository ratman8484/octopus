package com.nawafHusseinKaddoura.testGame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Random;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private FitViewport viewport;
    //public float deltaTime;
    public static Random random = new Random();

    Array<Thing> things;

    public static Array<Nutrient> nutrients;

    @Override
    public void create() {

        //deltaTime = Gdx.graphics.getDeltaTime();

        viewport = new FitViewport(10, 10);

        things = new Array<>();

        nutrients = new Array<>();

        batch = new SpriteBatch();

        createThing(viewport.getWorldWidth()/2, viewport.getWorldHeight()/2, "rectangle.png");
        //createNutrient(viewport.getWorldWidth()*0.55f, viewport.getWorldHeight()*0.55f, "rectangle.png");
        createNutrient(viewport.getWorldWidth()/4*2, viewport.getWorldHeight()/4*3, "rectangle.png");

    }
    public void createNutrient(float x, float y, String texture) {
        Texture texture1 = new Texture(texture);
        Nutrient nutrient = new Nutrient(x, y, texture1);
        nutrients.add(nutrient);
    }

    public void createThing(float x, float y, String texture) {
        Texture texture1 = new Texture(texture);
        Thing thing = new Thing(x, y, 1,texture1);
        things.add(thing);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {

        logic();
        input();
        draw();

    }

    void draw() {

        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        //ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();

        for(int f = things.size-1; f >= 0; f--) {
            Thing thing = things.get(f);
            thing.render(batch);
        }

        for(int f = nutrients.size-1; f >= 0; f--) {
            Nutrient nutrient = nutrients.get(f);
            nutrient.render(batch);
        }

        batch.end();

    }

    void logic() {
        float deltaTime = Gdx.graphics.getDeltaTime();
        for(int f = things.size-1; f >= 0; f--) {
            Thing thing = things.get(f);
            thing.update(deltaTime, nutrients);
        }

    }

    void input() {

        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            viewport.getCamera().translate(0, 0.1f, 0);
            viewport.getCamera().update();
        } else if(Gdx.input.isKeyPressed(Input.Keys.S)) {
            viewport.getCamera().translate(0, -0.1f, 0);
            viewport.getCamera().update();
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            viewport.getCamera().translate(0.1f, 0, 0);
            viewport.getCamera().update();
        } else if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            viewport.getCamera().translate(-0.1f, 0, 0);
            viewport.getCamera().update();
        }

    }

    @Override
    public void dispose() {
        for(Thing thing : things) {
            thing.dispose();
        }
        batch.dispose();
    }
}
