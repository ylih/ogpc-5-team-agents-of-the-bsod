/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package WorldObjects.towers;

import Enemies.Enemy;
import Utilities.Animation;
import Utilities.ImageCollection;
import Utilities.Vector2;
import ogpc5.game.CityGame;

/**
 *
 * @author Ksweeney12
 */
public class Monument extends Tower{

    public Monument(Vector2 pos, int high, int wide){
        super(pos,"", high, wide);  
        cost=2000;
    }
    
    @Override
    public Animation getAnimation() {
        return ani;
    }

    @Override
    protected void loadAnimation() {
        
    }

    @Override
    protected void loadStats() {
        damage=1;
        health=1000;
        range=48*Math.sqrt(2);
        adamage=10;
        projspeed=10;//not correct
        speed=10;//not done
        this.moneyBonus=35;
        this.happyBonus=7;
    }

    @Override
    public void Draw(ImageCollection batch) {
        
    }

    @Override
    public void updateGameStats(CityGame theGame) {
        
    }

    @Override
    protected Bullet setEnemyBulletHitting(Enemy e) {
        if(e.getID()==Enemy.SMOG){
            return new Bullet(position.clone(), damage/2, adamage/2, sdamage/2, projspeed, e);
        }else if(e.getID()==Enemy.ARSONIST){
            return new Bullet(position.clone(), damage/2, adamage/2, sdamage/2, projspeed, e);
        }
        return new Bullet(position.clone(), damage, adamage, sdamage, projspeed, e);
    }
    
}