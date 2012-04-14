/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIStuff;

import Utilities.Image2D;
import Utilities.ImageCollection;
import Utilities.Mouse;
import Utilities.Rect;
import Utilities.Vector2;
import WorldObjects.towers.*;
import ogpc5.game.CityGame;

/**
 *
 * @author pcowal15
 */
public class Draggable {
    Vector2 pos;
    Image2D sprite;
    double movespeed = 0.5;
    int id;
    public Draggable(String sp,Vector2 p, int ID){
        sprite=new Image2D(sp);
        pos=p;
        id=ID;
    }
    public void update(Mouse m){
        double x1=pos.getX();
        double x2=m.location().getX();
        double y1=pos.getY();
        double y2=m.location().getY();
        //smooth glidey motion!!!1!1
        pos.dX((x2-x1)*movespeed);
        pos.dY((y2-y1)*movespeed);
        
    }
    public void draw(ImageCollection batch){
        batch.Draw(sprite,pos,1000);
    }
    
    public Tower getTower(Rect pos){
        Tower t=null;
        Vector2 placingPos=new Vector2(pos.x*32 +16, pos.y*32 +16);
        switch(id){
            case Tower.FACTORY:
                t= new Factory(placingPos,1,1);
                break;
            case Tower.GREEN_BELT:
                t=new GreenBelt(placingPos,1,1);
                break;
            case Tower.HOUSE:
                t= new House(placingPos,1,1);
                break;
            case Tower.MONUMENT:
                t= new Monument(placingPos,1,1);
                break;
            case Tower.POLICE_FIRE_STATION:
                t= new PoliceFire(placingPos,1,1);
                break;
            case Tower.RECYCLING_CENTER:
                t= new RecyclingCenter(placingPos,1,1);
                break;
            case Tower.SCHOOL:
                t= new School(placingPos,1,1);
                break;
            case Tower.STORE:
                t= new Store(placingPos,1,1);
            case Tower.GENERIC:
                t= new GenericTower(placingPos,"");
                break;
            default:
                t= new GenericTower(placingPos,"");
                break;
        }
        return t;
    }
    
}
