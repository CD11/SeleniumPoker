package selenium;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;

import ca.carleton.poker.game.entity.card.Card;
import ca.carleton.poker.game.entity.card.Hand;
import ca.carleton.poker.game.entity.card.Rank;
import ca.carleton.poker.game.entity.card.Suit;
import config.SeleniumTest;
import selenium.page.IndexPage;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.concurrent.TimeUnit;

/**
 * Created by Cheryl 
 */
@SeleniumTest
public class Strategy1Tests extends AbstractSeleniumTest {

    @Autowired
    private IndexPage indexPage;
    
    
    @Test
    public void Rig1(){
    	/* This had is rigged with :
    	 * 	- O1:  >straight
    	 *  - O2: 3 of a kind
    	 *  - 03: has a pair
    	 *  - p:  high card
    	 */
    	
      	this.indexPage.connect.click();
    	this.waitForDisplayed(this.indexPage.open).isEnabled();
    	this.delay(2);
    	this.indexPage.open.click(); // defaults is 1 player
    	this.waitForDisplayed(this.indexPage.rig).isEnabled();
    	this.indexPage.rig.click();
    	
    	
    	/************************* Set inital hands ************************/
    	// Player  highcard 
    	this.waitForAlert();
    	Alert a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 hearts, rank-3 spades, rank-4 clubs, rank-9 spades, rank-k clubs");
    	a.accept();
    	
    	// other 1  Flush
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-10 hearts, rank-8 hearts, rank-7 hearts, rank-2 hearts, rank-a hearts");
    	a.accept();
    	
    	// other 2 three of a kind
    	this.waitForAlert();
    	a =this.webDriver.switchTo().alert();
    	a.sendKeys("rank-6 clubs, rank-6 hearts, rank-6 spades, rank-2 spades, rank-7 diams");
    	a.accept();
    	
    	
    	// other 3 has a pair
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 diams, rank-2 clubs, rank-4 diams, rank-7 clubs, rank-6 diams");
    	a.accept();
     

    	assertThat(this.indexPage.hasText("Setting all cards"), is(true));     
    	/****************************** check user options ************************/
    	
    	String[] ids = this.indexPage.getUsetTexts();
    	// user 1 will stay 
    	String result = ids[0] + " choose to STAY";
    	System.out.println(this.indexPage.hasText(result));
    	assertThat(this.indexPage.hasText(result), is(true));
    	
    	//user 2 has hit
    	//Handle prompt to improve user 2 
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("0:rank-a clubs, 4:rank-k spades");
    	a.accept();
    	
 
    	result = ids[1] + " choose to HIT";
    	assertThat(this.indexPage.hasText(result), is(true));
    	
    	//user 3 has hit
    	//Handle prompt to improve user 3 

    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("2:rank-j clubs, 3:rank-10 spades, 4:rank-8 diams");
    	a.accept();
    	
    	this.delay(1);
    	result = ids[2] + " choose to HIT";
    	assertThat(this.indexPage.hasText(result), is(true));
    	    	
    	// Player will hit
    	this.delay(1);
     	assertThat(this.waitForDisplayed(this.indexPage.hit).isEnabled(), is(true));
    	this.indexPage.hit.click();
    	this.indexPage.card1.click();
    	this.indexPage.card3.click();
    	this.indexPage.done.click();
     	
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("0:rank-q diams, 1:rank-j clubs, 2:rank-4 spades, 3:rank-8 clubs,  4:rank-2 clubs");
    	a.accept();
    	
   
    	this.delay(5);
    	assertThat(this.waitForDisplayed(this.indexPage.start).isEnabled(), is(true));
    	/*****************  Check results ******************/
    	this.delay(5);
    	// user 1 wins
    	result = ids[0] + " won with a score of FLUSH, ranked 1";
    	System.out.println(this.indexPage.hasText(result));
    	assertThat(this.indexPage.hasText(result), is(true));
    	
    	//user 2 looses
    	result = ids[1] + " lost with a score of THREE_OF_A_KIND, ranked 2";
    	assertThat(this.indexPage.hasText(result), is(true));
    	
    
    	// user 3 looses
    	result = ids[2] + " lost with a score of ONE_PAIR, ranked 3";
    	assertThat(this.indexPage.hasText(result), is(true));
    	    	
    	// Player looses
    	result = ids[3] + " lost with a score of HIGH_CARD, ranked 4";
    	assertThat(this.indexPage.hasText(result), is(true));
    
    	
    	this.indexPage.disconnect.click();
    
    }


    @Test
    public void Rig2(){
    	/*This had is rigged with :
       	 * 	- O1:  has 2 pairs
       	 *  - O2:  has 2 pairs
       	 *  - 03:  has 3 of a kind
       	 *  - p:   has 1 pair
       	 */
    	
      	this.indexPage.connect.click();
    	this.waitForDisplayed(this.indexPage.open).isEnabled();
    	this.delay(2);
    	this.indexPage.open.click(); // defaults is 1 player
    	this.waitForDisplayed(this.indexPage.rig).isEnabled();
    	this.indexPage.rig.click();
    	
    	
    	/************************* Set inital hands ************************/
    	
    	// Player  1 pair
    	this.waitForAlert();
    	Alert a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 hearts, rank-4 hearts, rank-7 hearts, rank-k clubs, rank-k hearts");
    	a.accept();
    	
    	// other 1 2 pairs
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-10 hearts, rank-10 clubs, rank-3 diams, rank-3 spades, rank-a hearts");
    	a.accept();
    	
    	// other 3 2 pairs
    	this.waitForAlert();
    	a =this.webDriver.switchTo().alert();
    	a.sendKeys("rank-j clubs, rank-j spades, rank-8 clubs, rank-8 spades, rank-3 diams");
    	a.accept();
    	
    	
    	// other 3  three of a kind
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("rank-2 clubs, rank-9 spades, rank-q clubs, rank-q spades, rank-q diams");
    	a.accept();
     

    	assertThat(this.indexPage.hasText("Setting all cards"), is(true)); 
    	this.delay(5);
    	/****************************** check user options ************************/
    	
    	//Handle prompt to improve user 1  gets a full house
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("0:rank-5 clubs,1:rank-5 diams,4:rank-5 spades");
    	a.accept();
    	
    
    	
    	String[] ids = this.indexPage.getUsetTexts();
    	
    	// user 1 will Hit 
    	this.delay(1);
    	String result = ids[0] + " choose to HIT";
    	System.out.println(this.indexPage.hasText(result));
    	assertThat(this.indexPage.hasText(result), is(true));
    	
    	
    	//Handle prompt to improve user 2  gets nothing
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("4:rank-j spades");
    	a.accept();
    	//user 2 has Hit
    	this.delay(1);
    	result = ids[1] + " choose to HIT";
    	assertThat(this.indexPage.hasText(result), is(true));
    	
    	// user 3 will hit
    	//Handle prompt to improve user 3  gets a full house
    	this.waitForAlert();
    	a = this.webDriver.switchTo().alert();
    	a.sendKeys("0:rank-2 clubs,1:rank-2 diams");
    	a.accept();
    	
    	this.delay(1);
    	result = ids[2] + " choose to HIT";
    	assertThat(this.indexPage.hasText(result), is(true));
    	    	
    	// Player will stay
    	this.delay(5);
     	assertThat(this.waitForDisplayed(this.indexPage.stay).isEnabled(), is(true));
    	this.indexPage.stay.click();

    	this.delay(2);
    	assertThat(this.waitForDisplayed(this.indexPage.start).isEnabled(), is(true));
    	/*****************  Check results ******************/
    	this.delay(5);
    	// user 1 loses
    	result = ids[0] + " lost with a score of FULL_HOUSE, ranked 2";
    	System.out.println(this.indexPage.hasText(result));
    	assertThat(this.indexPage.hasText(result), is(true));
    	
    	//user 2 looses
    	result = ids[1] + " lost with a score of TWO_PAIR, ranked 3";
    	assertThat(this.indexPage.hasText(result), is(true));
    	
    	// user 3 looses
    	result = ids[2] + " won with a score of FULL_HOUSE, ranked 1";
    	assertThat(this.indexPage.hasText(result), is(true));
    	    	
    	// Player looses
    	result = ids[3] + " lost with a score of ONE_PAIR, ranked 4";
    	assertThat(this.indexPage.hasText(result), is(true));
    
    	
    	this.indexPage.disconnect.click();
    
    }
    
    
   




 
 
 

  
    
    
    
    
}
