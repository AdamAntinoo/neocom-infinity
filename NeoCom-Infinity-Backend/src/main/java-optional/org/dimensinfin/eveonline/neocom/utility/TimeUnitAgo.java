package org.dimensinfin.eveonline.neocom.utility;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author Adam Antinoo (adamantinoo.git@gmail.com)
 * @since 0.20.0
 */
public class TimeUnitAgo {
	public final static long ONE_SECOND = 1000;
	public final static long SECONDS = 60;
	public final static long ONE_MINUTE = ONE_SECOND * 60;
	public final static long MINUTES = 60;
	public final static long ONE_HOUR = ONE_MINUTE * 60;
	public final static long HOURS = 24;
	public final static long ONE_DAY = ONE_HOUR * 24;

	public static final List<Long> times = Arrays.asList(
			TimeUnit.DAYS.toMillis( 365 ),
			TimeUnit.DAYS.toMillis( 30 ),
			TimeUnit.DAYS.toMillis( 1 ),
			TimeUnit.HOURS.toMillis( 1 ),
			TimeUnit.MINUTES.toMillis( 1 ),
			TimeUnit.SECONDS.toMillis( 1 ) );
	public static final List<String> timesString = Arrays.asList( "year", "month", "day", "hour", "minute", "second" );

	public static String toDuration( final long duration ) {
		final StringBuffer res = new StringBuffer();
		for (int i = 0; i < TimeUnitAgo.times.size(); i++) {
			final Long current = TimeUnitAgo.times.get( i );
			final long temp = duration / current;
			if (temp > 0) {
				res.append( temp ).append( " " ).append( TimeUnitAgo.timesString.get( i ) ).append( temp != 1 ? "s" : "" ).append( " ago" );
				break;
			}
		}
		if ("".equals( res.toString() ))
			return "0 seconds ago";
		else
			return res.toString();
	}

	public static String millisToLongDHMS( long duration ) {
		final StringBuffer res = new StringBuffer();
		long temp = 0;
		if (duration >= ONE_SECOND) {
			temp = duration / ONE_DAY;
			if (temp > 0) {
				duration -= temp * ONE_DAY;
				res.append( temp ).append( " day" ).append( temp > 1 ? "s" : "" )
						.append( duration >= ONE_MINUTE ? ", " : "" );
			}

			temp = duration / ONE_HOUR;
			if (temp > 0) {
				duration -= temp * ONE_HOUR;
				res.append( temp ).append( " hour" ).append( temp > 1 ? "s" : "" )
						.append( duration >= ONE_MINUTE ? ", " : "" );
			}

			temp = duration / ONE_MINUTE;
			if (temp > 0) {
				duration -= temp * ONE_MINUTE;
				res.append( temp ).append( " minute" ).append( temp > 1 ? "s" : "" );
			}

			if (!res.toString().equals( "" ) && duration >= ONE_SECOND) {
				res.append( " and " );
			}

			temp = duration / ONE_SECOND;
			if (temp > 0) {
				res.append( temp ).append( " second" ).append( temp > 1 ? "s" : "" );
			}
			return res.toString();
		} else {
			return "0 second";
		}
	}
}