package com.f14.innovation.exectuer;

import com.f14.bg.anim.AnimVar;
import com.f14.bg.exception.BoardGameException;
import com.f14.innovation.InnoGameMode;
import com.f14.innovation.InnoPlayer;
import com.f14.innovation.anim.InnoAnimParamFactory;
import com.f14.innovation.component.InnoCard;
import com.f14.innovation.component.ability.InnoAbility;
import com.f14.innovation.component.ability.InnoAbilityGroup;
import com.f14.innovation.consts.InnoAchieveTrigType;
import com.f14.innovation.param.InnoInitParam;
import com.f14.innovation.param.InnoResultParam;

/**
 * 合并的行动
 * 
 * @author F14eagle
 *
 */
public class InnoMeldExecuter extends InnoActionExecuter {

	public InnoMeldExecuter(InnoGameMode gameMode, InnoPlayer player,
			InnoInitParam initParam, InnoResultParam resultParam,
			InnoAbility ability, InnoAbilityGroup abilityGroup) {
		super(gameMode, player, initParam, resultParam, ability, abilityGroup);
	}

	@Override
	public void doAction() throws BoardGameException {
		InnoPlayer player = this.getTargetPlayer();
		for(InnoCard card : this.getResultCards()){
			player.meld(card);
			AnimVar from = this.getResultParam().getAnimVar(card);
			this.getGame().sendAnimationResponse(InnoAnimParamFactory.createMeldCardParam(player, card, from));
			this.getGame().sendPlayerCardStackResponse(player, card.color, null);
			this.getGame().sendPlayerIconsInfoResponse(player, null);
			
			gameMode.executeAchieveChecker(InnoAchieveTrigType.STACK_CHANGE, player);
		}
	}

}
