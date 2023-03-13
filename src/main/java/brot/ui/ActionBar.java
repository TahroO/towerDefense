package brot.ui;

import brot.helperMethods.Constants;
import brot.objects.Tower;
import brot.scenes.Playing;

import java.awt.*;
import java.text.DecimalFormat;

import static brot.main.GameStates.MENU;
import static brot.main.GameStates.setGameState;

public class ActionBar extends Bar {
    private Playing playing;
    private MyButton bMenu;
    private MyButton[] towerButtons;
    private Tower selectedTower;
    private Tower displayedTower;
    private DecimalFormat formatter;
    private int gold = 100;
    private boolean showTowerCost;
    private int towerCostType;

    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
        formatter = new DecimalFormat("0.00");
        initButtons();
    }

    public void draw(Graphics g) {
        // Background
        g.setColor(new Color(252, 165, 3));
        g.fillRect(x, y, width, height);
        // Buttons
        drawButtons(g);
        // displayed tower
        drawDisplayedTower(g);
        // waveInfo
        drawWaveInfo(g);
        // goldInfo
        drawGoldAmount(g);
        // towerCost
        if (showTowerCost) {
            drawTowerCost(g);
        }
    }

    private void drawTowerCost(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(280, 650, 120, 50);
        g.setColor(Color.BLACK);
        g.drawRect(280, 650,120, 50);
        g.drawString("" + getTowerCostName(), 285, 670);
        g.drawString("Cost: " + getTowerCostValue() + "g", 285, 695);
        // Show this if player lacks gold for tower
        if (isTowerCostMoreThanGold()) {
            g.setColor(Color.RED);
            g.drawString("Can´t Afford!", 260, 725);
        }
    }

    private boolean isTowerCostMoreThanGold() {
        return getTowerCostValue() > gold;
    }

    private String getTowerCostName() {
        return Constants.Towers.getName(towerCostType);
    }

    private int getTowerCostValue() {
        return Constants.Towers.getTowerCost(towerCostType);
    }

    private void drawGoldAmount(Graphics g) {
        g.drawString("Gold: " + gold, 110, 725);
    }

    private void drawWaveInfo(Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("LucidaSans", Font.BOLD, 20));
        drawWaveTimerInfo(g);
        drawEnemiesLeftInfo(g);
        drawWavesLeftInfo(g);
    }

    private void drawWavesLeftInfo(Graphics g) {
        int current = playing.getWaveManager().getWaveIndex();
        int size = playing.getWaveManager().getWaves().size();
        g.drawString("Wave " + (current + 1) + "/" + size, 425, 770);
    }

    private void drawEnemiesLeftInfo(Graphics g) {
        int remaining = playing.getEnemyManager().getAmountOfAliveEnemies();
        g.drawString("Enemies left: " + remaining, 425, 790);
        
    }

    private void drawWaveTimerInfo(Graphics g) {
        if (playing.getWaveManager().isWaveTimerStarted()) {
            float timeLeft = playing.getWaveManager().getTimeLeft();
            String formattedText = formatter.format(timeLeft);
            g.drawString("Time left: " + formattedText, 425, 750);
        }
    }

    private void drawDisplayedTower(Graphics g) {
        if (displayedTower != null) {
            g.setColor(Color.gray);
            g.fillRect(410, 645, 220, 85);
            g.setColor(Color.BLACK);
            g.drawRect(410, 645, 220, 85);
            g.drawRect(420, 650, 50, 50);
            g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowerType()], 420, 650, 50, 50, null );
            g.setFont(new Font("LucidaSans", Font.BOLD, 15));
            g.drawString("" + Constants.Towers.getName(displayedTower.getTowerType()), 490, 660);
            g.drawString("ID: " + displayedTower.getiD(), 490, 675);
            // Draw a border around selected tower
            drawDisplayedTowerBorder(g);
            drawDisplayTowerRange(g);
            
        }
    }

    private void drawDisplayTowerRange(Graphics g) {
        g.setColor(Color.white);
        g.drawOval(displayedTower.getX() + 16 - (int)(displayedTower.getRange() * 2) / 2,
                displayedTower.getY() + 16 - (int)(displayedTower.getRange() * 2) / 2,
                (int)displayedTower.getRange() * 2, (int)displayedTower.getRange() * 2);
    }

    private void drawDisplayedTowerBorder(Graphics g) {
        g.setColor(Color.CYAN);
        g.drawRect(displayedTower.getX(), displayedTower.getY(), 32, 32);
    }

    public void displayTower(Tower tower) {
        displayedTower = tower;
    }
    private void initButtons() {
        bMenu = new MyButton("Menu", 2, 642, 100, 30);
        towerButtons = new MyButton[3];
        int w = 50;
        int h = 50;
        int xStart = 110;
        int yStart = 650;
        int xOffset = (int) (w * 1.1f);
        for (int i = 0; i < towerButtons.length; i++) {
            towerButtons[i] = new MyButton("", xStart + xOffset * i, yStart, w, h, i);
        }
    }

    private void drawButtons(Graphics g) {
        bMenu.draw(g);
        for (MyButton b : towerButtons) {
            g.setColor(Color.GRAY);
            g.fillRect(b.x, b.y, b.width, b.height);
            g.drawImage(playing.getTowerManager().getTowerImgs()[b.getId()], b.x, b.y, b.width, b.height, null );
            drawButtonFeedback(g, b);
        }
    }

    public void mouseClicked(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            setGameState(MENU);
        } else {
            for (MyButton b : towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    if (!isEnoughGoldForTower(b.getId())) {
                        return;
                    }
                    selectedTower = new Tower(0, 0, -1, b.getId());
                    playing.setSelectedTower(selectedTower);
                    return;
                }
            }
        }
    }

    private boolean isEnoughGoldForTower(int towerType) {
        return gold >= Constants.Towers.getTowerCost(towerType);
    }

    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        showTowerCost = false;
        for (MyButton b : towerButtons) {
            b.setMouseOver(false);
        }

        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMouseOver(true);
        } else {
            for (MyButton b : towerButtons) {
                if (b.getBounds().contains(x, y)){
                    b.setMouseOver(true);
                    showTowerCost = true;
                    towerCostType = b.getId();
                    return;
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        if (bMenu.getBounds().contains(x, y)) {
            bMenu.setMousePressed(true);
        } else {
            for (MyButton b : towerButtons) {
                if (b.getBounds().contains(x, y)){
                    b.setMousePressed(true);
                    towerCostType = b.getId();
                    return;
                }
            }
        }
    }

    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        for (MyButton b : towerButtons) {
            b.resetBooleans();
        }
    }

    public void payForTower(int towerType) {
        this.gold -= Constants.Towers.getTowerCost(towerType);
    }

    public void addGold(int reward) {
        this.gold += reward;
    }
}
