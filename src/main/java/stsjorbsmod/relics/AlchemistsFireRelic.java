package stsjorbsmod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.relics.ClickableRelic;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import stsjorbsmod.JorbsMod;
import stsjorbsmod.util.TextureLoader;

import java.util.function.Function;

import static stsjorbsmod.JorbsMod.*;

/**
 * Reduces Burning's falloff rate to 10%
 */
public class AlchemistsFireRelic extends CustomRelic implements ClickableRelic {
    public static final String[] ON_CLICK = CardCrawlGame.languagePack.getUIString(makeID("AlchemistsFireOnClick")).TEXT;
    public static final String ID = JorbsMod.makeID(AlchemistsFireRelic.class.getSimpleName());

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("alchemistsfire_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("alchemistsfire_relic.png"));

    public static final int BURNING_FALLOFF_RATE = 10;
    public static final Function<Integer, Integer> CALCULATE_BURNING_AMOUNT = a -> (a * (100 - BURNING_FALLOFF_RATE)) / 100;

    public AlchemistsFireRelic() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStart() {
        this.flash();
    }

    @Override
    public void initializeTips() {
        this.description = DESCRIPTIONS[0];
        super.initializeTips();
        this.description = getUpdatedDescription();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0].replaceAll(JorbsMod.MOD_ID + ":", "#y");
    }

    @Override
    public AbstractRelic makeCopy() {
        return new AlchemistsFireRelic();
    }

    @Override
    public void onRightClick() {
        AbstractDungeon.effectList.add(new ThoughtBubble(AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, ON_CLICK[0], true));
    }
}
