package ModelTests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import Helpers.CardStackBuilder;
import Model.Card;
import Model.CardStack;
import Model.MoveValidator;
import Model.Ranks;
import Model.StackTypes;
import Model.Suits;

@RunWith(Parameterized.class)
public class InvalidMoveValidationTest 
{
	
		@Parameters
		   public static Collection<Object[]> parameters() {
			   
			   Card tenDiamonds = new Card(Ranks.ten, Suits.diamonds);
			   Card jackDiamonds = new Card(Ranks.jack, Suits.diamonds);
			   Card queenDiamonds = new Card(Ranks.queen, Suits.diamonds);
			   Card kingDiamonds = new Card(Ranks.king, Suits.diamonds);
			   Card queenSpades = new Card(Ranks.queen, Suits.spades);
			   Card jackSpades = new Card(Ranks.jack, Suits.spades);
			   
			   CardStack emptyPile = new CardStackBuilder()
					   .withType(StackTypes.pile)
					   .build();
			   
			   CardStack jackDiamondsPile = new CardStackBuilder()
					   .add(jackDiamonds)
					   .withType(StackTypes.pile)
					   .build();
			   
			   CardStack emptyFoundation = new CardStackBuilder()
					   .withType(StackTypes.foundation)
					   .build();
			   
			   CardStack jackDiamondsFoundation = new CardStackBuilder()
					   .add(jackDiamonds)
					   .withType(StackTypes.foundation)						   
					   .build();	

			   CardStack waste = new CardStackBuilder()
					   .withType(StackTypes.waste)
					   .build();
			   
			   CardStack queenSpadesStack = new CardStackBuilder()
					   .add(queenSpades)
					   .withType(StackTypes.undefined)
					   .build();
			   
			   CardStack jackSpadesStack = new CardStackBuilder()
					   .add(jackSpades)
					   .withType(StackTypes.undefined)
					   .build();
			   
			   CardStack tenDiamondsStack = new CardStackBuilder()
					   .add(tenDiamonds)
					   .withType(StackTypes.undefined)
					   .build();
			   
			   CardStack jackDiamondsStack = new CardStackBuilder()
					   .add(jackDiamonds)
					   .withType(StackTypes.undefined)
					   .build();
			   
			   CardStack diamondsStack = new CardStackBuilder()
					   .add(queenDiamonds)
					   .add(kingDiamonds)
					   .withType(StackTypes.undefined)
					   .build();
			   
		        return Arrays.asList(new Object[][] {
		                 { emptyPile, jackDiamondsStack }, //[0] card which suit is other than king can't be moved to empty pile
		                 { jackDiamondsPile, tenDiamondsStack },  //[1] card which colour is same as top card on pile can't be moved to pile
		                 { jackDiamondsPile, queenSpadesStack }, //[2] card which rank is higher than top card on pile can't be moved to pile
		                 { jackDiamondsPile, jackSpadesStack }, //[3] card which rank is equal to top card on pile can't be moved to pile
		                 { emptyFoundation, tenDiamondsStack}, //[4] card which suit is other than ace can't be moved to empty foundation
		                 { jackDiamondsFoundation, tenDiamondsStack }, //[5] card which rank is lower than top card on foundation can't be moved to foundation
		                 { jackDiamondsFoundation, jackDiamondsStack}, //[6] card which rank is equal to top card on foundation can't be moved to foundation
		                 { jackDiamondsFoundation, queenSpadesStack }, //[7] card which suit is different from top card on foundation can't be moved to foundation
		                 { jackDiamondsFoundation, diamondsStack }, //[8] stack which contains more than 1 card can't be moved to foundation   
		                 { waste, jackDiamondsStack }  //[9] no stack can be moved to waste
		           });
		    }
		  
		private CardStack destStack;
		private CardStack invalidMovingStack;

		public InvalidMoveValidationTest(CardStack destStack, CardStack invalidMovingStack)
		{
			// Arrange
			this.destStack = destStack;
			this.invalidMovingStack = invalidMovingStack;
		}
		
		@Test
		public void should_return_false_when_invalid_move()
		{	
			// Act
			boolean output = MoveValidator.isValid(invalidMovingStack, destStack);
			
			// Assert
			assertFalse(output);
		}
}
