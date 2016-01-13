package geral;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.joda.time.DateTime;

public class Info {

	// titulo//autor(es)//dataPub//disponibilidade

	/**
	 * A ideia aqui sera a de entrar com um array dos metodos que queremos e
	 * colocar isto mais reutilizavel, em vez de ter ai uma cena p cada metodo//
	 * assim preenchem-se as tabelas que quisermos
	 *
	 */

	public static String[] getInfo(Object p, String[] nomeMetodos){
			
		boolean b1 = false, encontrado = false;
		String[] info = new String[nomeMetodos.length];
		Arrays.fill(info, "-");
		Class<?> c = null;
		try {
			c = Class.forName(p.getClass().getCanonicalName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Method[] methods = c.getMethods();
		Method method1 = null;
		int j = 0;

		for (int i = 0; i < nomeMetodos.length; i++) {
			try {
				c = Class.forName(p.getClass().getCanonicalName());
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			j = 0;
			encontrado = false;
			while (j < methods.length && !encontrado) {
				if (nomeMetodos[i].equals(methods[j].getName())) {
					do {
						try {
							method1 = c.getDeclaredMethod(nomeMetodos[i]);
							info[i] = String.valueOf(method1.invoke(p));
							b1 = true;
						} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							c = c.getSuperclass();
						}
					} while (b1 == false);
					b1 = false;
					encontrado = true;
				}
				j++;
			}
		}
		return info;
	}
}
