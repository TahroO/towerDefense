package brot.managers;

import brot.events.Wave;
import brot.scenes.Playing;

import java.util.ArrayList;
import java.util.Arrays;

public class WaveManager {
    private Playing playing;
    private ArrayList<Wave> waves = new ArrayList<>();
    private int enemySpawnTickLimit = 60 * 1;
    private int enemySpawnTick = enemySpawnTickLimit;
    private int enemyIndex, waveIndex;
    private int waveTickLimit = 60 * 5;
    private int waveTick = 0;
    private boolean waveStartTimer, waveTickTimerOver;
    public WaveManager(Playing playing) {
        this.playing = playing;
        createWaves();
    }
    public void update() {
        if (enemySpawnTick < enemySpawnTickLimit) {
            enemySpawnTick++;
        }
            if (waveStartTimer) {
                waveTick++;
                if (waveTick >= waveTickLimit) {
                    waveTickTimerOver = true;
                }
            }
    }
    public void increaseWaveIndex() {
        waveIndex++;
        waveTickTimerOver = false;
        waveStartTimer = false;

    }
    public void startWaveTimer() {
     waveStartTimer = true;
    }

    public boolean isWaveTimeOver() {
        return waveTickTimerOver;
    }

    public int getNextEnemy() {
        enemySpawnTick = 0;
       return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
    }

    private void createWaves() {
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0,0,0,0,0,0,0,0,1,1))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1,1,1,0,0,0,0,0,2,2))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2,2,0,0,0,0,0,0,1,1))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2,2,2,1,1,0,0,0,0,2))));
    }
    public ArrayList<Wave> getWaves() {
        return waves;
    }

    public boolean isTimeForNewEnemy() {
        return enemySpawnTick >= enemySpawnTickLimit;
    }

    public boolean isThereMoreEnemiesInWave() {
        return enemyIndex < waves.get(waveIndex).getEnemyList().size();
    }

    public boolean isThereMoreWaves() {
        return (waveIndex + 1) < waves.size();
    }

    public void resetEnemyIndex() {
        enemyIndex = 0;
    }
    public int getWaveIndex() {
        return waveIndex;
    }
    public float getTimeLeft() {
        float ticksLeft = waveTickLimit - waveTick;
        return ticksLeft / 60;
    }

    public boolean isWaveTimerStarted() {
        return waveStartTimer;
    }
}