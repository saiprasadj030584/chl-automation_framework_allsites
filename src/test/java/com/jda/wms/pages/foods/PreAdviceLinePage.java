package com.jda.wms.pages.foods;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Match;
import org.sikuli.script.Screen;
import com.google.inject.Inject;

public class PreAdviceLinePage {
	Screen screen = new Screen();
	int timeoutInSec = 20;
	@Inject
	public PreAdviceLinePage() {
	}
	public void enterPreAdviceID(String preAdviceId) {
		screen.type(preAdviceId);
		
	}
	public void enterSkuID(String sku) throws FindFailed {
		Match mSkuId = screen.find("images/PreAdviceLine/General/SkuId.png");
		screen.click(mSkuId.getCenter().offset(70, 0));
		screen.type(sku);
	}
	public String getPalletType() throws FindFailed {
		Match mSkuId = screen.find("images/PreAdviceLine/General/palletType.png");
		screen.click(mSkuId.getCenter().offset(70, 0));
		screen.type("a", Key.CTRL);
		screen.type("c", Key.CTRL);
		return App.getClipboard();
	}
		
	public String enterPalletType(String palletType) throws FindFailed {
		Match mSkuId = screen.find("images/PreAdviceLine/General/palletType.png");
		screen.click(mSkuId.getCenter().offset(70, 0));
		screen.type(palletType);
		return App.getClipboard();
	}

}
