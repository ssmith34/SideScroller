package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;
public class EnemyManager {

    private Playing playing;
    private BufferedImage[][] crabbyArr, starfishArr, sharkArr;
    private Level currentLevel;

    public EnemyManager(Playing playing) {
        this.playing = playing;
        loadEnemyImgs();
    }

    public void loadEnemies(Level level) {
        this.currentLevel = level;
    }

    public void update(int[][] lvlData) {
        boolean isAnyActive = false;
        for (Crabby crabby : currentLevel.getCrabs())
            if (crabby.isActive()) {
                crabby.update(lvlData, playing);
                isAnyActive = true;
            }
        for (Starfish starfish : currentLevel.getStarfish())
            if (starfish.isActive()) {
                starfish.update(lvlData, playing);
                isAnyActive = true;
            }

        for (Shark shark : currentLevel.getSharks())
            if (shark.isActive()) {
                shark.update(lvlData, playing);
                isAnyActive = true;
            }

        if (!isAnyActive)
            playing.setLevelCompleted(true);
    }

    public void draw(Graphics g, int xLvlOffset) {
        drawCrabs(g, xLvlOffset);
        drawStarfish(g, xLvlOffset);
        drawSharks(g, xLvlOffset);
    }

    private void drawCrabs(Graphics g, int xLvlOffset) {
        for (Crabby c : currentLevel.getCrabs())
            if (c.isActive()) {
            g.drawImage(crabbyArr[c.getState()][c.getAniIndex()], (int) c.getHitbox().x - xLvlOffset - CRABBY_DRAWOFFSET_X + c.flipX(), (int) c.getHitbox().y - CRABBY_DRAWOFFSET_Y,
                    CRABBY_WIDTH * c.flipW(), CRABBY_HEIGHT, null);
//          c.drawHitbox(g, xLvlOffset);
//          c.drawAttackBox(g, xLvlOffset);
        }
    }

    private void drawStarfish(Graphics g, int xLvlOffset) {
        for (Starfish starfish : currentLevel.getStarfish())
            if (starfish.isActive()) {
                g.drawImage(starfishArr[starfish.getState()][starfish.getAniIndex()], (int) starfish.getHitbox().x - xLvlOffset - STARFISH_DRAWOFFSET_X + starfish.flipX(),
                        (int) starfish.getHitbox().y - STARFISH_DRAWOFFSET_Y + (int) starfish.getPushDrawOffset(), STARFISH_WIDTH * starfish.flipW(), STARFISH_HEIGHT, null);
//				p.drawHitbox(g, xLvlOffset);
            }
    }

    private void drawSharks(Graphics g, int xLvlOffset) {
        for (Shark shark : currentLevel.getSharks())
            if (shark.isActive()) {
                g.drawImage(sharkArr[shark.getState()][shark.getAniIndex()], (int) shark.getHitbox().x - xLvlOffset - SHARK_DRAWOFFSET_X + shark.flipX(),
                        (int) shark.getHitbox().y - SHARK_DRAWOFFSET_Y + (int) shark.getPushDrawOffset(), SHARK_WIDTH * shark.flipW(), SHARK_HEIGHT, null);
//				s.drawHitbox(g, xLvlOffset);
//				s.drawAttackBox(g, xLvlOffset);
            }
    }

    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        for (Crabby crabby : currentLevel.getCrabs())
            if (crabby.isActive())
                if (crabby.getState() != DEAD && crabby.getState() != HIT)
                    if (attackBox.intersects(crabby.getHitbox())) {
                        crabby.hurt(20);
                        return;
                    }
    }

    private void loadEnemyImgs() {
        crabbyArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITE), 9, 5, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
        starfishArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.STARFISH_ATLAS), 8, 5, STARFISH_WIDTH_DEFAULT, STARFISH_HEIGHT_DEFAULT);
        sharkArr = getImgArr(LoadSave.GetSpriteAtlas(LoadSave.SHARK_ATLAS), 8, 5, SHARK_WIDTH_DEFAULT, SHARK_HEIGHT_DEFAULT);
    }

    private BufferedImage[][] getImgArr(BufferedImage atlas, int xSize, int ySize, int spriteW, int spriteH) {
        BufferedImage[][] tempArr = new BufferedImage[ySize][xSize];
        for (int j = 0; j < tempArr.length; j++)
            for (int i = 0; i < tempArr[j].length; i++)
                tempArr[j][i] = atlas.getSubimage(i * spriteW, j * spriteH, spriteW, spriteH);
        return tempArr;
    }

    public void resetAllEnemies() {
        for (Crabby crabby : currentLevel.getCrabs())
            crabby.resetEnemy();
        for (Starfish starfish : currentLevel.getStarfish())
            starfish.resetEnemy();
        for (Shark shark : currentLevel.getSharks())
            shark.resetEnemy();
    }

}
