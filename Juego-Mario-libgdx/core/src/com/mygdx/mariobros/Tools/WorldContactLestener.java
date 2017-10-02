package com.mygdx.mariobros.Tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.mariobros.Sprites.interactiveTileObject;

/**
 * Created by PEDRO on 12/02/2016.
 */
public class WorldContactLestener implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture fixture_A = contact.getFixtureA();
        Fixture fixture_B = contact.getFixtureB();
        if(fixture_A.getUserData() == "head" || fixture_B.getUserData() == "head"){
            Fixture head = fixture_A.getUserData() == "head" ? fixture_A : fixture_B;
            Fixture Object = head == fixture_A ? fixture_B : fixture_A;
            if(Object.getUserData() instanceof interactiveTileObject){
                ((interactiveTileObject) Object.getUserData()).onHeadHit();
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
