package visitors;

import java.security.InvalidParameterException;

import net.ivoa.parameter.model.ParameterType;

import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * @author Carlo Maria Zwolf, Lerma/ObsPM
 * 
 *         Implementation of interface Icreteria for testing Date (Iso8601)
 *         parameter type
 * 
 */

public class DateCriteria implements Icriteria {

	private static final ParameterType authorizedCriteriaType = ParameterType.DATE;

	public boolean VerifyCriteria(ParameterType type, String value) {
		boolean isCorrectDate = false;
		if (type == this.getAuthorizedCriteriaType()) {

			try {
				DateTimeFormatter parser1 = ISODateTimeFormat
						.dateTimeNoMillis();
				parser1.parseDateTime(value);
				isCorrectDate = true;

			} catch (Exception e) {
				throw new InvalidParameterException("can't cast " + value
						+ " to an ISO date");
			}
		}
		return isCorrectDate;
	}

	public ParameterType getAuthorizedCriteriaType() {
		return this.authorizedCriteriaType;
	}

}
