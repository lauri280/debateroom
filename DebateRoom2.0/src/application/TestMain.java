package application;

import java.util.ArrayList;

public class TestMain {

	public static void main(String[] args) {
		OsalejateKogum osalejad = new OsalejateKogum();
		Randomiseerija randomiseerija = new Randomiseerija();
		
		osalejad.lisaOsaleja(new Osaleja("aa", false));
		osalejad.lisaOsaleja(new Osaleja("bb", false));
		osalejad.lisaOsaleja(new Osaleja("cc", false));
		osalejad.lisaOsaleja(new Osaleja("dd", false));
		osalejad.lisaOsaleja(new Osaleja("ee", false));
		osalejad.lisaOsaleja(new Osaleja("ff", false));
		osalejad.lisaOsaleja(new Osaleja("gg", false));
//		osalejad.lisaOsaleja(new Osaleja("hh", false));
//		osalejad.lisaOsaleja(new Osaleja("ii", false));
//		osalejad.lisaOsaleja(new Osaleja("jj", false));
		
		System.out.println(osalejad.getClass().getName());
		

	}

}
