package ca.carleton.poker.strategy;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ca.carleton.poker.game.GameOption;
import ca.carleton.poker.game.Player.AIPlayer;
import ca.carleton.poker.game.entity.card.Card;
import ca.carleton.poker.game.entity.card.Hand;
import ca.carleton.poker.game.entity.card.PokerHand;
@Service
public interface AIStrategy {
	
	 static final Logger LOG = LoggerFactory.getLogger(AIStrategy1.class);
	  public static  List<Card> doStrategy(AIPlayer player, ArrayList<AIPlayer>players){
		  boolean has3 = false;
		  boolean first = true;
		  PokerHand handvalue = player.getHand().getPokerValue();
		  ArrayList<AIPlayer> others = new ArrayList<>();
		  
		  for(AIPlayer p : players){
			if(!p.equals(player)){
				others.add(p);
				if(p.getHand().visibleHand().isThreeOfAKind()){
					has3 = p.getHand().visibleHand().isThreeOfAKind(); 
				}
				if(p.getLastOption() != null){
					first = false;
				}
			
			}
				
		  }
		  
		 
		
		  /*
			  * if player is not the first to player and another user has a visible 3 of a kind
			  * use strategy2
			  */
	     if(has3 && !first){
			  LOG.info("AI is using Strategy 2. first: {}, has3{}", first, has3);
			  AIStrategy2 ai =  new AIStrategy2(player, others);

			  return ai.chooseCard();
			  
		  }
		  
		  /*
		   *  If you are the first player use strategy 1, or you do not see a 3 of a kind use stategy 1
		   */
	     else if(first && !has3){
			  LOG.info("AI is using Strategy 1. first:{},has3:{}", first, has3);
			  AIStrategy1 ai =  new AIStrategy1(player);
			  return ai.chooseCard();   
		  }
	     else{
	    	  LOG.info("AI is using Strategy 1. first:{},has3:{}", first, has3);
			  AIStrategy1 ai =  new AIStrategy1(player);
			  return ai.chooseCard();   
		    	 
	     }
	  }
}
