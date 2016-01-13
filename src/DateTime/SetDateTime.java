package DateTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.DateTime;

public class SetDateTime {


	public static DateTime setDate(String data) {

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date d1 = null;
		DateTime date1 = null;
		try {
			d1 = format.parse(data);
			date1 = new DateTime(d1);
		} catch (ParseException e) {
			System.out.println("Erro na data");
		}
		return date1;
	}
}
