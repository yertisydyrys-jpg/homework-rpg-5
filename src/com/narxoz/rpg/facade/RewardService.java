package com.narxoz.rpg.facade;

public class RewardService {
    public String determineReward(AdventureResult battleResult) {
        if (battleResult == null) {
            return "No reward: adventure result is missing.";
        }
        if ("Draw".equals(battleResult.getWinner())) {
            return "Consolation prize: 50 gold for a mutual knockout";
        }
        if (!battleResult.isHeroVictory()) {
            return "No reward: the hero was defeated.";
        }
        if (battleResult.getRounds() <= 3) {
            return "Legendary chest: 500 gold, Phoenix Bow and Ember Trophy";
        }
        return "Victory chest: 250 gold and a rare upgrade shard";
    }
}