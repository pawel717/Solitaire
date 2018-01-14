package Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

public class TickTimer extends Timer 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer tickCounter;
	
	public TickTimer(ActionListener listener) 
	{
		super(1000, listener);
		this.tickCounter = 0;
	}
	
	public String getTickCount()
	{
		return this.tickCounter.toString();
	}
	
	@Override
	public void restart()
	{
		super.restart();
		this.tickCounter = 0;
		super.fireActionPerformed(null);
	}
	
	@Override
	protected void fireActionPerformed(ActionEvent e)
	{
		this.tickCounter++;
		super.fireActionPerformed(e);
	}

}
