package Model;


public class MoveValidator 
{
	public static boolean isValid(CardStack movingStack, CardStack destStack)
	{
		
		switch(destStack.getType())
		{
			
			case pile:
				if(destStack.isEmpty())
					if(movingStack.getLastCard().getRank() == Ranks.king) 
						return true;
					else return false;
				
				if(movingStack.getLastCard().getColour() != destStack.getFirstCard().getColour() && 
				   movingStack.getLastCard().getRank().ordinal() == destStack.getFirstCard().getRank().ordinal()-1)
					return true;
				return false;
				
			case foundation:
				if(movingStack.size() > 1)
					return false;
				
				if(destStack.isEmpty())
				{
					if(movingStack.getLastCard().getRank() == Ranks.ace) 
						return true;
					return false;
				}
				
				if(movingStack.getLastCard().getSuit() == destStack.getFirstCard().getSuit() && 
				   destStack.getFirstCard().getRank().ordinal() == movingStack.getLastCard().getRank().ordinal()-1)
					return true;
				return false;
				
			default:
				return false;
				
		}
	}
}
