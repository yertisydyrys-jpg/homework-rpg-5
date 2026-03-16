package com.narxoz.rpg.decorator;

public class PoisonCoatingDecorator extends ActionDecorator {
    private static final int POISON_BONUS_DAMAGE = 4;

    public PoisonCoatingDecorator(AttackAction wrappedAction) {
        super(wrappedAction);
    }

    @Override
    public String getActionName() {
        return super.getActionName() + " + Poison Coating";
    }

    @Override
    public int getDamage() {
        return super.getDamage() + POISON_BONUS_DAMAGE;
    }

    @Override
    public String getEffectSummary() {
        return super.getEffectSummary() + ", toxin coating (" + POISON_BONUS_DAMAGE + " bonus poison damage)";
    }
}