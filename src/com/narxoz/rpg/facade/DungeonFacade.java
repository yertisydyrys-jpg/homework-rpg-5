package com.narxoz.rpg.facade;

import com.narxoz.rpg.decorator.AttackAction;
import com.narxoz.rpg.enemy.BossEnemy;
import com.narxoz.rpg.hero.HeroProfile;

public class DungeonFacade {
    private final PreparationService preparationService = new PreparationService();
    private final BattleService battleService = new BattleService();
    private final RewardService rewardService = new RewardService();

    public DungeonFacade setRandomSeed(long seed) {
        battleService.setRandomSeed(seed);
        return this;
    }

    public AdventureResult runAdventure(HeroProfile hero, BossEnemy boss, AttackAction action) {
        String preparationSummary = preparationService.prepare(hero, boss, action);
        AdventureResult battleResult = battleService.battle(hero, boss, action);

        AdventureResult finalResult = new AdventureResult();
        finalResult.addLine(preparationSummary);
        for (String line : battleResult.getLog()) {
            finalResult.addLine(line);
        }
        finalResult.setWinner(battleResult.getWinner());
        finalResult.setRounds(battleResult.getRounds());
        finalResult.setHeroVictory(battleResult.isHeroVictory());
        finalResult.setReward(rewardService.determineReward(finalResult));
        finalResult.addLine("Reward granted: " + finalResult.getReward());
        return finalResult;
    }
}