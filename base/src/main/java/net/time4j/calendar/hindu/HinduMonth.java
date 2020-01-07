/*
 * -----------------------------------------------------------------------
 * Copyright © 2013-2020 Meno Hochschild, <http://www.menodata.de/>
 * -----------------------------------------------------------------------
 * This file (HinduMonth.java) is part of project Time4J.
 *
 * Time4J is free software: You can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Time4J is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Time4J. If not, see <http://www.gnu.org/licenses/>.
 * -----------------------------------------------------------------------
 */

package net.time4j.calendar.hindu;

import net.time4j.calendar.IndianMonth;
import net.time4j.format.CalendarText;

import java.io.Serializable;
import java.util.Locale;


/**
 * <p>The Hindu month varies in length and might also have a leap state when used in lunisolar context. </p>
 *
 * @author  Meno Hochschild
 * @since   5.6
 */
/*[deutsch]
 * <p>Die Hindumonate haben unterschiedliche L&auml;ngen und k&ouml;nnen im lunisolaren Kontext auch im
 * Schaltzustand vorliegen. </p>
 *
 * @author  Meno Hochschild
 * @since   5.6
 */
public final class HinduMonth
    implements Serializable {

    //~ Instanzvariablen --------------------------------------------------

    /**
     * @serial  month of Indian national calendar
     */
    private final IndianMonth value;

    /**
     * @serial  leap month flag
     */
    private final boolean leap;

    //~ Konstruktoren -----------------------------------------------------

    private HinduMonth(
        IndianMonth value,
        boolean leap
    ) {
        super();

        if (value == null) {
            throw new NullPointerException("Missing Indian month.");
        }

        this.value = value;
        this.leap = leap;

    }

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Gets the Hindu month which corresponds to the given Indian month. </p>
     *
     * <p>Users have to invoke the method {@link #withLeap()} in order to obtain a leap month
     * in lunisolar context. </p>
     *
     * @param   month   month of Indian national calendar
     * @return  associated Hindu month
     */
    /*[deutsch]
     * <p>Liefert den normalen Hindumonat basierend auf dem angegebenen indischen Standardmonat. </p>
     *
     * <p>Um einen Schaltmonat im lunisolaren Kontext zu erhalten, ist anschlie&szlig;end die Methode
     * {@link #withLeap()} aufrufen. </p>
     *
     * @param   month   month of Indian national calendar
     * @return  associated Hindu month
     */
    public static HinduMonth of(IndianMonth month) {

        return new HinduMonth(month, false);

    }

    /**
     * <p>Gets the Hindu month which corresponds to the given numerical value in lunisolar context. </p>
     *
     * <p>Users have to invoke the method {@link #withLeap()} in order to obtain a leap month
     * in lunisolar context. The first month is Chaitra. </p>
     *
     * @param   month   month value in the range [1-12]
     * @return  Hindu month as wrapper around a number
     * @throws  IllegalArgumentException if given argument is out of range 1-12
     */
    /*[deutsch]
     * <p>Liefert den normalen Hindumonat mit dem angegebenen kalendarischen Integer-Wert im lunisolaren Kalender. </p>
     *
     * <p>Um einen Schaltmonat im lunisolaren Kontext zu erhalten, ist anschlie&szlig;end die Methode
     * {@link #withLeap()} aufrufen. Der erste Monat ist Chaitra. </p>
     *
     * @param   month   month value in the range [1-12]
     * @return  Hindu month as wrapper around a number
     * @throws  IllegalArgumentException if given argument is out of range 1-12
     */
    public static HinduMonth ofLunisolar(int month) {

        return new HinduMonth(IndianMonth.valueOf(month), false);

    }

    /**
     * <p>Gets the Hindu month which corresponds to the given solar numerical value. </p>
     *
     * <p>Note: The first month of solar calendar is Mesa (or in lunisolar naming Vaishakha). </p>
     *
     * @param   month   month value in the range [1-12]
     * @return  Hindu month as wrapper around a number
     * @throws  IllegalArgumentException if given argument is out of range 1-12
     * @see     #getRasi()
     * @see     #getRasi(Locale)
     */
    /*[deutsch]
     * <p>Liefert den normalen Hindumonat mit dem angegebenen kalendarischen Integer-Wert im Sonnenkalender. </p>
     *
     * <p>Hinweis: Der erste Monat des Sonnenkalenders ist Mesa (oder in lunisolarer Benennung Vaishakha). </p>
     *
     * @param   month   month value in the range [1-12]
     * @return  Hindu month as wrapper around a number
     * @throws  IllegalArgumentException if given argument is out of range 1-12
     * @see     #getRasi()
     * @see     #getRasi(Locale)
     */
    public static HinduMonth ofSolar(int month) {

        int m = ((month == 12) ? 1 : month + 1);
        return new HinduMonth(IndianMonth.valueOf(m), false);

    }

    /**
     * <p>Obtains the corresponding Indian month. </p>
     *
     * <p>Important note: Hindu months in lunisolar context might be expunged which simply means that
     * there are gaps in the numbering of the months per year. And intercalated months have the same number. </p>
     *
     * @return  IndianMonth
     */
    /*[deutsch]
     * <p>Liefert den zugeh&ouml;rigen indischen Monat. </p>
     *
     * <p>Wichtiger Hinweis: Hindumonate im lunisolaren Kontext k&ouml;nnen L&uuml;cken in der Numerierung
     * haben. Und Schaltmonate haben dieselbe Nummer. </p>
     *
     * @return  IndianMonth
     */
    public IndianMonth getValue() {

        return this.value;

    }

    /**
     * <p>Obtains the value of solar month as the sun is going through the corresponding constellation (Rasi). </p>
     *
     * <p>The first solar month is VAISHAKHA. </p>
     *
     * @return  int
     * @see     #ofSolar(int)
     * @see     #getRasi(Locale)
     */
    /*[deutsch]
     * <p>Liefert den Wert des solaren Monats, so wie die Sonne durch die jeweilige Konstellation (Rasi) geht. </p>
     *
     * <p>Der erste solare Monat ist VAISHAKHA. </p>
     *
     * @return  int
     * @see     #ofSolar(int)
     * @see     #getRasi(Locale)
     */
    public int getRasi() {

        return ((this.value == IndianMonth.CHAITRA) ? 12 : this.value.getValue() - 1);

    }

    /**
     * <p>Obtains the localized text of solar month corresponding to the Hindu zodiac (Rasi). </p>
     *
     * <p>In many cases however, the lunisolar name is still used and can be obtained by
     * {@code getValue().getDisplayName(locale)}. </p>
     *
     * @return  String
     * @see     #getRasi()
     * @see     IndianMonth#getDisplayName(Locale)
     */
    /*[deutsch]
     * <p>Liefert den lokalisierten Namen des solaren Monats entsprechend dem Hindu-Tierkreiszeichen (Rasi). </p>
     *
     * <p>In vielen F&auml;llen wird jedoch der lunisolare Name noch verwendet und kann mittels
     * {@code getValue().getDisplayName(locale)} ermittelt werden. </p>
     *
     * @return  String
     * @see     #getRasi()
     * @see     IndianMonth#getDisplayName(Locale)
     */
    public String getRasi(Locale locale) {

        CalendarText names = CalendarText.getInstance("extra/hindu", locale);
        return names.getTextForms("R", IndianMonth.class).print(IndianMonth.valueOf(this.getRasi()));

    }

    /**
     * <p>Determines if this month is in leap state (intercalated month). </p>
     *
     * @return  boolean
     */
    /*[deutsch]
     * <p>Bestimmt, ob dieser Monat ein Schaltmonat ist, also ein eingeschobener Monat. </p>
     *
     * @return  boolean
     */
    public boolean isLeap() {

        return this.leap;

    }

    /**
     * <p>Obtains the leap month version of this month. </p>
     *
     * @return  copy of this month but in leap state
     */
    /*[deutsch]
     * <p>Liefert die geschaltete Version dieses Monats. </p>
     *
     * @return  copy of this month but in leap state
     */
    public HinduMonth withLeap() {

        return (this.leap ? this : new HinduMonth(this.value, true));

    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        } else if (obj instanceof HinduMonth) {
            HinduMonth that = (HinduMonth) obj;
            return ((this.value == that.value) && (this.leap == that.leap));
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {

        return this.value.hashCode() + (this.leap ? 12 : 0);

    }

    @Override
    public String toString() {

        String s = this.value.toString();
        return (this.leap ? "*" + s : s);

    }

}
