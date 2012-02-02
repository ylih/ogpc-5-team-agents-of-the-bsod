/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePlay;

import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Rect;
import Utilities.Vector2;
import java.util.ArrayList;
import ogpc5.game.CityGame;

/**
 *
 * @author Ksweeney12
 */
public class GUIObject {
    
    Rect boundingBox;
    Image2D sprite;
    Vector2 pos;
    boolean shouldMove=true;
    
    public void setMove(boolean should){
        shouldMove=should;
    }
    
    public GUIObject(Vector2 position, String path){
        pos=position;
        sprite= new Image2D(path);
        sprite.setVector2(pos);
        boundingBox=CityGame.getRectangle(sprite);
    }
    
    public void Update(ArrayList<GUIObject> guiobjects){
        boundingBox=CityGame.getRectangle(sprite);
        for(GUIObject g: guiobjects){
            if(!boundingBox.intersects(CityGame.getRectangle(g.sprite))&&g!=this&&shouldMove){
                pos.dY(5);
            }
        }
        //pos.dY(5);
    }
    
    public void Draw(ImageCollection batch){
        batch.addToCollection(sprite, 10, pos);
    }
    
}