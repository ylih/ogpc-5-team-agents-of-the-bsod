/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GamePlay;

import Utilities.ImageCollection;
import Utilities.Rect;
import Utilities.Vector2;
import WorldObjects.towers.Tower;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import ogpc5.game.CityGame;

/**
 *
 * @author Nekel-Seyew
 */
public class Menu {
    Vector2 position;
    int width;
    int height;
    int currentRibbon;//1-tower, 2-stats, 3-option
    
    Tile selectedTower;
    
    GUIObject towerRibbon;
    GUIObject statsRibbon;
    GUIObject optionsRibbon;
    
    ScrollingMenu stats;
    ScrollingMenu Towers;
    
    Tower[][] t;
    
    public Menu(CityGame g){
        position=new Vector2(835,0);
        width= 128;
        height=600;
        towerRibbon= new GUIObject(new Vector2(900,20), "Game Resources/Sprites/GUIs/BlueBox.png");
        statsRibbon=new GUIObject(new Vector2(900,60), "Game Resources/Sprites/GUIs/RedBox.png");
        optionsRibbon=new GUIObject(new Vector2(900,100), "Game Resources/Sprites/GUIs/BlueBox.png");
        stats= new Stats(g, new Vector2(840,120));
        Towers = new Towers(new Vector2(860,60));
        t=g.towers;
    }
    
    public void Draw(ImageCollection batch){
        towerRibbon.Draw(batch);
        statsRibbon.Draw(batch);
        optionsRibbon.Draw(batch);
        batch.fillRect(position, width, height, Color.white, 9);
        batch.drawRect(position, width, height, Color.black, 9);
        
        if(this.currentRibbon==2 && optionsRibbon.isAtBottom && statsRibbon.isAtTop){
            stats.Draw(batch);
        }
        if(this.currentRibbon==1 && optionsRibbon.isAtBottom && statsRibbon.isAtBottom){
            Towers.Draw(batch);
        }
        if(selectedTower!=null){
            selectedTower.Draw(batch);
        }
    }
    
    public void Update(){
        towerRibbon.Update();
        statsRibbon.Update();
        optionsRibbon.Update();
        if(!(towerRibbon.pos.getY()<statsRibbon.pos.getY())||!(statsRibbon.pos.getY()<optionsRibbon.pos.getY())){
            resetRibbons();
        }
        if((towerRibbon.pos.getY()>statsRibbon.pos.getY())||(statsRibbon.pos.getY()>optionsRibbon.pos.getY())){
            resetRibbons();
        }
    }
    
    public void giveMouseEvent(MouseEvent e){
        if(towerRibbon.boundingBox.contains(e.getX(), e.getY())){
            if (statsRibbon.isAtBottom) {
                statsRibbon.setDistanceToGo(40 * 10, -1);
                optionsRibbon.setDistanceToGo(40 * 10, -1);
                statsRibbon.start();
                optionsRibbon.start();
            } else {
                if(optionsRibbon.isAtBottom || optionsRibbon.isMoving){
                    statsRibbon.setDistanceToGo(40 * 10, 1);
                    statsRibbon.start();
                    this.currentRibbon=1;
                } else {
                    statsRibbon.setDistanceToGo(40 * 10, 1);
                    optionsRibbon.setDistanceToGo(40 * 10, 1);
                    statsRibbon.start();
                    optionsRibbon.start();
                    this.currentRibbon=1;
                }
            }
        }
        if(statsRibbon.boundingBox.contains(e.getX(), e.getY())) {
            if (statsRibbon.isAtBottom && !statsRibbon.isMoving) {
                statsRibbon.setDistanceToGo(40 * 10, -1);
                statsRibbon.start();
                this.currentRibbon=2;
            } else if (optionsRibbon.isAtBottom) {
                optionsRibbon.setDistanceToGo(40 * 10, -1);
                optionsRibbon.start();
                this.currentRibbon=0;
            } else {
                this.currentRibbon=2;
                optionsRibbon.setDistanceToGo(40 * 10, 1);
                optionsRibbon.start();
            }
        }
        if(optionsRibbon.boundingBox.contains(e.getX(), e.getY())){
            if(!statsRibbon.isAtTop){
                statsRibbon.setDistanceToGo(40 * 10, -1);
                statsRibbon.start();
                optionsRibbon.setDistanceToGo(40 * 10, -1);
                optionsRibbon.start();
            }
            this.currentRibbon=3;
        }
        
        if(currentRibbon==1){
           selectedTower=(Tile)Towers.giveMouseEvent(e);
        }
    }
    
    public void giveMouseDraggedEvent(MouseEvent e){
        if(selectedTower!=null){
            selectedTower.giveNewPos(new Vector2(e.getX(), e.getY()));
        }
    }
    
    public void giveMouseReleased(MouseEvent e){
        for(int i=0; i<t.length; i++){
            for(int j=0; j<t[i].length; j++){
                if(shouldPlaceTileHere(i*32, j*32)){
                    t[i][j]=selectedTower.getTower();
                    selectedTower=null;
                }
            }
        }
    }
    private boolean shouldPlaceTileHere(int x, int y){
        if (selectedTower != null) {
            Rectangle b = selectedTower.bounding.intersection(new Rect(x, y, 32, 32));
            if (b.height * b.width > 4*4) {
                return true;
            }
        }
        return false;
    }
    
    public void resetRibbons(){
        towerRibbon= new GUIObject(new Vector2(900,20), "Game Resources/Sprites/GUIs/BlueBox.png");
        statsRibbon=new GUIObject(new Vector2(900,60), "Game Resources/Sprites/GUIs/RedBox.png");
        optionsRibbon=new GUIObject(new Vector2(900,100), "Game Resources/Sprites/GUIs/BlueBox.png");
        this.currentRibbon=0;
    }
    
    public ScrollingMenu getScrollingMenu(){
        if(this.currentRibbon==1){
            return null;
        }
        if(this.currentRibbon==2){
            return stats;
        }
        if(this.currentRibbon==3){
            return null;
        }
        return null;//default
    }
    
    public void giveMouseWheelEvent(MouseWheelEvent e){
        
    }
}
