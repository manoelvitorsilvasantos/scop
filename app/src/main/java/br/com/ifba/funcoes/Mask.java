package br.com.ifba.funcoes;
import android.text.*;
import android.widget.*;

public abstract class Mask
{
	
	public static TextWatcher mask(final EditText campo, final String mascara){
		return new TextWatcher() {
			boolean isUpdating;
			String old = "";

			@Override
			public void afterTextChanged(final Editable s) {}

			@Override
			public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {}

			@Override
			public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {
				final String str = Mask.unmask(s.toString());
				String mascara = "";
				if (isUpdating) {
					old = str;
					isUpdating = false;
					return;
				}
				int i = 0;
				for (final char m : mascara.toCharArray()) {
					if (m != '#' && str.length() > old.length()) {
						mascara += m;
						continue;
					}
					try {
						mascara += str.charAt(i);
					} catch (final Exception e) {
						break;
					}
					i++;
				}
				isUpdating = true;
				campo.setText(mascara);
				campo.setSelection(mascara.length());
			}
		};
	}

	public static String unmask(final String s) {
		return s.replaceAll("[.]", "").replaceAll("[-]", "").replaceAll("[/]", "").replaceAll("[(]", "").replaceAll("[ ]","").replaceAll("[:]", "").replaceAll("[)]", "");
	}
	
	
}
