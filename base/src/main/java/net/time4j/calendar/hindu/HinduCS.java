/*
 * -----------------------------------------------------------------------
 * Copyright © 2013-2020 Meno Hochschild, <http://www.menodata.de/>
 * -----------------------------------------------------------------------
 * This file (HinduCS.java) is part of project Time4J.
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

import net.time4j.engine.CalendarEra;
import net.time4j.engine.CalendarSystem;

import java.util.Arrays;
import java.util.List;


/**
 * <p>Abstract calendar system for the Hindu calendar. </p>
 *
 * @author  Meno Hochschild
 * @since   5.6
 */
/*[deutsch]
 * <p>Abstraktes Kalendersystem f&uuml;r den Hindu-Kalender. </p>
 *
 * @author  Meno Hochschild
 * @since   5.6
 */
abstract class HinduCS
    implements CalendarSystem<HinduCalendar> {

    //~ Instanzvariablen --------------------------------------------------

    final HinduVariant variant;

    //~ Konstruktoren -----------------------------------------------------

    HinduCS(HinduVariant variant) {
        super();

        if (variant == null) {
            throw new NullPointerException();
        }

        this.variant = variant;
    }

    //~ Methoden ----------------------------------------------------------

    @Override
    public final HinduCalendar transform(long utcDays) {
        long min = this.getMinimumSinceUTC();
        long max = this.getMaximumSinceUTC();

        if ((utcDays < min) || (utcDays > max)) {
            throw new IllegalArgumentException("Out of range: " + min + " <= " + utcDays + " <= " + max);
        }

        return this.create(utcDays);
    }

    @Override
    public final long transform(HinduCalendar date) {
        return date.getDaysSinceEpochUTC();
    }

    @Override
    public List<CalendarEra> getEras() {
        return Arrays.asList(HinduEra.values());
    }

    abstract HinduCalendar create(long utcDays);

    abstract HinduCalendar create(
        int kyYear,
        HinduMonth month,
        HinduDay dom
    );

    abstract boolean isValid(
        int kyYear,
        HinduMonth month,
        HinduDay dom
    );

    // used in subclasses
    static double modulo(double x, double y) {
        return x - y * Math.floor(x / y);
    }

}
