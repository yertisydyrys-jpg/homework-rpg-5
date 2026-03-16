package com.narxoz.rpg.decorator;

public class FireRuneDecorator extends ActionDecorator {
    private static final int FIRE_BONUS_DAMAGE = 6;

    public FireRuneDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return super.getActionName() + " + Fire Rune";
    }

    @Override
    public int getDamage() {
        return super.getDamage() + FIRE_BONUS_DAMAGE;
    }

    @Override
    public String getEffectSummary() {
        return super.getEffectSummary() + ", burning rune (" + FIRE_BONUS_DAMAGE + " bonus fire damage)";
    }
}