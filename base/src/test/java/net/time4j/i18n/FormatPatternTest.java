package net.time4j.i18n;

import net.time4j.Moment;
import net.time4j.PlainDate;
import net.time4j.PlainTime;
import net.time4j.PlainTimestamp;
import net.time4j.format.CalendarText;
import net.time4j.format.DisplayMode;
import net.time4j.format.expert.ChronoFormatter;
import net.time4j.tz.OffsetSign;
import net.time4j.tz.Timezone;
import net.time4j.tz.ZonalOffset;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Locale;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(JUnit4.class)
public class FormatPatternTest {

    @Test
    public void patternForUnsupportedLocale() {
        String patternXX = CalendarText.patternForDate(DisplayMode.FULL, new Locale("xx"));
        String patternRoot = CalendarText.patternForDate(DisplayMode.FULL, Locale.ROOT);
        assertThat(patternXX, is(patternRoot));
    }

    @Test
    public void datePattern() {
        PlainDate date = PlainDate.of(2015, 9, 8);
        assertThat(
            ChronoFormatter.ofDateStyle(DisplayMode.FULL, Locale.GERMANY).format(date),
            is("Dienstag, 8. September 2015"));
        assertThat(
            ChronoFormatter.ofDateStyle(DisplayMode.LONG, Locale.GERMANY).format(date),
            is("8. September 2015"));
        assertThat(
            ChronoFormatter.ofDateStyle(DisplayMode.MEDIUM, Locale.GERMANY).format(date),
            is("08.09.2015"));
        assertThat(
            ChronoFormatter.ofDateStyle(DisplayMode.SHORT, Locale.GERMANY).format(date),
            is("08.09.15"));
    }

    @Test
    public void timePattern() {
        PlainTime time = PlainTime.of(17, 45, 30);
        assertThat(
            ChronoFormatter.ofTimeStyle(DisplayMode.FULL, Locale.GERMANY).print(time),
            is("17:45 Uhr")); // ohne Offset!!!
        assertThat(
            ChronoFormatter.ofTimeStyle(DisplayMode.LONG, Locale.GERMANY).print(time),
            is("17:45:30")); // ohne Offset!!!
        assertThat(
            ChronoFormatter.ofTimeStyle(DisplayMode.MEDIUM, Locale.GERMANY).print(time),
            is("17:45:30")); // ohne Offset!!!
        assertThat(
            ChronoFormatter.ofTimeStyle(DisplayMode.SHORT, Locale.GERMANY).print(time),
            is("17:45")); // ohne Offset!!!
    }

    @Test
    public void timestampPattern() {
        PlainTimestamp tsp = Moment.UNIX_EPOCH.toZonalTimestamp(ZonalOffset.ofHours(OffsetSign.AHEAD_OF_UTC, 1));
        assertThat(
            ChronoFormatter.ofTimestampStyle(DisplayMode.FULL, DisplayMode.FULL, Locale.GERMANY).format(tsp),
            is("Donnerstag, 1. Januar 1970 um 01:00:00")); // ohne Offset!!!
        assertThat(
            ChronoFormatter.ofTimestampStyle(DisplayMode.LONG, DisplayMode.LONG, Locale.GERMANY).format(tsp),
            is("1. Januar 1970 um 01:00:00")); // ohne Offset!!!
        assertThat(
            ChronoFormatter.ofTimestampStyle(DisplayMode.MEDIUM, DisplayMode.MEDIUM, Locale.GERMANY).format(tsp),
            is("01.01.1970, 01:00:00")); // ohne Offset!!!
        assertThat(
            ChronoFormatter.ofTimestampStyle(DisplayMode.SHORT, DisplayMode.SHORT, Locale.GERMANY).format(tsp),
            is("01.01.70, 01:00")); // ohne Offset!!!
        assertThat(
            ChronoFormatter.ofStyle(DisplayMode.MEDIUM, Locale.GERMANY, PlainTimestamp.axis()).format(tsp),
            is("01.01.1970, 01:00:00")); // ohne Offset!!!
    }

    @Test
    public void dateTimePattern() {
        Moment m = Moment.UNIX_EPOCH;
        assertThat(
            ChronoFormatter.ofMomentStyle(
                DisplayMode.FULL, DisplayMode.FULL, Locale.GERMANY, Timezone.of("Europe/Berlin").getID()).print(m),
            is("Donnerstag, 1. Januar 1970 um 01:00:00 Mitteleuropäische Zeit"));
        assertThat(
            ChronoFormatter.ofMomentStyle(
                DisplayMode.LONG, DisplayMode.LONG, Locale.GERMANY, Timezone.of("Europe/Berlin").getID()).print(m),
            is("1. Januar 1970 um 01:00:00 MEZ"));
        assertThat(
            ChronoFormatter.ofMomentStyle(
                DisplayMode.MEDIUM, DisplayMode.MEDIUM, Locale.GERMANY, Timezone.of("Europe/Berlin").getID()).print(m),
            is("01.01.1970, 01:00:00"));
        assertThat(
            ChronoFormatter.ofMomentStyle(
                DisplayMode.SHORT, DisplayMode.SHORT, Locale.GERMANY, Timezone.of("Europe/Berlin").getID()).print(m),
            is("01.01.70, 01:00"));
    }

}